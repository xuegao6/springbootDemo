package com.zzq.common;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Created by huguoju on 2016/12/29.
 * 拦截设置本地线程变量
 */
@Aspect
@Component
public class DataSourceAop {
    @Before("execution(* com.zzq.dao..*.select*(..)) || execution(* com.zzq.dao..*.get*(..))")
    public void setReadDataSourceType() {
        DataSourceContextHolder.read();
        System.out.println("dataSource切换到：Read");
    }

    @Before("execution(* com.zzq.dao..*.insert*(..)) || execution(* com.zzq.dao..*.update*(..))")
    public void setWriteDataSourceType() {
        DataSourceContextHolder.write();
        System.out.println("dataSource切换到：write");
    }
}
