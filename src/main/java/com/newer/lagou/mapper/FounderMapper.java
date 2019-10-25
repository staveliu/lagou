package com.newer.lagou.mapper;

import com.newer.lagou.domain.Founders;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface FounderMapper {
    @Insert("insert into founder values(null,#{companyid},null,#{fname},#{work},#{blog},#{fintroduction})")
    int addFounder(Founders founders);

    @Update("update founder set Fimage=#{fimage} where Founderid=#{id}")
    int updateFounderImage(@Param("fimage") String fimage,@Param("id")int id);

    @Select("select @@identity as NewID")
    int selectnewid();

    @Select("select * from founder where companyid=#{companyid}")
    List<Founders> findFounderById(@Param("companyid") int companyid);

}
