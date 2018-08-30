/*
 * (c)BOC
 */
package net.pis.service.signal;

import net.pis.common.Adapter;
import net.pis.common.JmsSender;
import net.pis.common.TaxInvoiceSignal;
import net.pis.service.table.MessageTagService;
import net.pis.exception.AdaptingException;
import net.pis.message.MessageMetaInfo;
import net.pis.service.DefaultAsyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
/**
 *
 * @author jh,Seo
 */
public abstract class UtilitySignalHandler implements DefaultAsyncService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected final String domain = "Adapter";

    protected final String SERVICE_ID = "DTI";

    protected final String SYSTEM_NAME = "SBMS";

    @Autowired
    protected MessageTagService messageTagService;

    @Autowired
    protected JmsSender jmsSender;

    public abstract List<TaxInvoiceSignal> getProviders();

    public abstract Adapter getAdpater();

    public void sendResult(MessageMetaInfo messageMetaInfo) throws AdaptingException {

        logger.debug(">> Request 수신에 의한 결과 송신 [시작]");
        logger.debug(">> 이런 경우는 없음");
        logger.debug(">> Request 수신에 의한 결과 송신 [완료]");

    }

}
