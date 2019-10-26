package com.newer.lagou.service;


import com.newer.lagou.domain.Pinformation;
import com.newer.lagou.mapper.JobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {
    @Autowired
    private JobMapper jobMapper;

    public int addPinformation(Pinformation pinformation){
//        System.out.println(jobMapper.findPnid(pinformation));
        pinformation.setPnid(jobMapper.findPnid(pinformation));
        pinformation.setSalaryMin(pinformation.getSalaryMin()+"k");
        pinformation.setSalaryMax(pinformation.getSalaryMax()+"k");
        pinformation.setStatu("有效");
        System.out.println(pinformation);
        return jobMapper.addPinformation(pinformation);
    }

    public Pinformation findByPositionid(int positionid){
        return jobMapper.findPinformation(positionid);
    }

    public List<Pinformation> findAllPinformation(int companyid){
        return jobMapper.findAllPinformation(companyid);
    }
}
