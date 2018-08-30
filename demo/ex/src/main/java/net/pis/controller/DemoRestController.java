package net.pis.controller;

import lombok.extern.slf4j.Slf4j;
import net.pis.common.*;
import net.pis.dto.ContractDTO;
import net.pis.dto.table.DTIInterfaceDTO;
import net.pis.exception.SBMSException;
import net.pis.message.MessageMetaInfo;
import net.pis.service.DemoService;
import net.pis.service.table.DTIInterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.jms.JMSException;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by PARKIS on 2018-07-30.
 * Title : Rest 컨트롤러
 */

@RestController
@Slf4j
public class DemoRestController {

    @Autowired
    private DemoService demoService;

    // Test JMS Controller
    @Autowired
    private AdaptingController taxInvoiceAdaptingController;

    @Autowired
    private DTIInterfaceService dtiInterfaceService;

    @Autowired
    private JmsSender jmsSender;

    // Query DSL
    @RequestMapping(value = "/restdemo1", method = {RequestMethod.POST})
    public ResponseEntity restDemo1(HttpServletRequest req, @RequestBody Map<String, Object> paramMap) {
        if (paramMap != null) {
            log.info("=== restdemo1 paramMap : " + paramMap.toString());
        }

        Map<String, Object> result = new HashMap<String, Object>();

        result = demoService.getList(paramMap);

        log.info("============restDemo==============");
        log.info("result : " + result.toString());

        return JSONResponse.getJSONResponse(req,result);
    }

    // JPA
    @RequestMapping(value = "/restdemo2", method = {RequestMethod.POST})
    public ResponseEntity restDemo2(HttpServletRequest req, @RequestBody Map<String, Object> paramMap) {

        if (paramMap != null) {
            log.info("=== restdemo2 paramMap : " + paramMap.toString());
        }

        Map<String, Object> result = new HashMap<String, Object>();

        paramMap.put("id", "1");
        result = demoService.getListJPA(paramMap);

        log.info("============restDemo==============");
        log.info("result : " + result.toString());

        return JSONResponse.getJSONResponse(req,result);
    }

    // Mybatis
    @RequestMapping(value = "/restmybatis1", method = {RequestMethod.POST})
    public ResponseEntity restMybatis1(HttpServletRequest req, @RequestBody Map<String, Object> paramMap) {

        if (paramMap != null) {
            log.info("=== restmybatis1 paramMap : " + paramMap.toString());
        }

        Map<String, Object> result = new HashMap<String, Object>();

        result = demoService.getNumTest(paramMap);

        log.info("============restMybatis1==============");
        log.info("result : " + result.toString());

        return JSONResponse.getJSONResponse(req,result);
    }

