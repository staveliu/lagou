package com.newer.lagou.service;

import com.newer.lagou.domain.Collect;
import com.newer.lagou.mapper.CollectMopper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CollectService {

    @Autowired
    private CollectMopper collectMopper;


    public int addCollect(String positionid,int userid){
        return collectMopper.addCollect(positionid,userid);
    }

    public Collect findCollect(int userid){
        return collectMopper.findCollect(userid);
    }

    public int updateCollect(int userid,String positionid){
        return collectMopper.updateCollect(positionid,userid);
    }
}
