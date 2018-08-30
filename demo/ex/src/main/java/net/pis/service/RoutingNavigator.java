/*
 * (c)BOC
 */
package net.pis.service;

import net.pis.common.TaxInvoiceSignal;
import net.pis.common.service.DocumentDataService;
import net.pis.dto.MessageTagDTO;
import net.pis.dto.RoutingInfoDTO;
import net.pis.dto.StatefulDTO;
import net.pis.exception.AdaptingException;
import net.pis.message.MessageMetaInfo;
import net.pis.service.table.MessageTagService;
import net.pis.service.table.RoutingInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
/**
 * 멀티 어댑터를 위한 ERP어댑터 파인더
 *
 * @author jh,Seo
 */
@Service
public class RoutingNavigator {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RoutingInfoService routingInfoService;

    @Autowired
    private MessageTagService messageTagService;

    @Autowired
    private DocumentDataService documentDataService;

    //SAP Handler에서 사용하는듯 없으니까 일단 막자
    /*
    @Autowired
    @Resource(name = "statefulMap")
    private Map<String, StatefulDTO> statefulMap;
    */

    /**
     * SAP에서 나올때 사용한다
     *
     * @param clientCode
     * @param companyCode
     * @return
     */
    public RoutingInfoDTO getRoutingPathInfo(String clientCode, String companyCode) {

        RoutingInfoDTO resultDTO = null;

        RoutingInfoDTO paramDTO = new RoutingInfoDTO();
        paramDTO.setClient(clientCode);
        paramDTO.setCompany(companyCode);

        List<RoutingInfoDTO> routingInfoDTOList = routingInfoService.read(paramDTO);
        if (!routingInfoDTOList.isEmpty()) {
            resultDTO = routingInfoDTOList.get(0);
        }

        return resultDTO;

    }

    public RoutingInfoDTO getRoutingPathInfo(String systemId) {

        if (systemId == null) {
            systemId = "default";
        }

        RoutingInfoDTO resultDTO = null;

        RoutingInfoDTO paramDTO = new RoutingInfoDTO();
        paramDTO.setSystemId(systemId);

        List<RoutingInfoDTO> routingInfoDTOList = routingInfoService.read(paramDTO);
        if (!routingInfoDTOList.isEmpty()) {
            resultDTO = routingInfoDTOList.get(0);
        }

        return resultDTO;
    }

    private RoutingInfoDTO getRoutingInfoByTargetSystemId(String targetSystemId) {

        RoutingInfoDTO routingInfoDTO = null;

        RoutingInfoDTO routingParam = new RoutingInfoDTO();
        routingParam.setSystemId(targetSystemId);

        List<RoutingInfoDTO> routingInfos = routingInfoService.read(routingParam);

        if (!routingInfos.isEmpty()) {

            routingInfoDTO = routingInfos.get(0);

        }

        return routingInfoDTO;
    }

    private RoutingInfoDTO getRoutingInfoByDestination(String destination) {
        RoutingInfoDTO param = new RoutingInfoDTO();
        param.setTargetKey(destination);

        return routingInfoService.read(param).get(0);
    }

