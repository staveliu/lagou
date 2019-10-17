package com.newer.lagou.domain;

import java.io.Serializable;

/**
 * @author shining
 * 权限角色类
 */
public class Authority implements Serializable{

    private Integer id;
    private AuthorityName name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AuthorityName getName() {
        return name;
    }

    public void setName(AuthorityName name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Authority{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}
