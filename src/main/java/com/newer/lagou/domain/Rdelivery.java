package com.newer.lagou.domain;

import java.io.Serializable;

public class Rdelivery implements Serializable {

    private Integer deliveryid;
    private Integer positionid;
    private Integer resumeid;
    private String statu;

    public Integer getDeliveryid() {
        return deliveryid;
    }

    public void setDeliveryid(Integer deliveryid) {
        this.deliveryid = deliveryid;
    }

    public Integer getPositionid() {
        return positionid;
    }

    public void setPositionid(Integer positionid) {
        this.positionid = positionid;
    }

    public Integer getResumeid() {
        return resumeid;
    }

    public void setResumeid(Integer resumeid) {
        this.resumeid = resumeid;
    }

    public String getStatu() {
        return statu;
    }

    public void setStatu(String statu) {
        this.statu = statu;
    }

    @Override
    public String toString() {
        return "Rdelivery{" +
                "deliveryid=" + deliveryid +
                ", positionid=" + positionid +
                ", resumeid=" + resumeid +
                ", statu='" + statu + '\'' +
                '}';
    }
}
