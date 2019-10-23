package com.newer.lagou.service;

import com.newer.lagou.domain.Workexp;
import com.newer.lagou.mapper.WorkexpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkexpService {
    @Autowired
    private WorkexpMapper workexpMapper;

    public int addWorkexp(int resumeid,String companyname,String position,
                          String startyear,String startmonth,String endyear,
                          String endmonth){
        return workexpMapper.addWorkexp(companyname,position,startyear,startmonth,endyear,endmonth,resumeid);
    }

    public List<Workexp> findWorkexp(int resumeid){
        return workexpMapper.findWorkexp(resumeid);
    }

    public int delWorkexp(int workexpid){
        return workexpMapper.delWorkexp(workexpid);
    }
}
