package com.newer.lagou.domain;

import java.io.Serializable;
import java.util.Date;

public class AuthorityCode implements Serializable {
    private Integer id;
    private String email;
    private String code;
    private Integer type;
    private Date create_time;
    private Date verify_time;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getVerify_time() {
        return verify_time;
    }

    public void setVerify_time(Date verify_time) {
        this.verify_time = verify_time;
    }

    @Override
    public String toString() {
        return "AuthorityCode{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", code='" + code + '\'' +
                ", create_time=" + create_time +
                ", verify_time=" + verify_time +
                '}';
    }
}
