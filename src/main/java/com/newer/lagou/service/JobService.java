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

    //添加职位
    public int addPinformation(Pinformation pinformation){
//        System.out.println(jobMapper.findPnid(pinformation));
        pinformation.setPnid(jobMapper.findPnid(pinformation));
        pinformation.setSalaryMin(pinformation.getSalaryMin()+"k");
        pinformation.setSalaryMax(pinformation.getSalaryMax()+"k");
        pinformation.setStatu("有效");
        System.out.println(pinformation);
        return jobMapper.addPinformation(pinformation);
    }


    //根据公司id查询发布的职位
    public List<Pinformation> findAllPinformationByCompanyid(int companyid){
        return jobMapper.findAllPinformationByCompanyid(companyid);
    }

    public Pinformation findPinformationById(int positionid){
        return jobMapper.findPinformationById(positionid);
    }

    public List<Pinformation> findAllPinformation(){
        return jobMapper.findAllPinformation();
    }
}
