package com.newer.lagou.security.domain;

import com.newer.lagou.domain.Authority;
import com.newer.lagou.domain.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JwtUserFactory {
    
    public static JwtUser create(Users users){
        return new JwtUser(users.getId(),users.getEmail(),users.getPassword(),true,
                users.getLastPasswordResetDate(),mapToAuthority(users.getAuthority()), users.getName(),users.getBy1(),users.getBy2(),users.getState(),users.getType());
    }

    private static Collection<? extends GrantedAuthority> mapToAuthority(List<Authority> authority) {
        //集合流操作，将角色转化成安全框架可以识别的角色
        return authority.stream().map(authority1 ->
                new SimpleGrantedAuthority(authority1.getName().name()))
                .collect(Collectors.toList());
    }
}
