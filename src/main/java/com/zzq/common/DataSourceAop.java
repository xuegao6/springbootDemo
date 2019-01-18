package com.zzq.common;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 拦截设置本地线程变量
 */
@Aspect
@Component
public class DataSourceAop {

    private static Logger logger = LoggerFactory.getLogger(DataSourceAop.class);

    @Before("execution(* com.zzq.dao..*.select*(..)) || execution(* com.zzq.dao..*.get*(..))")
    public void setReadDataSourceType() {
        DataSourceContextHolder.read();
        logger.info("dataSource切换到：Read");
    }

    @Before("execution(* com.zzq.dao..*.insert*(..)) || execution(* com.zzq.dao..*.update*(..))")
    public void setWriteDataSourceType() {
        DataSourceContextHolder.write();
        logger.info("dataSource切换到：write");
    }
}
