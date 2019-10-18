package com.newer.lagou.mapper;


import com.newer.lagou.domain.Resume;
import org.apache.ibatis.annotations.Select;

public interface ResumeMapper {

    @Select("select * from resume where id=#{id}")
    Resume findResume(int id);





}
