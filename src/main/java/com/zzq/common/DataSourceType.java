package com.zzq.common;

/**
 * Created by huguoju on 2016/12/29.
 */
public enum DataSourceType {
    READ("read", "从库"),
    WRITE("write", "主库");
    private String type;

    private String name;

    DataSourceType(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
