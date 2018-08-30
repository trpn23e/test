/*
 * (c)BOC
 */
package net.pis.config.cxf;

import net.pis.controller.TaxInvoiceConnectingController;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.feature.Feature;
import org.apache.cxf.feature.LoggingFeature;
import org.apache.cxf.jaxws.EndpointImpl;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;
import java.security.Security;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * CXF 웹서비스 설정 클래스
 *
 * @author jh,Seo
 */
@Configuration
public class WsConfiguration {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final String wsdlLocation = "/WEB-INF/IRequest.wsdl";

    @Autowired
    private TaxInvoiceConnectingController taxInvoiceConnectingController;

    @Bean
    public SpringBus cxf() {
        return new SpringBus();
    }

    static {
        Security.addProvider(new BouncyCastleProvider());

        //System.setProperty("com.sun.xml.ws.transport.http.client.HttpTransportPipe.dump", "true");
        //System.setProperty("javax.net.debug", "ssl");
        /*Path path = Paths.get(System.getProperty("SBMS_HOME"), "keystore", "keystore.jks");
        System.setProperty("javax.net.ssl.keyStore", path.toString());
        System.setProperty("javax.net.ssl.keyStoreType", "jks");
        System.setProperty("javax.net.ssl.keyStorePassword", "testtest");
        System.setProperty("trust_all_cert", "true");
        System.setProperty("com.sun.net.ssl.checkRevocation", "false");*/
    }

    /**
     * 웹서비스 서버 빈
     *
     * @return
     */
    @Bean
    public Endpoint iRequest() {

        EndpointImpl endpoint = new EndpointImpl(cxf(), taxInvoiceConnectingController);

        endpoint.publish("/iRequest");
        endpoint.setWsdlLocation(wsdlLocation);

        List<Feature> featureList = new ArrayList<>();
        featureList.add(new LoggingFeature());

        Map<String, Object> properties = new HashMap<>();
        properties.put("faultStackTraceEnabled", true);
        properties.put("exceptionMessageCauseEnabled", true);
        properties.put("schema-validation-enabled", true);
        properties.put("mtom-enabled", true);

        endpoint.setFeatures(featureList);
        endpoint.setProperties(properties);

        return endpoint;
    }
}
