/*
 * (c)BOC
 */
package net.pis.controller;

import net.pis.exception.RoutingException;
import net.pis.exception.SBMSException;
import net.pis.message.MessageMetaInfo;
import net.pis.service.RoutingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
/**
 * 라우팅 컨트롤러
 * <p>
 * 현재는 메세지 브릿지 이외의 역할을 하지 않는다....</p>
 *
 * @author jh,Seo
 */
@Controller
public class TaxInvoiceRoutingController implements RoutingController, MessageListener {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RoutingService routingService;

    @Override
    public void handleInbound(MessageMetaInfo messageMetaInfo) throws RoutingException {

        logger.info("===== TaxInvoiceRoutingController << Inbound  handling is stared! <<");
        logger.info("===== TaxInvoiceRoutingController << traget MetaInfo \n{}\n", messageMetaInfo);
        routingService.route(messageMetaInfo);
        logger.info("===== TaxInvoiceRoutingController << Inbound handling is completed! <<");

    }

    @Override
    public void handleOutbound(MessageMetaInfo messageMetaInfo) throws RoutingException {
        logger.info("=== 9. TaxInvoiceRoutingController.handleOutbound() 진입 =============");
        logger.info("===== TaxInvoiceRoutingController >> Outbound handling is stared! >>");
        logger.info("===== TaxInvoiceRoutingController >> traget MetaInfo \n{}\n", messageMetaInfo);
        routingService.route(messageMetaInfo);
        logger.info("===== TaxInvoiceRoutingController >> Outbound handling is completed! >>");

    }

    @Override
    public void onMessage(Message message) {

        logger.info("=== 8. TaxInvoiceRoutingController.onMessage() 진입 =================");
        logger.info("= Description : JmsConfiguration 클래스에서");
        logger.info("= listenQueueRouter()로 ActiveMQ에 Router명을 지정해준대로");
        logger.info("= 그리고 MessageListenerAdapter.setDefaultListenerMethod()에");
        logger.info("= 지정한 기본 메소드이름 onMessage를 통해 MessageListener를 implement한");
        logger.info("= 클래스로 메시지 데이터가 전달되게 된다.");
        logger.info("=====================================================================");
        logger.info("===== TaxInvoiceRoutingController <** 라우팅 시작 **>");

        if (message instanceof ObjectMessage) {

            ObjectMessage om = (ObjectMessage) message;

            try {
                MessageMetaInfo messageMetaInfo = (MessageMetaInfo) om.getObject();

                switch (messageMetaInfo.getDirection()) {
                    case Inbound: {
                        this.handleInbound(messageMetaInfo);
                        break;
                    }
                    case Outbound: {
                        this.handleOutbound(messageMetaInfo);
                        break;
                    }
                    default: {
                        logger.warn("NOT FOUND");
                    }
                }

            } catch (JMSException | SBMSException e) {
                logger.error(e.getMessage());
            }

        } else {
            logger.info(message.toString());
        }

        logger.info("===== TaxInvoiceRoutingController <** 라우팅 종료 **>");

    }
}
