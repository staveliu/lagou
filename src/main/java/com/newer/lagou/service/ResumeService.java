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
                            String exp,int accountid
                            ){

        resumeMapper.updatetime(accountid);
        return resumeMapper.updateBasicInfo(name,sex,dergee,exp,mobile,email,state,accountid);
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

    public int updateExpect(int accountid,String city,String worktype,String expectposition,String money){
        return resumeMapper.updateExpect(city,worktype,expectposition,money,accountid);
    }

    public int updatezwms(int accountid,String describ){
        return resumeMapper.updatezwjs(describ,accountid);
    }

    //简历名
    public int updateRname(int accountid,String resumename){
        return resumeMapper.updateRname(resumename,accountid);
    }

    //上传头像
    public int updateImg(int resumeid,String img){
        return resumeMapper.updateImg(img,resumeid);
    }

    //简历更新时间
    public int updateTime(int resumeid){
        return resumeMapper.updatetime(resumeid);
    }

}
