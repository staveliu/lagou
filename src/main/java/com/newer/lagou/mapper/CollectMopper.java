package com.newer.lagou.mapper;

import com.newer.lagou.domain.Collect;
import com.newer.lagou.domain.Pinformation;
import com.newer.lagou.domain.Pinformation1;
import org.apache.ibatis.annotations.*;


import java.util.List;

public interface CollectMopper {


    @Results(value = {@Result(property = "positionid",column = "positionid",id = true),
            @Result(property = "pnid",column = "pnid"),
            @Result(property = "postname",column = "postname"),
            @Result(property = "department",column = "department"),
            @Result(property = "nature",column = "nature"),
            @Result(property = "salaryMin",column = "salary_Min"),
            @Result(property = "salaryMax",column = "salary_Max"),
            @Result(property = "city",column = "city"),
            @Result(property = "experience",column = "experience"),
            @Result(property = "degree",column = "degree"),
            @Result(property = "jobtemptation",column = "jobtemptation"),
            @Result(property = "details",column = "details"),
            @Result(property = "address",column = "address"),
            @Result(property = "pemail",column = "pemail"),
            @Result(property = "time",column = "time"),
            @Result(property = "statu",column = "statu"),
            @Result(property = "company",column = "companyid",javaType = com.newer.lagou.domain.Company.class,
                    one = @One(select = "com.newer.lagou.mapper.CompanyMapper.findByCompanyid")) })
    @Select("select p.positionid,pnid,Postname,department,nature,salary_max,salary_min,city,Experience,Degree,pemail,time,statu,companyid from collect c join pinformation p on c.positionid=p.positionid where userid=#{userid}")
    List<Pinformation1> findPinfotmation(@Param("userid")int userid);



    @Insert("intsert into collect valuse(null,#{positionid},#{userid})")
    int addCollect(@Param("positionid")int positionid,@Param("userid")int userid);

    @Delete("delete from collect where  positionid=#{positionid} and userid=#{userid}")
    int delCollect(@Param("positionid")int positionid,@Param("userid")int userid);


}
