package com.newer.lagou.domain;

import java.io.Serializable;
import java.util.Date;

public class Subscription implements Serializable {
//订阅表
    private Integer accountid; //用户id
    private String email;
    private Integer periodicity;//周期天数
    private String position;//职位
    private String city;///城市
    private String stge;//公司阶段
    private String field;//行业领域
    private String maney;//期望工资
    private Date updatetime;//创建或修改时间

    public Integer getAccountid() {
        return accountid;
    }

    public void setAccountid(Integer accountid) {
        this.accountid = accountid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(Integer periodicity) {
        this.periodicity = periodicity;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStge() {
        return stge;
    }

    public void setStge(String stge) {
        this.stge = stge;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getManey() {
        return maney;
    }

    public void setManey(String maney) {
        this.maney = maney;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}