    // XXSB_DTI_INTERFACE Message Status를 N으로 업데이트
    // Outbound 메시지 생성을 위해 Interface 테이블 메시지 상태 변경
    @RequestMapping(value = "/updtDtiInterfaceMsgStatusN", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity updtDtiInterfaceMsgStatusN(HttpServletRequest req, @RequestBody Map<String, Object> paramMap) {

        if (paramMap != null) {
            log.info("=== updtDtiInterfaceMsgStatusN paramMap : " + paramMap.toString());
        }

        Map<String, Object> result = new HashMap<String, Object>();

        result = demoService.updtDtiInterfaceMsgStatusN(paramMap);

        log.info("============updtDtiInterfaceMsgStatusN==============");
        log.info("result : " + result.toString());

        return JSONResponse.getJSONResponse(req,result);
    }

    // JMS 호출 테스트를 위한 리스트 호출
    @RequestMapping(value = "/getListToTestJMS", method = {RequestMethod.POST})
    public ResponseEntity getListToTestJMS(HttpServletRequest req, @RequestBody Map<String, Object> paramMap) {

        if (paramMap != null) {
            log.info("=== getListToTestJMS paramMap : " + paramMap.toString());
        }

        Map<String, Object> result = new HashMap<String, Object>();

        List<ContractDTO> targetList = demoService.read();

        List<MessageMetaInfo> messageMetaInfoList = new ArrayList<>();

        try {
            for (ContractDTO interfaceDTO : targetList) {

                String uuid = UUID.randomUUID().toString();
                MessageMetaInfo metaInfo = new MessageMetaInfo();
                metaInfo.setMessageTagId(uuid);
                metaInfo.setMessageId("MESSAGE_ID_EXAMPLE_01");
                metaInfo.setDirection(Direction.Outbound);
                metaInfo.setDestination(Listener.Router);
                metaInfo.setAck(false);
                metaInfo.setError(false);
                metaInfo.setTargetSystemId("SYSTEM_ID_EXAMPLE_01");

                metaInfo.setTicket("TICKET_EXAMPLE_01");

                metaInfo.setErpSystem("NONSAP"); // NONSAP으로 고정하자 signal 셋팅

                messageMetaInfoList.add(metaInfo);

                // JMS 처리 가능한 Controller로 전송
                taxInvoiceAdaptingController.handleOutbound(metaInfo);
            }
            result.put("jmsList", messageMetaInfoList);
        } catch (SBMSException se ){
            log.info("DemoRestController SBMSException : " + se);
        }

        return JSONResponse.getJSONResponse(req,result);
    }

    // JMS 인바운드 호출 (SB -> Inbound Adaptor)
    @RequestMapping(value = "/sendMsgInbound", method = {RequestMethod.POST})
    public ResponseEntity sendMsgInbound(HttpServletRequest req, @RequestBody Map<String, Object> paramMap) {

        if (paramMap != null) {
            log.info("=== sendMsgInbound paramMap : " + paramMap.toString());
        }

        Map<String, Object> result = new HashMap<String, Object>();

        List<ContractDTO> targetList = demoService.read();
        ContractDTO contractDTO = (ContractDTO) targetList.get(0);

        MessageMetaInfo metaInfo = null;
        boolean sendResult = false;

        try {
            String uuid = UUID.randomUUID().toString();
            metaInfo = new MessageMetaInfo();
            metaInfo.setMessageTagId(uuid);
            metaInfo.setMessageId("00D9FCFA-3AB3-4668-96E5-358FE9864D46");
            // 인바운드 Router로 보낸다
            // Adaptor -> Router -> Connector 순으로 전달되듯
            // 반대로 SB에서 리턴될때도 Connector -> Router로 가야되는것 같다
            // 단계를 건너뛰어서 바로 특정 계층으로 보내버리는건 잘못된 방식인듯
            // 여기선 스마트빌에 갔다가 돌아왔다고 가정해야 하므로 라우터로 보내도록 하는게 맞음
            metaInfo.setDestination(Listener.Router);
            metaInfo.setDirection(Direction.Inbound);


            metaInfo.setAck(true); // SB에서 완료처리 됐다고 가정한다.

            metaInfo.setError(false); // SB에서 에러 없다고 가정한다
            metaInfo.setTargetSystemId("nonsap");

            metaInfo.setTicket("TICKET_EXAMPLE_01");

            metaInfo.setErpSystem("NONSAP"); // NONSAP으로 고정하자 signal 셋팅

            // JMS 처리 가능한 Controller로 메시지 전송
            jmsSender.sendMessage(metaInfo);

            sendResult = true;
        } catch (JMSException je) {
            log.info("DemoRestController JMSException : " + je);
            sendResult = false;
        } finally {
            result.put("sendResult", sendResult);
        }

        return JSONResponse.getJSONResponse(req,result);
    }

    // JMS 호출 테스트를 위한 리스트 호출 (DTI)
    @RequestMapping(value = "/getListToTestDtiJMS", method = {RequestMethod.POST})
    public ResponseEntity getListToTestDtiJMS(HttpServletRequest req, @RequestBody Map<String, Object> paramMap) {

        if (paramMap != null) {
            log.info("=== getListToTestDtiJMS paramMap : " + paramMap.toString());
        }

        Map<String, Object> result = new HashMap<String, Object>();

        DTIInterfaceDTO paramDto = new DTIInterfaceDTO();
        paramDto.setMessageStatusFlag("N");

        List<DTIInterfaceDTO> targetList = dtiInterfaceService.read(paramDto, 100);

        List<MessageMetaInfo> messageMetaInfoList = new ArrayList<>();

        try {
            for (DTIInterfaceDTO interfaceDTO : targetList) {

                DTIInterfaceDTO paramDTO = new DTIInterfaceDTO();
                paramDTO.setMessageId(interfaceDTO.getMessageId());
                paramDTO.setConversationId(interfaceDTO.getConversationId());
                paramDTO.setSupbuyType(interfaceDTO.getSupbuyType());
                paramDTO.setDirection(interfaceDTO.getDirection());
                paramDTO.setMessageStatusFlag(MessageStatus.Recognition.getCode()); //"R - 인지"

                dtiInterfaceService.update(paramDTO);

                String uuid = UUID.randomUUID().toString();
                MessageMetaInfo metaInfo = new MessageMetaInfo();
                metaInfo.setMessageTagId(uuid);
                metaInfo.setMessageId(interfaceDTO.getMessageId());
                metaInfo.setDirection(Direction.Outbound);
                metaInfo.setDestination(Listener.Router);
                metaInfo.setAck(false);
                metaInfo.setError(false);
                metaInfo.setTargetSystemId(interfaceDTO.getTargetSystemId());

                metaInfo.setTicket(interfaceDTO.getAuthTicket());

                metaInfo.setErpSystem("NONSAP");
                metaInfo.setTargetKey("nonsap");

                messageMetaInfoList.add(metaInfo);

                // JMS 처리 가능한 Controller로 전송
                taxInvoiceAdaptingController.handleOutbound(metaInfo);
            }
        } catch (SBMSException se) {
            log.info(se.toString());
        }

        result.put("resultList", targetList);

        return JSONResponse.getJSONResponse(req,result);
    }
}
