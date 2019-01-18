/*
 * Copyright (c) 2018, 北京中体骏彩信息技术有限公司
 * All rights reserved.
 * 本软件，包括了机密资料，是私有和保密的，属于北京中体骏彩信息
 * 技术有限公司的财产与版权所有。未经公司许可，不得对任何部分或
 * 全部文档进行复制，不得进行其他系统转存，不得以任何形式或任何
 * 方式进行传播，包括电子的、机械的、化学的、影印本、录制或者其
 * 他方式。
 */

package com.zzq.model;

/**
 * on 2017/3/22.
 */
public class Result<T> {

    //    error_code 状态值：0 极为成功，其他数值代表失败
    private Integer status;

    //    error_msg 错误信息，若status为0时，为success
    private String msg;

    //    content 返回体报文的出参，使用泛型兼容不同的类型
    private T data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer code) {
        this.status = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData(Object object) {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
