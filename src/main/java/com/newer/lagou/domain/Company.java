package com.newer.lagou.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(value = { "handler" })
public class Company implements Serializable {
    private Integer companyid;//公司id
    private String companyname;//公司全名
    private String cAbbreviations;//公司简称
    private String logo;//logo图片
    private String web;//公司网址
    private String city;//公司地址
    private String field;//行业领域
    private String csize;//公司规模
    private String stage;//发展阶段
    private String introduction;//公司简介
    private String label;//公司标签
    private String welfare;//福利
    private Integer userid;//用户id
    private String detailed;//公司详细介绍

    @Override
    public String toString() {
        return "Company{" +
                "companyid=" + companyid +
                ", companyname='" + companyname + '\'' +
                ", cAbbreviations='" + cAbbreviations + '\'' +
                ", logo='" + logo + '\'' +
                ", web='" + web + '\'' +
                ", city='" + city + '\'' +
                ", field='" + field + '\'' +
                ", csize='" + csize + '\'' +
                ", stage='" + stage + '\'' +
                ", introduction='" + introduction + '\'' +
                ", label='" + label + '\'' +
                ", welfare='" + welfare + '\'' +
                ", userid=" + userid +
                ", detailed='" + detailed + '\'' +
                ", shortDetailed='" + shortDetailed + '\'' +
                ", stages=" + stages +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    private String shortDetailed;//公司一句话介绍
    private List<Stage> stages;//投资此家公司的机构
    private String phone;
    private String email;

    public Integer getCompanyid() {
        return companyid;
    }

    public void setCompanyid(Integer companyid) {
        this.companyid = companyid;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getcAbbreviations() {
        return cAbbreviations;
    }

    public void setcAbbreviations(String cAbbreviations) {
        this.cAbbreviations = cAbbreviations;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getCsize() {
        return csize;
    }

    public void setCsize(String csize) {
        this.csize = csize;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getWelfare() {
        return welfare;
    }

    public void setWelfare(String welfare) {
        this.welfare = welfare;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getDetailed() {
        return detailed;
    }

    public void setDetailed(String detailed) {
        this.detailed = detailed;
    }

    public String getShortDetailed() {
        return shortDetailed;
    }

    public void setShortDetailed(String shortDetailed) {
        this.shortDetailed = shortDetailed;
    }

    public List<Stage> getStages() {
        return stages;
    }

    public void setStages(List<Stage> stages) {
        this.stages = stages;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
