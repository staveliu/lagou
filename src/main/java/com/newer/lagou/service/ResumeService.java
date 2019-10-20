package com.newer.lagou.service;

import com.newer.lagou.domain.Resume;
import com.newer.lagou.mapper.ResumeMapper;
import com.newer.lagou.mapper.StuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ResumeService {
    @Autowired
    private ResumeMapper resumeMapper;

    public int addResume(int accountid){
        return resumeMapper.addResume(accountid);
    }

    public int updateBasicInfo(String name,
                            String sex, String dergee, String mobile,
                            String email, String state,
                            String exp,int resumeid
                            ){
        Date datetime=new Date();
        resumeMapper.updatetime(datetime,resumeid);
        return resumeMapper.updateBasicInfo(name,sex,dergee,exp,mobile,email,state,resumeid);
    }

    public Resume findByAccountid(int accountid){
        return resumeMapper.findByAccountid(accountid);
    }

    public int findResumeNum(int accountid){
        return resumeMapper.findResumeNum(accountid);
    }

    public int  findResumeId(int accountid){
        return resumeMapper.findResumeId(accountid);
    }




}
