package com.newer.lagou.domain;

import java.io.Serializable;

public class Stage implements Serializable {
    private String stage;//融资伦次
    private String org;//机构名称
    private Integer id;//融资公司

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Stage{" +
                "stage='" + stage + '\'' +
                ", org='" + org + '\'' +
                ", id=" + id +
                '}';
    }
}
