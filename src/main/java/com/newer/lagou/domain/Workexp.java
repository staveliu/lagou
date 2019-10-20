package com.newer.lagou.domain;

import java.io.Serializable;


public class Workexp implements Serializable {
    //工作经验表
    private Integer workexpid;//主键
    private Integer Resumeid;//简历表
    private String companyname;//公司名
    private String position;//职位
    private String startyear;//开始年
    private String startmonth;//开始月
    private String endyear;//结束年
    private String endmonth;//结束月

    public Integer getWorkexpid() {
        return workexpid;
    }

    public void setWorkexpid(Integer workexpid) {
        this.workexpid = workexpid;
    }

    public Integer getResumeid() {
        return Resumeid;
    }

    public void setResumeid(Integer resumeid) {
        Resumeid = resumeid;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getStartyear() {
        return startyear;
    }

    public void setStartyear(String startyear) {
        this.startyear = startyear;
    }

    public String getStartmonth() {
        return startmonth;
    }

    public void setStartmonth(String startmonth) {
        this.startmonth = startmonth;
    }

    public String getEndyear() {
        return endyear;
    }

    public void setEndyear(String endyear) {
        this.endyear = endyear;
    }

    public String getEndmonth() {
        return endmonth;
    }

    public void setEndmonth(String endmonth) {
        this.endmonth = endmonth;
    }
}
