/*
 * (c)BOC
 */
package net.pis.controller;

import net.pis.exception.ConnectingException;
import net.pis.message.MessageMetaInfo;
import net.pis.service.TaxInvoiceConnectingService;
import net.pis.wsserver.IRequest;
import net.pis.wsserver.ItgBillACKResponse;
import net.pis.wsserver.ItgBillRequest;
import net.pis.wsserver.ItgBillResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jws.WebService;
/**
 *
 * @author jh,Seo
 */
@Controller(value = "basicHttpBinding_iRequest")
@WebService(
    serviceName = "Request",
    portName = "BasicHttpBinding_IRequest",
    targetNamespace = "http://tempuri.org/",
    //wsdlLocation = "http://devservice.smartbill.co.kr/NeoBill_Message/Request.svc?wsdl",
    endpointInterface = "net.pis.wsserver.IRequest")
public class TaxInvoiceConnectingController implements ConnectingController, MessageListener, IRequest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TaxInvoiceConnectingService taxInvoiceConnectingService;

    @Override
    public void handleInbound(MessageMetaInfo messageMetaInfo) {

        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void handleOutbound(MessageMetaInfo messageMetaInfo) {

        logger.debug(">> Outbound handling is stared! >>");
        logger.debug(">> traget MetaInfo \n{}\n", messageMetaInfo);

        try {

            taxInvoiceConnectingService.connectToSmartBill(messageMetaInfo);

        } catch (ConnectingException ce) {
            logger.error(ce.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        logger.debug(">> Outbound handling is completed! >>");

    }

    /**
     * JMS 메세지 수신
     * <p>
     * Router에서 넘어온 메세지를 수신하여 처리한다.
     * </p>
     * <p>
     * Router와 다르게 컨넥터에 전달되는 메세지는 전부 스마트빌에 전송하기 위한 처리이다. 즉, 나가는 처리만 담당한다.
     * </p>
     *
     * @param message
     */
    @Override
    public void onMessage(Message message) {

        if (message instanceof ObjectMessage) {

            ObjectMessage om = (ObjectMessage) message;

            try {
                MessageMetaInfo messageMetaInfo = (MessageMetaInfo) om.getObject();

                this.handleOutbound(messageMetaInfo);

            } catch (JMSException e) {
                logger.error(e.getMessage());
            }

        } else {
            logger.debug(message.toString());
        }

    }

    @Override
    public ItgBillACKResponse resultReceive(ItgBillResponse response) {

        logger.info("<< 스마트빌에서 결과 메세지 수신");

        ItgBillACKResponse ackResponse = null;

        try {
            ackResponse = (ItgBillACKResponse) taxInvoiceConnectingService.connectToMessageServer(response);
        } catch (Exception e) {
            logger.error("{}", e);
        }

        return ackResponse;
    }

    @Override
    public ItgBillACKResponse serviceRequest(ItgBillRequest request) {

        logger.info("<< 스마트빌에서 요청 메세지 수신");

        ItgBillACKResponse ack = null;

        try {
            ack = (ItgBillACKResponse) taxInvoiceConnectingService.connectToMessageServer(request);
        } catch (Exception e) {
            logger.error("{}", e);
        }

        return ack;
    }

    @Override
    public String doTest(String p) {
        logger.info("Executing operation doTest");
        try {
            String _return = p;
            return _return;
        } catch (Exception ex) {

            throw new RuntimeException(ex);
        }
    }

}
