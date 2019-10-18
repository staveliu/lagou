package com.newer.lagou.domain;

import java.io.Serializable;

public class works implements Serializable {
    private Integer resumeid; //简历id
    private Integer id;//主键id
    private String link; //作品id
    private String describe;//作品描述

    public Integer getResumeid() {
        return resumeid;
    }

    public void setResumeid(Integer resumeid) {
        this.resumeid = resumeid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
