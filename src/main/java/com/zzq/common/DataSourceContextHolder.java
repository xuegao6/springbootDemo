package com.zzq.common;

/**
 * 本地线程全局变量
 */
public class DataSourceContextHolder  {
    private static final ThreadLocal<String> local = new ThreadLocal<>();

    public static ThreadLocal<String> getLocal() {
        return local;
    }

    /**
     * 读可能是多个库
     */
    public static void read() {
        local.set(DataSourceType.READ.getType());
    }

    /**
     * 写只有一个库
     */
    public static void write() {
        local.set(DataSourceType.WRITE.getType());
    }

    public static String getJdbcType() {
        return local.get();
    }
}