    public RoutingInfoDTO getRoutingPathInfo(MessageMetaInfo messageMetaInfo) throws AdaptingException {

        logger.info("<ROUTING> 라우팅 패스 정보 검색 [시작]");
        RoutingInfoDTO routingInfoDTO = null;

        String messageTagId = messageMetaInfo.getMessageTagId();
        String messageId = messageMetaInfo.getMessageId();

        MessageTagDTO messageTagDTO = messageTagService.getMessageTagDTO(messageTagId);
        String conversationId = messageTagDTO.getConversationId();

        String targetSystemId = messageTagDTO.getTargetSystemId();
        String senderSystemId = messageTagDTO.getSenderSystemId();

        switch (TaxInvoiceSignal.valueOf(messageTagDTO.getSignal())) {
            case STATS_REQUEST:
            case STATS_REQUEST_FINISH:
            case STATS_RESULT:
            case STATS_RESULT_FINISH:

                RoutingInfoDTO sbmsRouting = new RoutingInfoDTO();

                sbmsRouting.setAdapter("SBMS");
                sbmsRouting.setTargetKey("SBMS");

                return sbmsRouting;
        }

        if (messageMetaInfo.isAck()) { // ACK 메세지라면 그냥 있는 어댑터를 사용한다.

            logger.info("<ROUTING> 라우팅 패스 정보 검색중 - ACK 처리");
            routingInfoDTO = getRoutingInfoByDestination(senderSystemId);

        } else {

            switch (TaxInvoiceSignal.valueOf(messageTagDTO.getSignal()).toMessageType()) {
                case RESULT: { // 결과 인바운드라면, 반드시 내가 송신한 기록이 남아 있다.

                    logger.info("<ROUTING> 라우팅 패스 정보 검색중 - RESULT 타입 : 송신 기록 검색");
                    MessageTagDTO tagDTOParam = new MessageTagDTO();
                    tagDTOParam.setMessageId(messageId);

                    for (MessageTagDTO tag : messageTagService.read(tagDTOParam)) {

                        if (messageTagId.equals(tag.getMessageTagId())) {
                            continue; // 나 자신의 기록은 SKIP
                        }

                        routingInfoDTO = getRoutingInfoByDestination(tag.getSenderSystemId());
                    }
                    break;
                }
                default: { // REQUEST or STATUS

                    if (null != targetSystemId && !"".equals(targetSystemId)) { // 스마트빌에서 타겟을 준 경우

                        logger.info("<ROUTING> 라우팅 패스 정보 검색중 - REQUEST 타입 :  스마트빌에서 넘겨준 타겟 : {}", targetSystemId);
                        routingInfoDTO = getRoutingInfoByTargetSystemId(targetSystemId);

                    } else { // STATUS 인 경우 타겟을 안준다.

                        logger.info("<ROUTING> 라우팅 패스 정보 검색중 - STATUS 타입 : 발행 기록을 검색합니다. ");
                        MessageTagDTO messageTagDTOParam = new MessageTagDTO();
                        messageTagDTOParam.setConversationId(conversationId);

                        List<MessageTagDTO> messageTagDTOs = messageTagService.read(messageTagDTOParam);
                        for (MessageTagDTO tagDTO : messageTagDTOs) { // 발행 기록을 찾는다.

                            TaxInvoiceSignal signal = TaxInvoiceSignal.valueOf(tagDTO.getSignal());

                            switch (signal) {
                                case ARISSUE:
                                case DETAILARISSUE:
                                case RARREQUEST:
                                case RDETAILREQUEST: {

                                    logger.info("<ROUTING> 라우팅 패스 정보 검색중 - STATUS 타입 : 발행 기록이 매칭됨 : " + signal);
                                    routingInfoDTO = getRoutingInfoByDestination(tagDTO.getSenderSystemId());

                                    break;
                                }
                            }
                        }

                        break;
                    } // enf of STATUS
                } // end of default

            } // end of switch
        }
        logger.info("<ROUTING> 라우팅 패스 이메일 정보 검색 [완료] ");

        if (null == routingInfoDTO) { // 라우팅을 이미 찾아냈다면 이후 식은 무의미

            logger.info("<ROUTING> 이메일로 못찾겠습니다.");

            //사업자번호로 라우팅 Destination 을 찾는다. 이 행위는 Destination 의 SYSTEM ID 가 사업자번호로 되어있을 경우
            //위의 이메일보다 사업자번호의 SYSTEM ID 를 Destination 으로  우선 적용되어진다.
            //사업자번호로 라우팅 Dest를 찾음 ( 이메일 다음으로 검사 )
            logger.info("<ROUTING> 라우팅 패스 정보 사업자번호로 재검색  [시작]");

            //List<RoutingInfoDTO> bizRoutingInfoDTO = null;
            RoutingInfoDTO bizRoutingParam = new RoutingInfoDTO();

            logger.info("<ROUTING> 라우팅 패스 정보 사업자번호로 재검색  - 기존 발행 이력 조회");
            //Conversation ID로 tag 기록 찾음

            try {
                bizRoutingParam.setSystemId(messageTagService.getMessageTag(messageMetaInfo).getReceiveRegNo());
                routingInfoDTO = routingInfoService.read(bizRoutingParam).get(0);

            } catch (Exception e) {
                logger.warn("<ROUTING> 사업자번호로 라우팅 SYSTEMID 검색시 오류 [무시]");
            }

            logger.info("<ROUTING> 라우팅 패스 정보 사업자번호로 재검색 [완료]");
        }

        if (null == routingInfoDTO) {
            logger.info("<ROUTING> 사업자 번호로도 못찾겠습니다.");
            logger.info("<ROUTING> 'default' 로 검색");
            RoutingInfoDTO routingParam = new RoutingInfoDTO();
            routingParam.setSystemId("default");

            List<RoutingInfoDTO> routingInfos = routingInfoService.read(routingParam);

            if (!routingInfos.isEmpty()) {
                routingInfoDTO = routingInfos.get(0);
            }
        }

        if (null == routingInfoDTO) {

            AdaptingException.AdaptingError err = AdaptingException.AdaptingError.CANNOT_FIND_ADAPTER;
            throw new AdaptingException(
                err.getErrorCode(),
                err.getErrorMessage());
        } else {

            MessageTagDTO paramDTO = new MessageTagDTO();
            paramDTO.setMessageTagId(messageTagId);

            logger.info("라우팅 키 : {}", routingInfoDTO.getTargetKey());

            paramDTO.setSenderSystemId(routingInfoDTO.getTargetKey());
            messageTagService.update(paramDTO);

            return routingInfoDTO;

        }

    }

    public RoutingInfoDTO test(MessageMetaInfo messageMetaInfo) throws AdaptingException {

        String messageTagId = messageMetaInfo.getMessageTagId();

        MessageTagDTO tagDTO = messageTagService.getMessageTagDTO(messageTagId);

        if (null != tagDTO.getTargetSystemId()) { // 서버에서 보내준 타켓이 있다면,

            String targetSystemId = tagDTO.getTargetSystemId();

            RoutingInfoDTO routingInfoDTOParam = new RoutingInfoDTO();
            routingInfoDTOParam.setSystemId(targetSystemId);

            List<RoutingInfoDTO> routingInfos = routingInfoService.read(routingInfoDTOParam);

            if (routingInfos.isEmpty()) { // 서버에서 보내준게 없다면,

            }

        }

        return null;

    }

}
