package com.newer.lagou.security.service;

import com.newer.lagou.domain.Users;
import com.newer.lagou.mapper.UsersMapper;
import com.newer.lagou.security.domain.JwtUserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
public class JwtUserService implements UserDetailsService {
    @Autowired
    private UsersMapper usersMapper;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println(11);
        System.out.println(email);
        Users users=usersMapper.findByEmail(email);
        System.out.println(55);

        System.out.println(users);
        if(users==null){
            throw new UsernameNotFoundException("用户名不存在！");
        }
        return JwtUserFactory.create(users);
    }
}
