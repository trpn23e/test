package net.pis.config.mybatis;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@PropertySource({ "classpath:application.properties" })
@MapperScan(value="net.pis.dao", sqlSessionFactoryRef="db2SqlSessionFactory")
//@MapperScan(value="net.pis.dao")
public class SqlSessionFactorySecConfig {

    @Autowired
    private Environment env;

    @Autowired
    private ApplicationContext applicationContext;

    // 트랜잭션 Manager를 사용하려면 이거 쓰면 안됨
    // (Hikari CP)
    // @Bean(name = "db1DataSource", destroyMethod = "close")
    // @ConfigurationProperties(prefix = "spring.db1.datasource")
    // public DataSource db1DataSource() {
    //     return DataSourceBuilder.create().build();
    // }

    // 트랜잭션 Manager 사용시 사용
    // @Bean(name = "db2DataSource", destroyMethod = "close")
    @Bean(name = "db2DataSource", destroyMethod = "postDeregister")
    public DataSource db1DataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.db2.datasource.driverClassName"));
        dataSource.setUrl(env.getProperty("spring.db2.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.db2.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.db2.datasource.password"));

        return dataSource;
    }

    @Bean(name = "db2TransactionManager")
    public DataSourceTransactionManager transactionManager() throws Exception {
        return new DataSourceTransactionManager(db1DataSource());
    }

    @Bean(name = "db2SqlSessionFactory")
    // public SqlSessionFactory db1SqlSessionFactory(@Qualifier("db1DataSource") DataSource db1DataSource, ApplicationContext applicationContext) throws Exception {
    public SqlSessionFactory db1SqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(this.db1DataSource());
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:net/pis/mapper/dti/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "db2SqlSessionTemplate", destroyMethod = "clearCache")
    // public SqlSessionTemplate db1SqlSessionTemplate(SqlSessionFactory db1SqlSessionFactory) throws Exception {
    public SqlSessionTemplate db1SqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(this.db1SqlSessionFactory());
    }
}
