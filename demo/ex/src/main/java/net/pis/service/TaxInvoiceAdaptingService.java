/*
 * (c)BOC
 */
package net.pis.service;

import net.pis.common.MessageType;
import net.pis.common.TaxInvoiceSignal;
import net.pis.dto.RoutingInfoDTO;
import net.pis.exception.AdaptingException;
import net.pis.exception.SBMSException;
import net.pis.message.MessageMetaInfo;
import net.pis.service.signal.SignalHandlerMapper;
import net.pis.service.table.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaxInvoiceAdaptingService implements AdaptingService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final String domain = "Adapter";

    @Autowired
    private RoutingNavigator routingNavigator;

    @Autowired
    private MessageService messageService;

    @Autowired
    private SignalHandlerMapper signalHandlerMapper;

    /**
     * 인바운드 데이타 처리
     *
     * @param messageMetaInfo
     */
    private void adaptInbound(MessageMetaInfo messageMetaInfo) throws AdaptingException {
        logger.info("TaxInvoiceAdaptingService adaptInbound =============================================== ");
        // writeActivityLog(messageMetaInfo.getMessageTagId(), MessageStatus.Progress);
        try {
            TaxInvoiceSignal signal = (TaxInvoiceSignal) messageService.getSignal(messageMetaInfo);
            MessageType messageType = signal.toMessageType();

            DefaultAsyncService asyncService
                    = signalHandlerMapper.getHandler(signal, messageMetaInfo.getErpSystem());

            logger.info("messageType :::: " + messageType.toString());
            logger.info("messageMetaInfo.isAck() : " + messageMetaInfo.isAck());

            if (messageMetaInfo.isAck()) {
                if (!MessageType.RESULT.equals(messageType)) {
                    logger.info("====== isAck true, MessageType.RESULT(ARISSUE가 아닐경우 -  RESULT처리요청) =====");
                    logger.info("==== asyncService.recieveAckAboutRequest 로 가자 ===");
                    asyncService.recieveAckAboutRequest(messageMetaInfo);
                } else if (MessageType.RESULT.equals(messageType)) {
                    logger.info("====== isAck true, MessageType.RESULT(ARISSUE일 경우 -  RESULT처리요청) =====");
                    logger.info("==== asyncService.recieveAckAboutResult 로 가자 ===");
                    asyncService.recieveAckAboutResult(messageMetaInfo);
                }
            } else if (!MessageType.RESULT.equals(messageType)) {
                logger.info("====== isAck false, MessageType.RESULT(ARISSUE가 아닐경우 - REQUEST처리요청) =====");
                try {
                    logger.info("==== asyncService.receiveRequest 로 가자 ===");
                    asyncService.receiveRequest(messageMetaInfo);
                } catch (SBMSException se) {

                    messageMetaInfo.setError(true);
                    logger.error(se.getMessage());

                    throw se;

                } finally {
                    logger.info("=== MessageType.RESULT(ARISSUE_FINISH) 가 아니고 isAck가 false");
                    logger.info("=== asyncService.sendResult 로 가자 ===");
                    asyncService.sendResult(messageMetaInfo);
                }

            } else if (MessageType.RESULT.equals(messageType)) {
                logger.info("====== isAck false, MessageType.RESULT(ARISSUE일 경우) - RESULT처리 요청 =====");
                logger.info("==== asyncService.receiveResult 로 가자 ===");
                asyncService.receiveResult(messageMetaInfo);
            }

            // writeActivityLog(messageMetaInfo.getMessageTagId(), MessageStatus.Complete);

        } catch (SBMSException ae) {
            logger.error(ae.getMessage());

            // errorLogService.createMessageError(messageMetaInfo.getMessageTagId(), ae);

            throw new AdaptingException(ae);

        }

    }

    private void adaptOutbound(MessageMetaInfo messageMetaInfo) throws AdaptingException {

        logger.info("===== 3.TaxInvoiceAdaptingService.adaptOutBound() =====");
        logger.info("= Description : 처리 Handler 서비스 접근을 위해 진입 =");
        logger.info("=======================================================");
        logger.info("===== TaxInvoiceAdaptingService adaptOutbound =============================================== ");

        TaxInvoiceSignal signal = (TaxInvoiceSignal) messageService.getSignal(messageMetaInfo);

        try {

            logger.info("==== signalHandlerMapper.getHandler : " + signalHandlerMapper.getHandler(signal, messageMetaInfo.getErpSystem()));

            signalHandlerMapper.getHandler(signal, messageMetaInfo.getErpSystem())
                .sendRequest(messageMetaInfo);

            logger.info("============== adaptOutbound ====================");
        } catch (SBMSException se) {
            logger.error("{}", se);

            if ("SAP".equals(messageMetaInfo.getErpSystem())) {
                AdaptingException ae = new AdaptingException(se);

                throw ae;
            }

        }
    }

    @Override
    public void adapt(MessageMetaInfo messageMetaInfo) throws AdaptingException {
        logger.info("START ADAPTING - TaxInvoiceAdaptingService.adapt() ===== ");

        if (null != messageMetaInfo.getDirection()) {
            logger.info("Direction :::::: " + messageMetaInfo.getDirection());
            switch (messageMetaInfo.getDirection()) {
                case Outbound: {
                    logger.info("=== TaxInvoiceAdaptingService Outbound Case 진입 ===");
                    this.adaptOutbound(messageMetaInfo);
                    break;
                }
                case Inbound: {
                    logger.info("=== TaxInvoiceAdaptingService Inbound Case 진입 ===");
                    // RoutingNavigator 관련 테이블들이 없으므로 nonsap만 기준으로 돌려ㅈ보자

                    messageMetaInfo.setErpSystem("NONSAP");
                    messageMetaInfo.setTargetKey("nonsap");

                    DynamicDSContextHolder.setDynamicDSType("nonsap");
                    this.adaptInbound(messageMetaInfo);
                    DynamicDSContextHolder.clearDynamicDSType();

                    /***
                    final RoutingInfoDTO routingInfoDTO = routingNavigator
                            .getRoutingPathInfo(messageMetaInfo);

                    logger.info("getAdapter() :::::::::::::::::::::  " + routingInfoDTO.getAdapter());

                    // 조건 추가 routing 테이블 없어서 회피하도록 함 NONSAP으로 가도록
                    if (routingInfoDTO == null ||
                            routingInfoDTO.getAdapter() == null ||
                            "".equals(routingInfoDTO.getAdapter())) {

                        messageMetaInfo.setErpSystem("NONSAP");
                        messageMetaInfo.setTargetKey(routingInfoDTO.getTargetKey());

                        // DynamicDSContextHolder.setDynamicDSType(routingInfoDTO.getTargetKey());

                        this.adaptInbound(messageMetaInfo);
                        DynamicDSContextHolder.clearDynamicDSType();

                    } else {
                        if ("SAP".equals(routingInfoDTO.getAdapter())) {

                            messageMetaInfo.setErpSystem("SAP");
                            messageMetaInfo.setClientCode(routingInfoDTO.getClient());
                            messageMetaInfo.setCompanyCode(routingInfoDTO.getCompany());
                            messageMetaInfo.setTargetKey(routingInfoDTO.getTargetKey());

                        } else if ("NONSAP".equals(routingInfoDTO.getAdapter())) {

                            messageMetaInfo.setErpSystem("NONSAP");
                            messageMetaInfo.setTargetKey(routingInfoDTO.getTargetKey());

                            DynamicDSContextHolder.setDynamicDSType(routingInfoDTO.getTargetKey());

                        } else if ("SBMS".equals(routingInfoDTO.getAdapter())) {

                            messageMetaInfo.setErpSystem("SBMS");
                        }

                        this.adaptInbound(messageMetaInfo);
                        DynamicDSContextHolder.clearDynamicDSType();

                        break;
                    }
                     **/
                }
            }
        }
        logger.info("==== END ADAPTING =====");

    }
}
