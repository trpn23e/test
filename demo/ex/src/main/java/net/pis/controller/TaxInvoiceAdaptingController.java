/*
 * (c)BOC
 */
package net.pis.controller;

import net.pis.exception.SBMSException;
import net.pis.message.MessageMetaInfo;
import net.pis.service.AdaptingService;
import net.pis.service.DynamicDSContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import java.util.Iterator;
import java.util.Map;
/**
 * 어댑팅 컨트롤러
 *
 * @author jh,Seo
 */
@Controller
@ManagedResource
public class TaxInvoiceAdaptingController implements AdaptingController, MessageListener {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AdaptingService adaptingService;

    /**
     * 인바운드 데이타 컨트롤러
     *
     * @param messageMetaInfo
     */
    // @RequestMapping(value = "/inb", method = RequestMethod.GET)
    public void handleInbound(MessageMetaInfo messageMetaInfo) {

        logger.info("==== 2. TaxInvoiceAdaptingController.handleInbound() 진입 ========");
        logger.info("==== TaxInvoiceAdaptingController << Inbound  handling is stared! <<");
        logger.info("==== TaxInvoiceAdaptingController << traget MetaInfo \n{}\n", messageMetaInfo);

        long ctm = System.currentTimeMillis();

        try {

            adaptingService.adapt(messageMetaInfo);

        } catch (Exception ae) {
            logger.error(ae.getMessage());
        } finally {

            DynamicDSContextHolder.clearDynamicDSType();
        }

        logger.trace("\n====================\nMSG :[{}], ACK : [{}], deal: [{}]\n====================",
                messageMetaInfo.getMessageId(), messageMetaInfo.isAck(), System.currentTimeMillis() - ctm);

        logger.info("==== TaxInvoiceAdaptingController << Inbound handling is completed! <<");

    }


    /**
     * 아웃바운드 데이타 컨트롤러
     *
     * @param messageMetaInfo
     */
    // @RequestMapping(value = "/outb", method = RequestMethod.GET)
    public void handleOutbound(MessageMetaInfo messageMetaInfo) throws SBMSException {

        logger.info("==== 2. TaxInvoiceAdaptingController.handleOutbound() 진입 ========");
        logger.info("= Description : PollingTaskService를 통해 Interface 테이블을 조회");
        logger.info("= 해서 얻은 결과를 가지고 처리할때는 OutBound 처리가 된다.");
        logger.info("= (SmartBill로 나가는 데이터 처리가 이뤄지는 첫 단계임");
        logger.info("===================================================================");
        logger.info("===== TaxInvoiceAdaptingController >> Outbound handling is stared! >>");
        logger.info("===== TaxInvoiceAdaptingController >> target MetaInfo \n{}\n", messageMetaInfo);

        adaptingService.adapt(messageMetaInfo);

        logger.info("===== TaxInvoiceAdaptingController >> Outbound handling is completed! >>");

    }

    /**
     * JMS Listener
     *
     * @param message
     */
    @Override
    public void onMessage(Message message
    ) {
        logger.info("TaxInvoiceAdaptingController onMessage =============================================== ");
        if (message instanceof ObjectMessage) {

            ObjectMessage om = (ObjectMessage) message;

            try {
                logger.info("==== SB에서 완료 후 Router -> Adapter로 오게될 경우임 JMS onMessage() ===");
                logger.info("==== TaxInvoiceAdaptingController Inbound 진입 ===");
                MessageMetaInfo messageMetaInfo = (MessageMetaInfo) om.getObject();
                this.handleInbound(messageMetaInfo);

            } catch (Exception e) {
                logger.error("{}", e);
            }

        } else {
            logger.info(message.toString());
        }

    }
}
