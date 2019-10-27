package com.newer.lagou.mapper;

import com.newer.lagou.domain.Pinformation;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface JobMapper {
    //添加pinformation  发布的职位信息
    @Insert("insert into pinformation(Companyid,pnid,Postname,department,nature,salary_min,city,Experience,Degree,Jobtemptation,address,pemail,Statu,salary_max,details) values(#{companyid},#{pnid},#{postname}," +
            "#{department},#{nature},#{salaryMin},#{city},#{experience},#{degree},#{jobtemptation},#{address}," +
            "#{pemail},#{statu},#{salaryMax},#{details})")
    int addPinformation(Pinformation pinformation);

    //根据职位类别 查找pnid从pname表    数据库表逻辑有待思考..
    @Select("select pnid from pname where name=#{pname}")
    int findPnid(Pinformation pinformation);

    //查找该公司所有的职位信息
    @Select("select Positionid positionid,Postname postname,department,nature,salary_min salaryMin,salary_max salaryMax," +
            "city,Experience experience,Degree degree,Jobtemptation jobtemptation,address,pemail,time,Statu statu from pinformation where Companyid=#{companyid}")
    List<Pinformation> findAllPinformationByCompanyid(int companyid);

    //查找所有的职位信息，并按照时间顺序排列
    List<Pinformation> findAllPinformation();

    //查询单个职位信息 根据positionid
    Pinformation findPinformationById(int positionid);
}
