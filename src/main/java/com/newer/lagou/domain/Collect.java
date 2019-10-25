package com.newer.lagou.domain;

import java.io.Serializable;

public class Collect implements Serializable {
    private Integer collectid;
    private String positionid;
    private Integer userid;

    public Integer getCollectid() {
        return collectid;
    }

    public void setCollectid(Integer collectid) {
        this.collectid = collectid;
    }

    public String getPositionid() {
        return positionid;
    }

    public void setPositionid(String positionid) {
        this.positionid = positionid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
}
