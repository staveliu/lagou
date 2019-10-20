package com.newer.lagou.mapper;


import com.newer.lagou.domain.Resume;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.security.core.parameters.P;

import java.util.Date;

public interface ResumeMapper {

    @Select("select * from resume where id=#{id}")
    Resume findResume(int id);

    //添加简历表
    @Insert("insert into resume(accountid) values(#{accountid})")
    int addResume(@Param("accountid")int accountid);

    //修改基本信息
    @Update("update resume set name=#{name},sex=#{sex},degree=#{degree},exp=#{exp},mobile=#{mobile},email=#{email},state={state} where resumeid=#{resumeid}")
    int updateBasicInfo(@Param("name")String name,
                        @Param("sex")String sex,
                        @Param("degree")String degree,
                        @Param("exp")String exp,
                        @Param("mobile")String mobile,
                        @Param("email")String email,
                        @Param("state")String state,
                        @Param("resumeid")int resumeid);

    //查找简历id
    @Select("select resumeid from where accountid=#{accountid}")
    int findResumeId(@Param("accountid")int accountid);

    //查询简历数
    @Select("select count(resumeid) from resume where accountid=#{accountid}")
    int findResumeNum(@Param("accountid")int accountid);

    //简历更新时间
    @Update("update resume set datetime=#{datetime} where resumeid=#{resumeid}")
    int updatetime(@Param("datetime")Date datetime,@Param("resumeid")int resumeid);

    //查简历
    @Select("select * from resume where accountid=#{accountid}")
    Resume findByAccountid(@Param("accountid")int accountid);









}
