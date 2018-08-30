/*
 * (c)BOC
 */
package net.pis.service.signal;

import net.pis.common.*;
import net.pis.dto.table.DTIInterfaceDTO;
import net.pis.dto.table.DTIStatusDTO;
import net.pis.exception.AdaptingException;
import net.pis.exception.SBMSException;
import net.pis.message.MessageMetaInfo;
import net.pis.service.table.DTIInterfaceService;
import net.pis.service.table.DTIStatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
/**
 * NonSAP 시그널 핸들러
 *
 * @author jh,Seo
 */
public abstract class TaxInvoiceNonSapSignalHandler extends TaxInvoiceSignalHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected DTIInterfaceService dTIInterfaceService;

    @Autowired
    protected DTIStatusService dTIStatusService;

    @Override
    public Adapter getAdpater() {
        return new NonSapAdapter();
    }

    @Override
    public void afterRecieveAckAboutRequest(MessageMetaInfo messageMetaInfo, MessageStatus messageStatus, MessageStatus transferState) throws AdaptingException {
        logger.info("=== TaxInvoiceNonSapSignalHandler.afterRecieveAckAboutRequest() =============");
        try {

            DTIInterfaceDTO paramDTO = new DTIInterfaceDTO();
            paramDTO.setMessageId(messageMetaInfo.getMessageId());

            List<DTIInterfaceDTO> interfaceList = dTIInterfaceService.read(paramDTO);

            if (!interfaceList.isEmpty()) {

                DTIInterfaceDTO interfaceDTO = interfaceList.get(0);

                logger.info("인터페이스 상태 갱신 : {}", messageStatus.getCode());
                interfaceDTO.setMessageStatusFlag(messageStatus.getCode());
                interfaceDTO.setLastUpdateDate(Calendar.getInstance().getTime());

                dTIInterfaceService.update(interfaceDTO);

                DTIStatusDTO statusDTOParm = new DTIStatusDTO();
                statusDTOParm.setConversationId(interfaceDTO.getConversationId());
                statusDTOParm.setSupbuyType(interfaceDTO.getSupbuyType());
                statusDTOParm.setDirection(interfaceDTO.getDirection());

                String statusCode = dTIStatusService.read(statusDTOParm).get(0).getTranStatus();

                logger.debug("현재 계산서의 전송 상태 : {}", statusCode);

                if (MessageStatus.Sending.getCode().equals(statusCode)) {

                    DTIStatusDTO statusDTO = new DTIStatusDTO();

                    statusDTO.setConversationId(interfaceDTO.getConversationId());
                    statusDTO.setSupbuyType(interfaceDTO.getSupbuyType());
                    statusDTO.setDirection(interfaceDTO.getDirection());

                    logger.info("변경할 계산서의 전송 상태 : {}", transferState.getCode());
                    statusDTO.setTranStatus(transferState.getCode());

                    statusDTO.setLastUpdatedBy("SBMS");
                    statusDTO.setLastUpdateDate(Calendar.getInstance().getTime());

                    if (MessageStatus.Error.equals(messageStatus)) {

                        logger.info("MessageStatus.Error : " + MessageStatus.Error);
/*                        ErrorLogDTO errorLogDTOParam = new ErrorLogDTO();
                        errorLogDTOParam.setMessageTagId(messageMetaInfo.getMessageTagId());
                        ErrorLogDTO errorLogDTO = errorLogService.getLatestErrorLogDTO(errorLogDTOParam);

                        statusDTO.setReturnCode(errorLogDTO.getCodeId());
                        statusDTO.setReturnDescription(errorLogDTO.getErrorMessage());*/

                    }

                    dTIStatusService.update(statusDTO);

                } else if (MessageStatus.Error.getCode().equals(statusCode)) {
                    // FIXME : 재시도 전에 오류인 경우 상태 변경 하는 로직을 삽입할 것

                } else {
                    logger.info("ACK보다 먼저 Result가 도달한 경우 상태와 관련된 일은 하지 않는다....");
                }
            } else {
                logger.info("수신 오류로 ERP에 기록하지 못한 경우.");
            }

        } catch (Exception e) {
            SBMSException se = new SBMSException(
                    AdaptingException.AdaptingError.ACK_STATUS_EXCEPTION.getErrorCode(), e);

            throw new AdaptingException(se);
        }        
    }

    @Override
    public void afterReceiveResult(MessageMetaInfo messageMetaInfo, MessageStatus messageStatus, MessageStatus transferState) throws AdaptingException {
        logger.info("=== TaxInvoiceNonSapSignalHandler.afterReceiveResult() =============");
        try {
            DTIInterfaceDTO paramDTO = new DTIInterfaceDTO();
            paramDTO.setMessageId(messageMetaInfo.getMessageId());

            List<DTIInterfaceDTO> interfaceList = dTIInterfaceService.read(paramDTO);
            DTIInterfaceDTO interfaceDTO = interfaceList.get(0);

            DTIStatusDTO statusDTOParm = new DTIStatusDTO();
            statusDTOParm.setConversationId(interfaceDTO.getConversationId());
            statusDTOParm.setSupbuyType(interfaceDTO.getSupbuyType());
            statusDTOParm.setDirection(interfaceDTO.getDirection());

            DTIStatusDTO statusDTO = new DTIStatusDTO();

            statusDTO.setConversationId(interfaceDTO.getConversationId());
            statusDTO.setSupbuyType(interfaceDTO.getSupbuyType());
            statusDTO.setDirection(interfaceDTO.getDirection());

            statusDTO.setTranStatus(messageStatus.getCode());

            statusDTO.setLastUpdateDate(Calendar.getInstance().getTime());
            statusDTO.setLastUpdatedBy(SYSTEM_NAME);

            if (MessageStatus.Error.equals(messageStatus)) {
                logger.info("MessageStatus.Error : " + MessageStatus.Error);

/*                ErrorLogDTO errorLogDTOParam = new ErrorLogDTO();
                errorLogDTOParam.setMessageTagId(messageMetaInfo.getMessageTagId());
                ErrorLogDTO errorLogDTO = errorLogService.getLatestErrorLogDTO(errorLogDTOParam);

                statusDTO.setReturnCode(errorLogDTO.getCodeId());
                statusDTO.setReturnDescription(
                        StringUtil.parseErrorMessage(errorLogDTO.getErrorMessage(), 2000));
                        */
            } else {
                statusDTO.setReturnCode(SUCCESS_CODE);
                statusDTO.setReturnDescription(""); // 과거의 오류 메세지를 갱신하기 위해 빈 문자열을 삽입한다.
            }

            dTIStatusService.update(statusDTO);

            updateFinalStatus(
                    messageMetaInfo,
                    interfaceDTO, statusDTO);

        } catch (Exception e) {

            SBMSException se = new SBMSException(
                    AdaptingException.AdaptingError.RESULT_STATUS_EXCEPTION.getErrorCode(), e);

            throw new AdaptingException(se);
        }
    }

    @Override
    public void afterRecieveAckAboutResult(MessageMetaInfo messageMetaInfo, MessageStatus messageStatus, MessageStatus transferState) throws AdaptingException {
        logger.info("=== TaxInvoiceNonSapSignalHandler.afterRecieveAckAboutResult() =============");
        try {
            DTIInterfaceDTO paramDTO = new DTIInterfaceDTO();
            paramDTO.setMessageId(messageMetaInfo.getMessageId());

            List<DTIInterfaceDTO> interfaceList = dTIInterfaceService.read(paramDTO);
            DTIInterfaceDTO interfaceDTO = interfaceList.get(0);

            DTIStatusDTO statusDTOParm = new DTIStatusDTO();
            statusDTOParm.setConversationId(interfaceDTO.getConversationId());
            statusDTOParm.setSupbuyType(interfaceDTO.getSupbuyType());
            statusDTOParm.setDirection(interfaceDTO.getDirection());

            // 결과는 ACK 의 수신여부에 관심이 없다. 그냥 갱신함
            // 'T' 상태일 경우가 정상적인 경우
            DTIStatusDTO statusDTO = new DTIStatusDTO();

            statusDTO.setConversationId(interfaceDTO.getConversationId());
            statusDTO.setSupbuyType(interfaceDTO.getSupbuyType());
            statusDTO.setDirection(interfaceDTO.getDirection());

            statusDTO.setTranStatus(messageStatus.getCode());

            statusDTO.setLastUpdateDate(Calendar.getInstance().getTime());
            statusDTO.setLastUpdatedBy(SYSTEM_NAME);

            if (MessageStatus.Error.equals(messageStatus)) {

                logger.info("MessageStatus.Error : " + MessageStatus.Error);
                /***
                ErrorLogDTO errorLogDTOParam = new ErrorLogDTO();
                errorLogDTOParam.setMessageTagId(messageMetaInfo.getMessageTagId());
                ErrorLogDTO errorLogDTO = errorLogService.getLatestErrorLogDTO(errorLogDTOParam);

                statusDTO.setReturnCode(errorLogDTO.getCodeId());
                statusDTO.setReturnDescription(
                        StringUtil.parseErrorMessage(errorLogDTO.getErrorMessage(), 2000));
                 */
            } else {
                statusDTO.setReturnCode(SUCCESS_CODE);
                statusDTO.setReturnDescription("");
            }

            dTIStatusService.update(statusDTO);

            updateFinalStatus(messageMetaInfo, interfaceDTO, statusDTO);

        } catch (Exception e) {

            SBMSException se = new SBMSException(
                    AdaptingException.AdaptingError.RESULT_STATUS_EXCEPTION.getErrorCode(), e);

            throw new AdaptingException(se);
        }
    }


    /**
     * 자동 수신 승인 쓰레드
     *
     * @param conversationId
     * @param supbuyType
     * @param direction
     * @return
     */
    protected Runnable getAutoApproveRunnable(final String conversationId, final String supbuyType, final String direction) {

        logger.info("=== TaxInvoiceNonSapSignalHandler.getAutoApproveRunnable() ==================");

        return new Runnable() {
            @Override
            public void run() {
                logger.info("++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                logger.info("conversationId : {}", conversationId);
                logger.info("supBuyType : {}", supbuyType);
                logger.info("direction : {}", direction);

                try {

                    Thread.sleep(3 * 1000); // 자동 수신승인 3초 대기
                } catch (InterruptedException ie) {
                    logger.info("{}", ie);
                }

                String uuid = UUID.randomUUID().toString();

                Date now = Calendar.getInstance().getTime();

                DTIStatusDTO dtiStatusDTO = new DTIStatusDTO();

                dtiStatusDTO.setDtiStatus(TaxInvoiceStatus.Confirmation.getCode());

                dtiStatusDTO.setConversationId(conversationId);
                dtiStatusDTO.setSupbuyType(supbuyType);
                dtiStatusDTO.setDirection(direction);
                dtiStatusDTO.setLastUpdateDate(now);
                dtiStatusDTO.setLastUpdatedBy("SBMS");

                dTIStatusService.update(dtiStatusDTO);

                DTIInterfaceDTO newDTO = new DTIInterfaceDTO();

                newDTO.setMessageId(uuid);
                newDTO.setConversationId(conversationId);
                newDTO.setSupbuyType(supbuyType);
                newDTO.setDirection(direction);
                newDTO.setSignal(TaxInvoiceSignal.APPROVE.name());
                newDTO.setMessageStatusFlag("N");
                newDTO.setLastUpdateDate(now);
                // newDTO.setAuthTicket("sbms@smartbill.co.kr");
                newDTO.setAuthTicket("test@test.com");

                dTIInterfaceService.create(newDTO);
            }
        };

    }

    /**
     * 최종상태 갱신
     */
    private void updateFinalStatus(MessageMetaInfo messageMetaInfo,
                                   DTIInterfaceDTO dTIInterfaceDTO, DTIStatusDTO dTIStatusDTO) {
        
        
        logger.info("================ TaxInvoiceNonSapSignalHandler.updateFinalStatus() ===================");
        logger.info("최종 세금계산서 상태를 결정 시작");

        // 모든 상태의 오류가 없는 경우
        if (!messageMetaInfo.isError()
                && !MessageStatus.Error.getCode().endsWith(dTIInterfaceDTO.getMessageStatusFlag())
                && !MessageStatus.Error.getCode().endsWith(dTIStatusDTO.getTranStatus())) {

            TaxInvoiceSignal signal = TaxInvoiceSignal.valueOf(
                    messageTagService.getMessageTag(messageMetaInfo).getSignal());

            TaxInvoiceStatus taxInvoiceStatus = TaxInvoiceStatus.Saved;
            switch (signal) {
                case ARISSUE_FINISH:
                case DETAILARISSUE_FINISH:
                case RARISSUE_FINISH:
                case RDETAILISSUE_FINISH: {

                    taxInvoiceStatus = TaxInvoiceStatus.Unapproved;
                    break;
                }
                case CHGSTATUS_FINISH: {

                    TaxInvoiceSignal innerSiganl
                            = TaxInvoiceSignal.valueOf(dTIInterfaceDTO.getSignal());

                    switch (innerSiganl) {
                        case APPROVE: {
                            taxInvoiceStatus = TaxInvoiceStatus.Confirmation;
                            break;
                        }
                        case REJECT: {
                            taxInvoiceStatus = TaxInvoiceStatus.Rejection;
                            break;
                        }
                        case RIREJECT: {
                            taxInvoiceStatus = TaxInvoiceStatus.IssueRequestRejection;
                            break;
                        }
                        case CANCELALL: {
                            taxInvoiceStatus = TaxInvoiceStatus.Canceled;
                            break;
                        }
                        case CANCELRREQUEST: {
                            taxInvoiceStatus = TaxInvoiceStatus.IssueRequestCanceled;
                            break;
                        }
                    }
                    break;
                }
                case RARREQUEST_FINISH:
                case RDETAILREQUEST_FINISH: {
                    taxInvoiceStatus = TaxInvoiceStatus.IssueRequest;
                    break;
                }

            }

            //멀티발행건은 최종 상태 업데이트 하지 않음
            if (0 > signal.name().lastIndexOf("_S_FINISH")) {
                dTIStatusDTO.setFinalStatus(taxInvoiceStatus.getCode());
                dTIStatusDTO.setLastUpdatedBy(SYSTEM_NAME);
                dTIStatusDTO.setLastUpdateDate(Calendar.getInstance().getTime());

                dTIStatusService.update(dTIStatusDTO);
            }

        }

        logger.info("최종 세금계산서 상태를 결정 완료");

    }    
}
