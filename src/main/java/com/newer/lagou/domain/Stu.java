package com.newer.lagou.domain;

import java.io.Serializable;
import java.util.Date;

public class Stu implements Serializable {
    private Integer resumeid;
    private String school;
    private String degree;
    private String major;
    private String startyear;
    private String endyear;

    public Integer getResumeid() {
        return resumeid;
    }

    public void setResumeid(Integer resumeid) {
        this.resumeid = resumeid;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }


}
