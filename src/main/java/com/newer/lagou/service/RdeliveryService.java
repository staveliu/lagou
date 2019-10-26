package com.newer.lagou.service;


import com.newer.lagou.mapper.RdeliveryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RdeliveryService {
    @Autowired
    private RdeliveryMapper rdeliveryMapper;

    public int addRdelivery(int positionid,int resumeid){
        return rdeliveryMapper.addRdelivery(positionid,resumeid);
    }
}
