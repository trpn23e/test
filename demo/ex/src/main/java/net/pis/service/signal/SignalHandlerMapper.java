/*
 * (c)BOC
 */
package net.pis.service.signal;

import net.pis.common.Adapter;
import net.pis.common.TaxInvoiceSignal;
import net.pis.service.DefaultAsyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * DTI 서비스 맵퍼
 * <p>
 * 자동으로 빈에 등록되며 런타임에 Adapter+Business Signal 의 KEY로 찾아서 소비된다.
 *
 * @author jh,Seo
 */
@Service
public class SignalHandlerMapper {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final Map<String, DefaultAsyncService> handlerMap = new HashMap<>();

    @Autowired
    private Collection<TaxInvoiceSignalHandler> signalHandlers;
    //private Collection<TaxInvoiceSignalHandler> signalHandlers;

    // @Autowired
    // private Collection<UtilitySignalHandler> utilitylHandlers;

    @PostConstruct
    public void initialize() {
        // 이건 좀 아닌듯 하지만..
        // signalHandlers = new ArrayList<TaxInvoiceSignalHandler>();
        // utilitylHandlers = new ArrayList<UtilitySignalHandler>();


        Iterator<TaxInvoiceSignalHandler> it = signalHandlers.iterator();

        while (it.hasNext()) {
            TaxInvoiceSignalHandler signalHandler = it.next();

            logger.info("=== SignalHandlerMapper initialize() =====================================");
            logger.info("=== signalHandler.getClass().getSimpleName() : " + signalHandler.getClass().getSimpleName());
            //logger.info(">> {} <<", signalHandler.getClass().getSimpleName());

            for (TaxInvoiceSignal signal : signalHandler.getProviders()) {
                String key = signalHandler.getAdpater().getAdapterName() + signal.name();

                handlerMap.put(key, signalHandler);
            }
        }

        logger.info("SignalHandlerMapper handlerMap ::::::::::::::::: " + handlerMap.toString());


/*        Iterator<UtilitySignalHandler> itUtil = utilitylHandlers.iterator();

        while (itUtil.hasNext()) {
            UtilitySignalHandler signalHandler = itUtil.next();

            logger.trace(">> {} <<", signalHandler.getClass().getSimpleName());

            for (TaxInvoiceSignal signal : signalHandler.getProviders()) {

                String key = signalHandler.getAdpater().getAdapterName() + signal.name();

                handlerMap.put(key, signalHandler);
            }
        }*/
    }

    /**
     * 인자를 토대로 등록된 서비스 핸들러를 반환한다.
     * <p>
     * Adapter + Signal
     * </p>
     *
     * @param signal
     * @param adapter
     * @return
     */
    public DefaultAsyncService getHandler(TaxInvoiceSignal signal, Adapter adapter) {

        String key = adapter.getAdapterName() + signal.name();

        switch (signal.toMessageType()) {
            case STATUS: {
                key = adapter.getAdapterName() + TaxInvoiceSignal.CHGSTATUS.name();
                break;
            }
        }

        logger.debug("검색용 키 : {}", key);

        return handlerMap.get(key);

    }

    public DefaultAsyncService getHandler(TaxInvoiceSignal signal, String erpSystem) {

        logger.info("=========== 5.SignalHandlerMapper.getHandler() 진입 ==========================");
        logger.info("= Description : erpSystem + signal 을 키로 Class객체를 담은 HandlerMap");
        logger.info("= 에서 가져온다.");
        logger.info("==============================================================================");
        logger.info("=========== erpSystem : " + erpSystem);
        logger.info("=========== signal name : " + signal.name());

        String key = erpSystem + signal.name();

        /*
        switch (signal.toMessageType()) {
            case STATUS: {
                key = erpSystem + TaxInvoiceSignal.CHGSTATUS.name();
                break;
            }
        }
        */

        logger.info("public DefaultAsyncService getHandler(TaxInvoiceSignal signal, String erpSystem) 검색용 키 : {}" +  key);
        logger.info("public DefaultAsyncService getHandler(TaxInvoiceSignal signal, String erpSystem) handlerMap : " + handlerMap.toString());


        return handlerMap.get(key);

    }

}
