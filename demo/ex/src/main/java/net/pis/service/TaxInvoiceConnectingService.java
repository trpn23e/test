/*
 * (c)BOC
 */
package net.pis.service;

import net.pis.common.Direction;
import net.pis.common.JmsSender;
import net.pis.common.Listener;
import net.pis.common.MessageStatus;
import net.pis.common.service.DocumentDataService;
import net.pis.common.service.FileDataService;
import net.pis.common.service.MetaDocumentDataService;
import net.pis.dto.DocumentDataDTO;
import net.pis.dto.FileDataDTO;
import net.pis.dto.MessageTagDTO;
import net.pis.dto.MetaDocumentDataDTO;
import net.pis.dto.table.DTIInterfaceDTO;
import net.pis.dto.table.DTIStatusDTO;
import net.pis.exception.ConnectingException;
import net.pis.exception.SBMSException;
import net.pis.message.MessageMetaInfo;
import net.pis.service.table.DTIStatusService;
import net.pis.service.table.MessageService;
import net.pis.service.table.MessageTagService;
import net.pis.wsserver.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.ws.rs.WebApplicationException;
import javax.xml.ws.WebServiceException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

/**
 * 컨넥팅 서비스
 * <p>
 * 스마트빌과 중계서버간 송,수신 데이타를 처리한다.
 * </p>
 *
 * @author jh, Seo
 */
