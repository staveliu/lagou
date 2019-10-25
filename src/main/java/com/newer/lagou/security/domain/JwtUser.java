package com.newer.lagou.security.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

/**
 * 用于安全框架的用户类
 */
public class JwtUser implements UserDetails {

    private final Integer id;//必须
    private final String email;//必须
    private final String password;//必须
    private final Date lastPasswordResetDate;//必须
    private final Collection<? extends GrantedAuthority> authorities;//必须
    private final String name;
    private final String by1;
    private final String by2;
    private final boolean enabled;
    private final Integer state;
    private final Integer type;//临时使用


    public JwtUser(Integer id, String email, String password, boolean enabled, Date lastPasswordResetDate, Collection<? extends GrantedAuthority> authorities, String name, String by1, String by2,Integer state,Integer type) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.lastPasswordResetDate = lastPasswordResetDate;
        this.authorities = authorities;
        this.name = name;
        this.by1 = by1;
        this.by2 = by2;
        this.state=state;
        this.type=type;
    }

    public String getEmail() {
        return email;
    }

    public Integer getState() {
        return state;
    }

    public Integer getType() {
        return type;
    }

    public Integer getId() {
        return id;
    }

    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public String getName() {
        return name;
    }

    public String getBy1() {
        return by1;
    }

    public String getBy2() {
        return by2;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 定义账户是否可用
     * @return
     */
    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }
}
