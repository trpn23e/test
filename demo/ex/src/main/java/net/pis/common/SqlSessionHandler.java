/*
 * (c)BOC
 */
package net.pis.common;

import net.pis.service.DynamicDSContextHolder;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jh,Seo
 */
@Service
public class SqlSessionHandler {

    /******
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private List<SqlSessionTemplate> sessionBeans;

    private final Map<String, SqlSessionTemplate> sessionBeanMap = new HashMap<>();

    //@Autowired
    @PostConstruct
    void initialize() {

        logger.info(" >>> create sessionBeanMap >>>");
        for (SqlSessionTemplate bean : sessionBeans) {

            String environmentId = bean.getConfiguration().getEnvironment().getId();

            logger.info("Datasource env id = {}", environmentId);

            if (!"SqlSessionFactoryBean".equals(environmentId)) {

                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;

                try {

                    conn = bean.getSqlSessionFactory().openSession().getConnection();
                    stmt = conn.createStatement();
                    rs = stmt.executeQuery("SELECT COUNT(*) as cnt FROM XXSB_DTI_INTERFACE WHERE conversation_id = '*'");

                    while (rs.next()) {
                        logger.trace("## {} ##", rs.getInt(1));
                    }

                    rs.close();
                    stmt.close();
                    conn.close();

                } catch (Exception e) {
                    logger.error("JDBC session error : {}", e);
                } finally {
                    try {
                        if (rs != null) {
                            rs.close();
                        }
                        if (stmt != null) {
                            stmt.close();
                        }
                        if (conn != null) {
                            conn.close();
                        }
                    } catch (Exception e) {

                        logger.error("ignore : {}", e);

                    }
                }

                logger.error("초기화를 종료합니다.");
                sessionBeanMap.put(environmentId, bean);
            }
        }
        logger.info(" <<< create sessionBeanMap <<<");
        logger.info(" SqlSessionTemplates have been set.");

    }

    public SqlSessionTemplate getSqlSessionTemplate() {

        return sessionBeanMap.get(DynamicDSContextHolder.getDynamicDSType());

    }

    public Iterator<String> iterator() {
        return (Iterator<String>) sessionBeanMap.keySet().iterator();
    }

    ******/
}
