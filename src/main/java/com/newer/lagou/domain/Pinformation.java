package com.newer.lagou.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(value = { "handler" })
public class Pinformation implements Serializable {
    private Integer positionid;//职位id
    private Integer companyid;//公司id
    private String pname;//职位类型
    private Integer pnid;//职位名称id
    private String postname;//岗位名称
    private String department;//部门
    private String nature;//工作性质
    private String salaryMin;//最低工资
    private String salaryMax;//最高工资
    private String city;//城市
    private String experience;//经验
    private String degree;//学历
    private String jobtemptation;//职业诱惑
    private String details;//职位描述
    private String address;//工作详细地址
    private String pemail;//公司邮箱
    private Date time;//发布时间
    private String statu;//状态
    private Company company;
    private List<Founders> founders;

    @Override
    public String toString() {
        return "Pinformation{" +
                "positionid=" + positionid +
                ", companyid=" + companyid +
                ", pname='" + pname + '\'' +
                ", pnid=" + pnid +
                ", postname='" + postname + '\'' +
                ", department='" + department + '\'' +
                ", nature='" + nature + '\'' +
                ", salaryMin='" + salaryMin + '\'' +
                ", salaryMax='" + salaryMax + '\'' +
                ", city='" + city + '\'' +
                ", experience='" + experience + '\'' +
                ", degree='" + degree + '\'' +
                ", jobtemptation='" + jobtemptation + '\'' +
                ", details='" + details + '\'' +
                ", address='" + address + '\'' +
                ", pemail='" + pemail + '\'' +
                ", time=" + time +
                ", statu='" + statu + '\'' +
                ", company=" + company +
                ", founders=" + founders +
                '}';
    }


    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Founders> getFounders() {
        return founders;
    }

    public void setFounders(List<Founders> founders) {
        this.founders = founders;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getSalaryMin() {
        return salaryMin;
    }

    public void setSalaryMin(String salaryMin) {
        this.salaryMin = salaryMin;
    }

    public String getSalaryMax() {
        return salaryMax;
    }

    public void setSalaryMax(String salaryMax) {
        this.salaryMax = salaryMax;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Integer getPositionid() {
        return positionid;
    }

    public void setPositionid(Integer positionid) {
        this.positionid = positionid;
    }

    public Integer getCompanyid() {
        return companyid;
    }

    public void setCompanyid(Integer companyid) {
        this.companyid = companyid;
    }

    public Integer getPnid() {
        return pnid;
    }

    public void setPnid(Integer pnid) {
        this.pnid = pnid;
    }

    public String getPostname() {
        return postname;
    }

    public void setPostname(String postname) {
        this.postname = postname;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }



    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getJobtemptation() {
        return jobtemptation;
    }

    public void setJobtemptation(String jobtemptation) {
        this.jobtemptation = jobtemptation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPemail() {
        return pemail;
    }

    public void setPemail(String pemail) {
        this.pemail = pemail;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getStatu() {
        return statu;
    }

    public void setStatu(String statu) {
        this.statu = statu;
    }
}
