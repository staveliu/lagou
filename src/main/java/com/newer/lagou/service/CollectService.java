package com.newer.lagou.service;

import com.newer.lagou.domain.Collect;
import com.newer.lagou.domain.Pinformation;
import com.newer.lagou.domain.Pinformation1;
import com.newer.lagou.mapper.CollectMopper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectService {

    @Autowired
    private CollectMopper collectMopper;


    public int addCollect(int positionid,int userid){
        return collectMopper.addCollect(positionid,userid);
    }

    public List<Pinformation1> findPinformation(int userid){
        return collectMopper.findPinfotmation(userid);
    }

    public int delCollect(int userid,int positionid){
        return collectMopper.delCollect(positionid,userid);
    }
}
