/*
 * (c)BOC
 */
package net.pis.config.cache;

import net.pis.wsserver.IRequest;
import net.pis.wsserver.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
/**
 * 캐시
 *
 * @author jh,Seo
 */
@Configuration
public class CacheConfiguration {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // @Autowired
    // private CertificateService certificateService;

    @Value("${sbms.bill.endpoint}")
    private String billEndpoint;

    /**
     * History and ActivityLog
     *
     * @return
     */
    @Bean(name = "logThreadPoolTaskExecutor")
    public Executor logThreadPoolTaskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(10);
        taskExecutor.setMaxPoolSize(20);
        taskExecutor.setQueueCapacity(100);

        return taskExecutor;
    }

    /**
     * 자동수신 승인 쓰레드풀
     *
     * @return
     */
    @Bean(name = "autoApproveThreadPoolTaskExecutor")
    public TaskExecutor autoApproveThreadPoolTaskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(5);
        taskExecutor.setMaxPoolSize(10);
        taskExecutor.setQueueCapacity(1000);
        taskExecutor.setThreadNamePrefix("autoApprove-");

        return taskExecutor;
    }

    @Bean(name = "retryMap")
    public Map<String, Integer> retryMap() {
        Map<String, Integer> retryMap = new ConcurrentHashMap<>();
        return retryMap;
    }


    /*
    @Bean
    public IRequest neobillClient() throws MalformedURLException {
        Request ireq = new Request(new URL(billEndpoint), Request.SERVICE);

        IRequest port;
        if (billEndpoint.startsWith("https")) {
            port = ireq.getBasicHttpBindingIRequest1();
        } else {
            port = ireq.getBasicHttpBindingIRequest();
        }

        return port;
    }
    */

}
