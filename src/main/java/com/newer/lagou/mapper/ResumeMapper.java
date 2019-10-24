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
    @Update("update resume set name=#{name},sex=#{sex},degree=#{degree},exp=#{exp},mobile=#{mobile},email=#{email},state=#{state} where accountid=#{accountid}")
    int updateBasicInfo(@Param("name")String name,
                        @Param("sex")String sex,
                        @Param("degree")String degree,
                        @Param("exp")String exp,
                        @Param("mobile")String mobile,
                        @Param("email")String email,
                        @Param("state")String state,
                        @Param("accountid")int accountid);

    //查找简历id
    @Select("select resumeid from resume where accountid=#{accountid}")
    int findResumeId(@Param("accountid")int accountid);

    //查询简历数
    @Select("select count(resumeid) from resume where accountid=#{accountid}")
    int findResumeNum(@Param("accountid")int accountid);

    //简历更新时间
    @Update("update resume set datetime=now() where resumeid=#{resumeid}")
    int updatetime(@Param("resumeid")int resumeid);

    //查简历
    @Select("select * from resume where accountid=#{accountid}")
    Resume findByAccountid(@Param("accountid")int accountid);


    //修改期望工作
    @Update("update resume set city=#{city},worktype=#{worktype},expectposition=#{expectposition},money=#{money} where accountid=#{accountid}")
    int updateExpect(@Param("city")String city,@Param("worktype")String worktype,@Param("expectposition")String expectposition,@Param("money")String money,@Param("accountid")int accountid);

    //自我评价
    @Update("update resume set `describe`=#{describe} where accountid=#{accountid}")
    int updatezwjs(@Param("describe")String describe,@Param("accountid")int accountid);

    //简历名
    @Update("update resume set resumename=#{resumename} where accountid=#{accountid}")
    int updateRname(@Param("resumename")String resumename,@Param("accountid")int accountid);


    @Update("update resume set img=#{img} where resumeid=#{resumeid}")
    int updateImg(@Param("img")String img,@Param("resumeid")int resumeid);

}