@Service
//@Scope(scopeName = "prototype")
@ManagedResource
public class TaxInvoiceConnectingService implements ConnectingService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${sbms.bill.endpoint}")
    private String billEndpoint;

    // @Value("${sbms.oapi.endpoint}")
    private String oapiEndpoint;

    //@Autowired
    // @Resource(name = "neobillClient")
    private IRequest neobillClient;

    @Autowired
    private MessageTagService messageTagService;

    @Autowired
    private FileDataService fileDataService;

    @Autowired
    private DocumentDataService documentDataService;

    @Autowired
    private MetaDocumentDataService metaDocumentDataService;

    @Autowired
    private DTIStatusService dTIStatusService;

    @Autowired
    @Resource(name = "retryMap")
    private Map<String, Integer> retryMap;

    @ManagedAttribute(description = "재시도 맵에 담겨있는 친구들")
    public int getRetryCount() {
        return retryMap.size();
    }

    @Autowired
    private JmsSender jmsSender;

    @Autowired
    private MessageService messageService;

    private final String[] retryExceptionMessage = new String[]{
            "Marshalling Error",
            "Failed to create service"
    };


    @Override
    public Object connectToMessageServer(Object object) throws ConnectingException {
        ItgBillACKResponse ack = new ItgBillACKResponse();

        if (object instanceof ItgBillRequest) {
            this.connectToMessageServerForRequest(object, ack);
        } else if (object instanceof ItgBillResponse) {
            // this.connectToMessageServerForResult(object, ack);
        }
        return ack;
    }

    private ItgBillACKResponse connectToMessageServerForRequest(Object object, ItgBillACKResponse ack) {
        ItgBillRequest request = (ItgBillRequest) object;
        for (String mainDoc : request.getMSGDOC().getString()) {

        }
        for (String metaDoc : request.getREQDOC().getString()) {

        }
        for (AttachFile attachFile : request.getFILES().getAttachFile()) {

        }
        return ack;
    }

    public void connectToSmartBill(MessageMetaInfo messageMetaInfo) throws ConnectingException {
        logger.info("== TaxInvoiceConnectingService connectToSmartBill() === ");
        logger.info("== signal :  === " + messageService.getSignal(messageMetaInfo));
        logger.info("== messageType :  === " + messageService.getSignal(messageMetaInfo).toMessageType());
        switch (messageService.getSignal(messageMetaInfo).toMessageType()) {
            case RESULT:
                logger.info(">> 스마트빌에 결과 메세시 송신");
                // this.connectToSmartBillForResult(messageMetaInfo);
                break;

            case OpenAPI: {
                logger.info(">> 스마트빌에 oAPI 메세시 송신");
                // this.connectToSmartBillByOpenAPI(messageMetaInfo);
                break;
            }
            case InnerService: {
                logger.info(">> 내부 루프백 서비스");
                // this.connectToSelf(messageMetaInfo);
                break;
            }
            case REQUEST:
            case UTIL:
            case STATUS: {
                logger.info(">> 스마트빌에 요청 메세시 송신");
                this.connectToSmartBillForRequest(messageMetaInfo);
                break;
            }
        }
    }


    /**
     * 스마트빌로 보내는 REQUEST 메시지 처리
     *
     * @param messageMetaInfo
     * @throws ConnectingException
     */
    private void connectToSmartBillForRequest(MessageMetaInfo messageMetaInfo) throws ConnectingException {

        /*
        ActivityLogDTO activityLogDTO = new ActivityLogDTO();
        activityLogDTO.setActivity(DOMAIN);
        activityLogDTO.setActivityLogId(UUID.randomUUID().toString());
        activityLogDTO.setMessageTagId(messageMetaInfo.getMessageTagId());
        activityLogDTO.setRegTimestamp(Calendar.getInstance().getTime());
        activityLogDTO.setActivityStatus(MessageStatus.Progress.getCode());
        activityLogService.addLogToBackground(activityLogDTO);
        */

        ConnectingException ce = null;

        try {

            logger.debug("MESSAGE_TAG 상태 갱신 'Sending...'");

            Date now = Calendar.getInstance().getTime();
            MessageTagDTO messageTagDTOParam = new MessageTagDTO();
            messageTagDTOParam.setMessageTagId(messageMetaInfo.getMessageTagId());
            messageTagDTOParam.setMessageStatus(MessageStatus.Sending.getCode());
            messageTagDTOParam.setSendTimestamp(now);
            messageTagDTOParam.setCurrTimestamp(now);

            //테이블 없기때문에 패스!
            //messageTagService.update(messageTagDTOParam);

            ItgBillRequest itgBillRequest = new ItgBillRequest();

            ArrayOfAttachFile attachFile = new ArrayOfAttachFile();
            //테이블 없으므로 패스!
            /***
            ArrayOfAttachFile attachFile = new ArrayOfAttachFile();
            List<FileDataDTO> attachFileList = fileDataService.getFileDatas(messageMetaInfo.getMessageTagId());

            List<AttachFile> afileList = new ArrayList<>();

            for (FileDataDTO fileDTO : attachFileList) {
                AttachFile file = new AttachFile();
                file.setFILENAME(fileDTO.getFileName());
                file.setFILESIZE(fileDTO.getFileSize());
                file.setFILEDATA(fileDTO.getFileData());
                file.setFILESEQ(fileDTO.getFileSeq());
                afileList.add(file);
            }

            attachFile.getAttachFile().addAll(afileList);
             **/
            itgBillRequest.setFILES(attachFile);

            ArrayOfstring arrayOfstring = new ArrayOfstring();
            List<String> messageDocumentList = new ArrayList<>();

            // 얘도 테이블 없음.. 넣었다 치자
            /**
            List<DocumentDataDTO> messageMainDocumentList = documentDataService.getDocumentDataList(messageMetaInfo.getMessageTagId());

            if (null != messageMainDocumentList) {
                for (DocumentDataDTO documentDataDTO : messageMainDocumentList) {
                    messageDocumentList.add(documentDataDTO.getDocumentData());
                }

            }**/

            arrayOfstring.getString().addAll(messageDocumentList);
            itgBillRequest.setMSGDOC(arrayOfstring);

            // 얘도 테이블 없음...
            // MessageTagDTO messageTagDTO = messageTagService.getMessageTag(messageMetaInfo);
            MessageTagDTO messageTagDTO = new MessageTagDTO();

            RequestHeader requestHeader = new RequestHeader();
            arrayOfstring = new ArrayOfstring();
            List<String> requestHeaderConversationIdList = new ArrayList<>();
            requestHeaderConversationIdList.add(messageTagDTO.getConversationId());
            arrayOfstring.getString().addAll(requestHeaderConversationIdList);
            requestHeader.setCONVERSATIONID(arrayOfstring);
            requestHeader.setMESSAGEID(messageTagDTO.getMessageId());
            requestHeader.setRCVCOMREGNO(messageTagDTO.getReceiveRegNo());
            //requestHeader.setREQTIME(messageTagDTO.getCurrTimestamp().); // TODO : REQ_TIME 어느걸로 MAPPING? 자릿수 포함
            requestHeader.setSERVICECODE(messageTagDTO.getServiceId());
            requestHeader.setSIGNAL(messageTagDTO.getSignal());
            requestHeader.setSNDCOMREGNO(messageTagDTO.getSendRegNo());
            requestHeader.setSYSTEMTYPE(messageTagDTO.getFromSystem());
            requestHeader.setRCVSYSTEMID(messageTagDTO.getTargetSystemId());

            requestHeader.setAUTHTICKET(messageMetaInfo.getTicket());
            itgBillRequest.setREQUESTHEADER(requestHeader);

            //object - messagedocument
            arrayOfstring = new ArrayOfstring();
            List<String> messageMetaList = new ArrayList<>();
            //이것도 테이블 x pass!
            //List<MetaDocumentDataDTO> messageMetaDocumentList = metaDocumentDataService.getMetaDocumentDataList(messageMetaInfo.getMessageTagId());

            List<MetaDocumentDataDTO> messageMetaDocumentList = new ArrayList<MetaDocumentDataDTO>();
            if (null != messageMetaDocumentList) {
                StringBuilder metaData = new StringBuilder("<META>");

                for (MetaDocumentDataDTO metaDocumentDataDTO : messageMetaDocumentList) {
                    metaData.append(metaDocumentDataDTO.getDocumentData());
                }
                metaData.append("</META>");

                logger.trace("===== META DATA =====");
                logger.trace("{}", metaData.toString());

                messageMetaList.add(metaData.toString());
            }
            arrayOfstring.getString().addAll(messageMetaList);
            itgBillRequest.setREQDOC(
                    arrayOfstring
            );

            try {

                // FIXME : 코드 스코프상 NONSAP 데이타를 여기서 핸들링 하는건 올지 않다. 클래스를 분리하여 처리 할 것
                if ("NONSAP".equals(messageMetaInfo.getErpSystem())) {

                    DynamicDSContextHolder.clearDynamicDSType();
                    DynamicDSContextHolder.setDynamicDSType(messageMetaInfo.getTargetKey());

                    //
                    DTIInterfaceDTO dTIInterfaceDTO = (DTIInterfaceDTO) messageService.getInterfaceDTO(messageMetaInfo);


                    if (!"SENDMAIL".equals(dTIInterfaceDTO.getSignal())) {
                        DTIStatusDTO dtiStatusDTO = new DTIStatusDTO();

                        dtiStatusDTO.setConversationId(dTIInterfaceDTO.getConversationId());
                        dtiStatusDTO.setSupbuyType(dTIInterfaceDTO.getSupbuyType());
                        dtiStatusDTO.setDirection(dTIInterfaceDTO.getDirection());
                        dtiStatusDTO.setTranStatus(MessageStatus.Sending.getCode());

                        dTIStatusService.update(dtiStatusDTO);
                    }

                    DynamicDSContextHolder.clearDynamicDSType();

                }


                /*if (!retryMap.containsKey(messageMetaInfo.getMessageTagId())) {
                    throw new WebServiceException("Failed to create service");
                }*/

                // bean셋팅 안하고 불러오도록 일단...
                neobillClient = this.neobillClient();
                ItgBillACKResponse itgBillACKResponse = neobillClient.serviceRequest(itgBillRequest);

                String returnCode = itgBillACKResponse.getRESULTCODE();
                String returnMessage = itgBillACKResponse.getRESULTMESSAGE();

                retryMap.remove(messageMetaInfo.getMessageTagId());

                logger.info("======= returnCode = {}", returnCode);
                logger.info("======== returnMessage = {}", returnMessage);

                if (SUCCESS.equals(returnCode)) { // 정상일 경우
                    messageMetaInfo.setError(false);

                } else {

                    messageMetaInfo.setError(true);
                    throw new SBMSException(returnCode, returnMessage);
                }
            } catch (WebServiceException wae) {
                logger.error("{}", wae);

                String exceptionMessage = wae.getMessage();

                if (null != exceptionMessage) {

                    for (String knownException : retryExceptionMessage) {

                        if (exceptionMessage.contains(knownException)) {
                            /*
                            activityLogDTO = new ActivityLogDTO();
                            activityLogDTO.setActivity(DOMAIN);
                            activityLogDTO.setActivityLogId(UUID.randomUUID().toString());
                            activityLogDTO.setMessageTagId(messageMetaInfo.getMessageTagId());
                            activityLogDTO.setRegTimestamp(Calendar.getInstance().getTime());
                            activityLogDTO.setActivityStatus(MessageStatus.Warning.getCode());
                            activityLogService.addLogToBackground(activityLogDTO);
                            */

                            ConnectingException.ConnectingError c
                                    = ConnectingException.ConnectingError.MOMENTARY_NETWORK_ERROR;

                            throw new ConnectingException(c.getErrorCode(),
                                    wae.getMessage() == null ? c.getErrorMessage() : wae.getMessage());
                        }
                    }
                }

                throw wae;

            }

            /*
            activityLogDTO = new ActivityLogDTO();
            activityLogDTO.setActivity(DOMAIN);
            activityLogDTO.setActivityLogId(UUID.randomUUID().toString());
            activityLogDTO.setMessageTagId(messageMetaInfo.getMessageTagId());
            activityLogDTO.setRegTimestamp(Calendar.getInstance().getTime());
            activityLogDTO.setActivityStatus(MessageStatus.Complete.getCode());
            activityLogService.addLogToBackground(activityLogDTO);
            */

        } catch (WebApplicationException we) {
            logger.error("WebApplicationException : {}", we);
            throw new RuntimeException(we);
        } catch (ConnectingException connectinException) {

            if (ConnectingException.ConnectingError.MOMENTARY_NETWORK_ERROR.getErrorCode()
                    .equals(connectinException.getCodeId())) {
                // 재시도는 일단.. no
                // retry(messageMetaInfo);
            }

            ce = connectinException;
            // errorLogService.createMessageError(messageMetaInfo.getMessageTagId(), ce);
            logger.info(ce.toString());

        } catch (SBMSException se) {
            logger.error("{}", se);

            ce = new ConnectingException(se.getCodeId(), se.getMessage());
            // errorLogService.createMessageError(messageMetaInfo.getMessageTagId(), ce);

        } catch (Exception e) {
            logger.error("{}", e);

            ConnectingException.ConnectingError uce
                    = ConnectingException.ConnectingError.UNKNOWN_CONNECTING_EXCEPTION;

            ce = new ConnectingException(uce.getErrorCode(), e.getMessage() == null
                    ? uce.getErrorMessage() : e.getMessage());
            // errorLogService.createMessageError(messageMetaInfo.getMessageTagId(), ce);
            logger.info(ce.toString());

        } finally {
            try {

                logger.info("ACK 처리를 위해 큐에 전달");
                logger.info("=== TaxinvoiceConnectingService.connectToSmartBillForRequest() Finally Meta : {}", messageMetaInfo);

                messageMetaInfo.setDestination(Listener.Router);
                messageMetaInfo.setDirection(Direction.Inbound);
                messageMetaInfo.setAck(true);

                if (ce != null) {
                    messageMetaInfo.setError(true);
                }

                jmsSender.sendMessage(messageMetaInfo);

            } catch (JMSException je) {

                ce = new ConnectingException("JMS001", je.getMessage());
                // errorLogService.createMessageError(messageMetaInfo.getMessageTagId(), ce);

            } catch (Exception e) {
                logger.info("{}", e);
                logger.info("시스템 오류로 처리할 수 없음.");
            }

        }

    }

    private IRequest neobillClient() throws MalformedURLException {
        Request ireq = new Request(new URL(billEndpoint), Request.SERVICE);

        IRequest port;
        if (billEndpoint.startsWith("https")) {
            port = ireq.getBasicHttpBindingIRequest1();
        } else {
            port = ireq.getBasicHttpBindingIRequest();
        }

        return port;
    }
}
