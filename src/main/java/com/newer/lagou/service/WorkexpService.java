package com.newer.lagou.service;

import com.newer.lagou.domain.Workexp;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkexpService {
    @Autowired
    private WorkexpService workexpMapper;

    public int addWorkexp(int resumeid,String companyname,String position,
                          String startyear,String startmonth,String endyear,
                          String engmonth){
        return workexpMapper.workexpMapper.addWorkexp(resumeid,companyname,position,startyear,startmonth,endyear,engmonth);
    }

    public List<Workexp> fendWorkexp(int resumeid){
        return workexpMapper.fendWorkexp(resumeid);
    }

    public int delWorkexp(int workexpid){
        return workexpMapper.delWorkexp(workexpid);
    }
}
