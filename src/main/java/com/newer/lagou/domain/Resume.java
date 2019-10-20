package com.newer.lagou.domain;

import java.io.Serializable;

public class Resume implements Serializable {
    //简历表
    private Integer resumeid;//简历表id
    private Integer accountid;//用户id
    private String name;//姓名
    private String resumename;//简历名
    private String img;//头像
    private String sex;//性别
    private String degree;//学历
    private String mobile;//手机号
    private String email;//联系邮件
    private String state;//目前状态
    private String city;//期望城市
    private String worktype;//期望工作类型
    private String expectpositioon;//期望职位
    private String money;//期望工资
    private String datetime;//简历最后更新时间
    private String exp;//工作时间
    private String describe;//自我描述

    public int getResumeid() {
        return resumeid;
    }

    public void setResumeid(int resumeid) {
        this.resumeid = resumeid;
    }

    public int getAccountid() {
        return accountid;
    }

    public void setAccountid(int accountid) {
        this.accountid = accountid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResumename() {
        return resumename;
    }

    public void setResumename(String resumename) {
        this.resumename = resumename;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setResumeid(Integer resumeid) {
        this.resumeid = resumeid;
    }

    public void setAccountid(Integer accountid) {
        this.accountid = accountid;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getWorktype() {
        return worktype;
    }

    public void setWorktype(String worktype) {
        this.worktype = worktype;
    }

    public String getExpectpositioon() {
        return expectpositioon;
    }

    public void setExpectpositioon(String expectpositioon) {
        this.expectpositioon = expectpositioon;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    @Override
    public String toString() {
        return "Resume{" +
                "resumeid=" + resumeid +
                ", accountid=" + accountid +
                ", name='" + name + '\'' +
                ", resumename='" + resumename + '\'' +
                ", img='" + img + '\'' +
                ", sex='" + sex + '\'' +
                ", degree='" + degree + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", worktype='" + worktype + '\'' +
                ", expectpositioon='" + expectpositioon + '\'' +
                ", money='" + money + '\'' +
                ", datetime='" + datetime + '\'' +
                ", exp='" + exp + '\'' +
                ", describe='" + describe + '\'' +
                '}';
    }
}
