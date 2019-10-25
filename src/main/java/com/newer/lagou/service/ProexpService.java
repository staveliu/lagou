package com.newer.lagou.service;

import com.newer.lagou.domain.Proexp;
import com.newer.lagou.mapper.ProexpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProexpService {
    @Autowired
    private ProexpMapper proexpMapper;


    public List<Proexp> findProexp(int resumeid){
        return proexpMapper.findproexp(resumeid);
    }

    public int addProexp(int resumeid,String proname,String position,String describe,String startyear,String startmonth,
            String endyear,String endmonth){

        return proexpMapper.addproexp(resumeid,proname,position,describe,startyear,startmonth,endyear,endmonth);
    }

    public int delProexp(int proid){
        return proexpMapper.delProexp(proid);
    }
}
