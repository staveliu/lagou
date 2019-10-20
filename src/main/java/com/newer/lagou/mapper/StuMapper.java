package com.newer.lagou.mapper;

import com.newer.lagou.domain.Stu;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface StuMapper {

    //添加教育背景
    @Insert("insert into stu values(#{resumeid},#{school},#{degree},#{major},#{startyear},#{endyear})")
    int addSut(@Param("resumeid")int resumeid,@Param("school")String school,@Param("degree")String degree,@Param("major")String major,@Param("startyear")String startyear,@Param("endyear")String endyear);

    //删除教育背景
    @Delete("delete from stu where stuid=#{stuid}")
    int delStu(@Param("stuid")int stuid);

    //查教育背景
    @Select("select * from stu where resumeid=#{resumeid}")
    List<Stu> findByResumeid(@Param("resumeid")int resumeid);


}