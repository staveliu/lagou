package com.newer.lagou.domain;

import java.io.Serializable;

public class Product implements Serializable {
    private Integer productid;//产品id
    private String posters;//海报
    private String productname;//产品名称
    private String productaddress;//产品地址
    private String pintroduction;//产品简介
    private Integer companyid;//所属公司id

    public String getProductaddress() {
        return productaddress;
    }

    public void setProductaddress(String productaddress) {
        this.productaddress = productaddress;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productid=" + productid +
                ", posters='" + posters + '\'' +
                ", productname='" + productname + '\'' +
                ", productaddress='" + productaddress + '\'' +
                ", pintroduction='" + pintroduction + '\'' +
                ", companyid=" + companyid +
                '}';
    }

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    public String getPosters() {
        return posters;
    }

    public void setPosters(String posters) {
        this.posters = posters;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getPintroduction() {
        return pintroduction;
    }

    public void setPintroduction(String pintroduction) {
        this.pintroduction = pintroduction;
    }

    public Integer getCompanyid() {
        return companyid;
    }

    public void setCompanyid(Integer companyid) {
        this.companyid = companyid;
    }
}
