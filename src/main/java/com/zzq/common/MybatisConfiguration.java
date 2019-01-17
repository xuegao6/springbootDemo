package com.zzq.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 配置mybatis
 */
@Configuration
public class MybatisConfiguration {
    @Value("${spring.datasource.type}")
    private Class<? extends DataSource> dataSourceType;

    @Value("${spring.datasource.readSize}")
    private String dataSourceSize;
    @Resource(name = "writeDataSource")
    private DataSource dataSource;
    @Resource(name = "readDataSources")
    private List<DataSource> readDataSources;

    /**
     * 多数据源需要自己设置sqlSessionFactory
     * @return
     * @throws Exception
     */
    @Bean
    @ConditionalOnMissingBean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setDataSource(roundRobinDataSouceProxy());
        sqlSessionFactoryBean.setTypeAliasesPackage("com.demo.model");
        // mybatis的XML的配置
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:mapping/*.xml"));
        sqlSessionFactoryBean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
        return sqlSessionFactoryBean.getObject();
    }
    /**
     * 有多少个数据源就要配置多少个bean
     * 设置数据源路由，通过该类中的determineCurrentLookupKey决定使用哪个数据源
     * @return
     */
    @Bean
    public AbstractRoutingDataSource roundRobinDataSouceProxy() {
        int size = Integer.parseInt(dataSourceSize);
        MyAbstractRoutingDataSource proxy = new MyAbstractRoutingDataSource(size);
        Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
        // 写
        targetDataSources.put(DataSourceType.write.getType(),dataSource);
        //多个读数据库时
        for (int i = 0; i < size; i++) {
            targetDataSources.put(i, readDataSources.get(i));
        }
        proxy.setDefaultTargetDataSource(dataSource);
        proxy.setTargetDataSources(targetDataSources);
        return proxy;
    }
}