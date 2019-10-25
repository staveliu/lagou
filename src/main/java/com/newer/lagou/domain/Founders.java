package com.newer.lagou.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(value = { "handler" })
public class Founders implements Serializable {
    private Integer founderid;//创始人id
    private String fimage;//创始人头像
    private String fname;//创始人名字
    private String work;//创始人职位
    private String blog;//创始人微博地址
    private String fintroduction;//创始人简介
    private Integer companyid;//公司id

    @Override
    public String toString() {
        return "Founders{" +
                "founderid=" + founderid +
                ", fimage='" + fimage + '\'' +
                ", fname='" + fname + '\'' +
                ", work='" + work + '\'' +
                ", blog='" + blog + '\'' +
                ", fintroduction='" + fintroduction + '\'' +
                ", companyid=" + companyid +
                '}';
    }

    public Integer getFounderid() {
        return founderid;
    }

    public void setFounderid(Integer founderid) {
        this.founderid = founderid;
    }


    public String getFimage() {
        return fimage;
    }

    public void setFimage(String fimage) {
        this.fimage = fimage;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    public String getFintroduction() {
        return fintroduction;
    }

    public void setFintroduction(String fintroduction) {
        this.fintroduction = fintroduction;
    }

    public Integer getCompanyid() {
        return companyid;
    }

    public void setCompanyid(Integer companyid) {
        this.companyid = companyid;
    }
}
