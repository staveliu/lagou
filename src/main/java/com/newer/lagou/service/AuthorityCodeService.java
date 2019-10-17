package com.newer.lagou.service;

import com.newer.lagou.domain.Authority;
import com.newer.lagou.domain.AuthorityCode;
import com.newer.lagou.mapper.AuthorityCodeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityCodeService {
    @Autowired
    private AuthorityCodeMapper authorityCodeMapper;

    public AuthorityCode verify(String email, String code){
        AuthorityCode authorityCode = authorityCodeMapper.getVerify(email,code);
        return authorityCode;
    }
    public int addVerify(AuthorityCode authorityCode){
        return authorityCodeMapper.addVerify(authorityCode);
    }
}
