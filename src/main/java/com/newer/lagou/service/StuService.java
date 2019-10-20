package com.newer.lagou.service;

import com.newer.lagou.domain.Stu;
import com.newer.lagou.mapper.StuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StuService {
    @Autowired
    private StuMapper stuMapper;

    public List<Stu> findStu(int resumeid){
       return stuMapper.findByResumeid(resumeid);
    }

    public int addStu(int resumeid,String school,String degree,String major,String startyear,String endyear){
        return stuMapper.addSut(resumeid,school,degree,major,startyear,endyear);
    }

    public int delStu(int stuid){
        return stuMapper.delStu(stuid);
    }
}
