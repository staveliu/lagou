package com.newer.lagou.service;

import com.newer.lagou.domain.Users;
import com.newer.lagou.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UsersMapper usersMapper;

    public Users findByEmail(String email){
        return usersMapper.findByEmail(email);
    }

    public int updateName(String name,int id){
        return usersMapper.updateName(name,id);
    }
}
