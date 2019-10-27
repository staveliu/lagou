package com.newer.lagou.service;


import com.newer.lagou.domain.Company;
import com.newer.lagou.domain.Founders;
import com.newer.lagou.domain.Product;
import com.newer.lagou.mapper.CompanyMapper;
import com.newer.lagou.mapper.FounderMapper;
import com.newer.lagou.mapper.ProductMapper;
import com.newer.lagou.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyhomeService {
    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private FounderMapper founderMapper;

    @Autowired
    private ProductMapper productMapper;

    public Company findCinformation(String username){
        System.out.println(companyMapper.findCinformation(companyMapper.findCompnyid(usersMapper.findUserid(username))));
        return companyMapper.findCinformation(companyMapper.findCompnyid(usersMapper.findUserid(username)));
    }

    public List<Product> findProduct(String username){
        int userid=usersMapper.findUserid(username);
        int companyid=companyMapper.findCompnyid(userid);
        return productMapper.findProduct(companyid);
    }

    public List<Founders> findFounders(String username){
        int userid=usersMapper.findUserid(username);
        int companyid=companyMapper.findCompnyid(userid);
        return founderMapper.findFounder(companyid);
    }
}
