package com.newer.lagou.domain;

import java.io.Serializable;

/**
 * @author shining
 * 自定义异常信息类
 */
public class ErrorInfo<T> implements Serializable{

    public static final int OK=0;
    public static final int ERROR=100;

    private int code;
    private String message;
    private String url;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
