/**
 * (c)BOC
 */
package net.pis.service;

import net.pis.common.JmsSender;
import net.pis.common.Listener;
import net.pis.exception.RoutingException;
import net.pis.exception.SBMSException;
import net.pis.message.MessageMetaInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import java.util.Calendar;
import java.util.UUID;
/**
 * Created by achiz on 14. 6. 11.
 */
@Service
//@Scope(scopeName = "prototype")
public class TaxInvoiceRoutingService implements RoutingService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JmsSender jmsSender;

    public void route(MessageMetaInfo messageMetaInfo) throws RoutingException {
        logger.info("=== 10. TaxInvoiceRoutingService.route() 진입 =============");
        logger.info("============ TaxInvoiceRoutingService messageMetaInfo.getDirection() : " + messageMetaInfo.getDirection());
        // createActivityLog(messageMetaInfo.getMessageTagId(), MessageStatus.Progress);

        try {

            switch (messageMetaInfo.getDirection()) {
                case Inbound: {
                    logger.info("============ 라우팅 Inbound 접근 ==================");
                    logger.info("============ SB 처리 완료후 Inbound로 돌아옴 ==================");
                    messageMetaInfo.setDestination(Listener.Adapter);
                    jmsSender.sendMessage(messageMetaInfo);

                    // createActivityLog(messageMetaInfo.getMessageTagId(), MessageStatus.Complete);

                    break;
                }
                case Outbound: {
                    logger.info("============ 라우팅 Outbound 접근 ==================");
                    logger.info("=== 11. Connector로 전달 =============");
                    messageMetaInfo.setDestination(Listener.Connector);
                    jmsSender.sendMessage(messageMetaInfo);

                    // createActivityLog(messageMetaInfo.getMessageTagId(), MessageStatus.Complete);
                    break;
                }
                default: {
                    RoutingException.RoutingError re
                        = RoutingException.RoutingError.NO_DIRECTION;

                    throw new RoutingException(re.getErrorCode(), re.getErrorMessage());

                }
            }

        } catch (JMSException je) {
            logger.error("{}", je);

            //SBMSException.SBMSError se = 
            RoutingException re = new RoutingException(
                SBMSException.SBMSError.JMS_ERROR.getErrorCode(),
                je.getMessage() == null ? SBMSException.SBMSError.JMS_ERROR.getErrorMessage()
                : je.getMessage());

            // errorLogService.createMessageError(messageMetaInfo.getMessageTagId(), re);

            throw re;

        } catch (RoutingException re) {
            logger.error("{}", re);

            // errorLogService.createMessageError(messageMetaInfo.getMessageTagId(), re);

            throw re;

        } catch (Exception e) {
            logger.error("{}", e);

            RoutingException re = new RoutingException(
                RoutingException.RoutingError.UNKNOWN.getErrorCode(),
                e.getMessage() == null ? RoutingException.RoutingError.UNKNOWN.getErrorMessage()
                : e.getMessage());

            // errorLogService.createMessageError(messageMetaInfo.getMessageTagId(), re);

            throw re;

        }

    }
}
