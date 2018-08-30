/*
 * (c)BOC
 */
package net.pis.service.signal;

import net.pis.common.*;
import net.pis.dto.MessageTagDTO;
import net.pis.exception.SBMSException;
import net.pis.service.table.MessageTagService;
import net.pis.exception.AdaptingException;
import net.pis.message.MessageMetaInfo;
import net.pis.service.DefaultAsyncService;
import net.pis.service.table.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.JMSException;
import java.nio.charset.Charset;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

/**
 * 전자 세금계산서 비즈니스 시그널 핸들러
 *
 * @author jh,Seo
 */
public abstract class TaxInvoiceSignalHandler implements DefaultAsyncService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected final String domain = "Adapter";

    protected final String SERVICE_ID = "DTI";

    protected final String SYSTEM_NAME = "SBMS";

    protected final String SUCCESS_CODE = "30000";

    /**
     * 매입
     */
    protected final String BUYER = "AP";
    /**
     * 매출
     */
    protected final String SUPPLIER = "AR";

    /**
     * 정방향
     */
    protected final String FORWARD = "2";
    /**
     * 역방향
     */
    protected final String REVERSE = "1";

    @Autowired
    public MessageService messageService;

    @Autowired
    public MessageTagService messageTagService;

    @Autowired
    public JmsSender jmsSender;

    public abstract List<TaxInvoiceSignal> getProviders();

    public abstract Adapter getAdpater();

    /****
    @Override
    public void recieveAckAboutRequest(MessageMetaInfo messageMetaInfo) throws AdaptingException {
        logger.info("=== TaxInvoiceSignalHandler.recieveAckAboutRequest() ==================== ");
    }

    @Override
    public void receiveResult(MessageMetaInfo messageMetaInfo) throws AdaptingException {
        logger.info("=== TaxInvoiceSignalHandler.receiveResult() ==================== ");
    }

    @Override
    public void receiveRequest(MessageMetaInfo messageMetaInfo) throws AdaptingException {
        logger.info("=== TaxInvoiceSignalHandler.receiveRequest() ==================== ");
    }

    @Override
    public void recieveAckAboutResult(MessageMetaInfo messageMetaInfo) throws AdaptingException {
        logger.info("=== TaxInvoiceSignalHandler.recieveAckAboutResult() ==================== ");
    }


    public void sendResult(MessageMetaInfo messageMetaInfo) throws AdaptingException {
        logger.info("=== TaxInvoiceSignalHandler.sendResult() ==================== ");
    }
    */

    /**
     * recieveAckAboutRequest 에 대한 후처리
     *
     * @param messageMetaInfo
     * @param messageStatus
     * @param transferState
     * @throws AdaptingException
     */
    public abstract void afterRecieveAckAboutRequest(MessageMetaInfo messageMetaInfo,
         MessageStatus messageStatus, MessageStatus transferState) throws AdaptingException;

    /**
     * receiveResult 에 대한 후처리
     *
     * @param messageMetaInfo
     * @param messageStatus
     * @param transferState
     * @throws AdaptingException
     */
    public abstract void afterReceiveResult(MessageMetaInfo messageMetaInfo,
        MessageStatus messageStatus, MessageStatus transferState) throws AdaptingException;

    /**
     * SBMS의 메세지 요청에 대한 ACK 처리
     *
     * @param messageMetaInfo
     */
    @Override
    public void recieveAckAboutRequest(MessageMetaInfo messageMetaInfo) throws AdaptingException {
        logger.info("=== TaxInvoiceSignalHandler.recieveAckAboutRequest() ==================== ");
        logger.info("<< Request에 대한 ACK 수신 [시작]");

        MessageStatus messageStatus;
        MessageStatus tranStatus;
        try {

            if (messageMetaInfo.isError()) {
                messageStatus = MessageStatus.Error;
                tranStatus = MessageStatus.Error;
            } else {
                messageStatus = MessageStatus.Complete;
                tranStatus = MessageStatus.Sent;
            }

            // 테이블 없음 막자...
            // updateMessageTagStatus(messageStatus, messageMetaInfo.getMessageTagId());
            afterRecieveAckAboutRequest(messageMetaInfo, messageStatus, tranStatus);

        } catch (AdaptingException ae) {
            logger.info(ae.getMessage());

            // errorLogService.createMessageError(messageMetaInfo.getMessageTagId(), ae);
            afterRecieveAckAboutRequest(messageMetaInfo,
                    MessageStatus.Error, MessageStatus.Error);

            throw ae;
        } catch (Exception e) {
            logger.error(e.getMessage());

            AdaptingException.AdaptingError ae = AdaptingException.AdaptingError.ACK_EXCEPTION;

            AdaptingException ex = new AdaptingException(ae.getErrorCode(),
                    e.getMessage() == null ? ae.getErrorMessage() : e.getMessage());

            // errorLogService.createMessageError(messageMetaInfo.getMessageTagId(), ex);
            afterRecieveAckAboutRequest(messageMetaInfo,
                    MessageStatus.Error, MessageStatus.Error);

            throw ex;
        }

        logger.info("<< Request에 대한 ACK 수신 [완료]");
    }

    /**
     * SBMS의 메세지 요청에 대한 RESULT 처리
     *
     * @param messageMetaInfo
     */
    @Override
    public void receiveResult(MessageMetaInfo messageMetaInfo) throws AdaptingException {
        logger.info("=== TaxInvoiceSignalHandler.receiveResult() ==================== ");
        logger.info("<< Request에 대한 결과 수신 [시작]");

        try {

            MessageStatus messageStatus;
            if (messageMetaInfo.isError()) {
                messageStatus = MessageStatus.Error;
            } else {
                messageStatus = MessageStatus.Complete;
            }

            updateMessageTagStatus(messageStatus, messageMetaInfo.getMessageTagId());
            afterReceiveResult(messageMetaInfo, messageStatus, messageStatus);

        } catch (AdaptingException ae) {

            logger.error(ae.getMessage());

            // errorLogService.createMessageError(messageMetaInfo.getMessageTagId(), ae);
            afterReceiveResult(messageMetaInfo,
                    MessageStatus.Error, MessageStatus.Error);

            throw ae;

        } catch (Exception e) {
            logger.error(e.getMessage());

            AdaptingException.AdaptingError ae = AdaptingException.AdaptingError.RESULT_EXCEPTION;

            AdaptingException ex = new AdaptingException(ae.getErrorCode(),
                    e.getMessage() == null ? ae.getErrorMessage() : e.getMessage());

            // errorLogService.createMessageError(messageMetaInfo.getMessageTagId(), ex);

            afterReceiveResult(messageMetaInfo,
                    MessageStatus.Error, MessageStatus.Error);

            throw ex;
        }

        logger.info("<< Request에 대한 결과 수신 [완료]");

        logger.info("=== 국세청 요청처리 ntsRequestService.ntsRequest() ==================== ");
        // ntsRequestService.ntsRequest(messageMetaInfo);

    }

    @Override
    public void sendResult(MessageMetaInfo messageMetaInfo) throws AdaptingException {
        logger.info("=== TaxInvoiceSignalHandler.sendResult() ==================== ");
        logger.info(">> Request 수신에 의한 결과 송신 [시작]");

        String uuid = UUID.randomUUID().toString();

        MessageTagDTO messageTagDTO = messageTagService.getMessageTagDTO(messageMetaInfo.getMessageTagId());

        try {

            MessageTagDTO newMessageTagDTO = new MessageTagDTO();
            newMessageTagDTO.setMessageTagId(uuid);
            newMessageTagDTO.setMessageId(messageMetaInfo.getMessageId());
            newMessageTagDTO.setSignal(messageTagDTO.getSignal() + "_FINISH");
            newMessageTagDTO.setServiceId(SERVICE_ID);
            newMessageTagDTO.setConversationId(messageTagDTO.getConversationId());
            newMessageTagDTO.setSendRegNo(messageTagDTO.getReceiveRegNo());
            newMessageTagDTO.setReceiveRegNo(messageTagDTO.getSendRegNo());
            newMessageTagDTO.setDirection(Direction.Outbound.getCode());
            newMessageTagDTO.setFromSystem(messageMetaInfo.getErpSystem());
            newMessageTagDTO.setMessageStatus(MessageStatus.Progress.getCode());
            newMessageTagDTO.setSenderSystemId(messageTagDTO.getSenderSystemId());

            messageTagService.create(newMessageTagDTO);

            MessageMetaInfo newMessageMetaInfo = new MessageMetaInfo();

            //BeanUtils.copyProperties(messageMetaInfo, newMessageMetaInfo);
            newMessageMetaInfo.setMessageTagId(uuid);
            newMessageMetaInfo.setMessageId(messageMetaInfo.getMessageId());
            newMessageMetaInfo.setDestination(Listener.Router);
            newMessageMetaInfo.setDirection(Direction.Outbound);
            newMessageMetaInfo.setAck(false);
            newMessageMetaInfo.setError(messageMetaInfo.isError());

            newMessageMetaInfo.setErpSystem(messageMetaInfo.getErpSystem());
            newMessageMetaInfo.setTargetKey(messageMetaInfo.getTargetKey());
            newMessageMetaInfo.setClientCode(messageMetaInfo.getClientCode());
            newMessageMetaInfo.setCompanyCode(messageMetaInfo.getCompanyCode());

            logger.info("_FINISH 를 보낼때의 메타 데이타 : {}", newMessageMetaInfo);

            jmsSender.sendMessage(newMessageMetaInfo);

        } catch (JMSException je) {
            logger.error("{}", je);

        } catch (Exception e) {
            logger.error("{}", e);
        }

        logger.info(">> Request 수신에 의한 결과 송신 [완료]");

    }

    @Override
    public void recieveAckAboutResult(MessageMetaInfo messageMetaInfo) throws AdaptingException {
        logger.info("=== TaxInvoiceSignalHandler.recieveAckAboutResult() ==================== ");
        logger.debug("<< Result 송신에 의한 ACK 수신 [시작]");

        try {
            MessageStatus messageStatus;
            MessageStatus tranStatus;

            if (messageMetaInfo.isError()) {

                messageStatus = MessageStatus.Error;
                tranStatus = MessageStatus.Error;

            } else {
                messageStatus = MessageStatus.Complete;
                tranStatus = MessageStatus.Complete;

            }

            logger.info("=== TaxInvoiceSignalHandler.updateMessageTagStatus() ==================== ");
            updateMessageTagStatus(messageStatus, messageMetaInfo.getMessageTagId());
            logger.info("=== TaxInvoiceSignalHandler.afterRecieveAckAboutResult() ==================== ");
            afterRecieveAckAboutResult(messageMetaInfo, messageStatus, tranStatus);

        } catch (AdaptingException ae) {
            logger.info(ae.getMessage());

            // errorLogService.createMessageError(messageMetaInfo.getMessageTagId(), ae);

            logger.info(ae.getMessage());
            logger.info("에러면.. === TaxInvoiceSignalHandler.afterRecieveAckAboutResult() ====================");
            afterRecieveAckAboutResult(messageMetaInfo,
                    MessageStatus.Error, MessageStatus.Error);

            throw ae;
        } catch (Exception e) {
            logger.info(e.getMessage());

            AdaptingException.AdaptingError ae = AdaptingException.AdaptingError.RESULT_EXCEPTION;

            AdaptingException ex = new AdaptingException(ae.getErrorCode(),
                    e.getMessage() == null ? ae.getErrorMessage() : e.getMessage());

            // errorLogService.createMessageError(messageMetaInfo.getMessageTagId(), ex);
            logger.info("에러면.. === TaxInvoiceSignalHandler.afterRecieveAckAboutResult() ====================");
            afterRecieveAckAboutResult(messageMetaInfo,
                    MessageStatus.Error, MessageStatus.Error);

            throw ex;

        }
        logger.debug("<< Result 송신에 의한 ACK 수신 [완료]");
    }

    /**
     * recieveAckAboutResult 에 대한 후처리
     *
     * @param messageMetaInfo
     * @param messageStatus
     * @param transferState
     * @throws AdaptingException
     */
    public abstract void afterRecieveAckAboutResult(MessageMetaInfo messageMetaInfo,
                                                    MessageStatus messageStatus, MessageStatus transferState) throws AdaptingException;


    @Deprecated
    protected String parseErrorMessage3(String message, int length) {
        String errorMessage = "";
        if (null != message) {

            byte[] bytes = message.getBytes(Charset.defaultCharset());
            if (bytes != null && bytes.length > length) {
                errorMessage = new String(bytes, 0, length);
            } else {
                errorMessage = message;
            }
        }

        return errorMessage;
    }

    protected void updateMessageTagStatus(MessageStatus messageStatus,
                                          String messageTagId) throws AdaptingException {

        try {
            MessageTagDTO mtd = new MessageTagDTO();
            mtd.setMessageTagId(messageTagId);
            mtd.setMessageStatus(messageStatus.getCode());
            mtd.setCurrTimestamp(Calendar.getInstance().getTime());

            messageTagService.update(mtd);

        } catch (Exception e) {

            SBMSException se = new SBMSException(
                    AdaptingException.AdaptingError.ACK_TAG_EXCEPTION.getErrorCode(), e);

            throw new AdaptingException(se);
        }

    }
}