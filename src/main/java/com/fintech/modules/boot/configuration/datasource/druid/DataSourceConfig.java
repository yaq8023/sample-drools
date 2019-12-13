package com.fintech.modules.boot.configuration.datasource.druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * Description
 * <br>  数据源处理
 *
 * @author chendongdong
 * @date 2019/12/11
 **/
@Configuration
@AutoConfigureBefore(DataSourceAutoConfiguration.class)
@MapperScan(basePackages = DataSourceConfig.PACKAGE, sqlSessionFactoryRef = "sqlSessionFactory")
public class DataSourceConfig {

    private static final Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

    /**
     * 精确到 master 目录，以便跟其他数据源隔离
     */
    protected static final String PACKAGE = "com.fintech.modules.**.dao";
    protected static final String MAPPER_LOCATION = "com/fintech/modules/**/dao/*Mapper.xml";


    @Bean(name = "masterDataSource", destroyMethod = "close", initMethod = "init")
    @Primary
    @ConfigurationProperties("spring.datasource.druid.primary")
    public DruidDataSource masterDataSource() {
        logger.info("---------------------------masterDataSource配置成功------------------------------------------");
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "transactionManager")
    @Primary
    public DataSourceTransactionManager masterTransactionManager() {
        return new DataSourceTransactionManager(masterDataSource());
    }

    @Bean(name = "sqlSessionFactory")
    @Primary
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("masterDataSource") DataSource masterDataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(masterDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION));
        //解决问题:mysql设置字段为null值,查询出来属性消失
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setCallSettersOnNulls(true);
        sessionFactory.setConfiguration(configuration);
        return sessionFactory.getObject();

    }

}
