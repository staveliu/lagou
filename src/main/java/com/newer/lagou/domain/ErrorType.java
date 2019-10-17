package com.newer.lagou.domain;

import java.io.Serializable;

/**
 * 自定义错误信息类
 * @author shining
 */
public class ErrorType implements Serializable{

    private String msg;

    public ErrorType(String msg){
        this.msg=msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
