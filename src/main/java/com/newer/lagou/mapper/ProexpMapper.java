package com.newer.lagou.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface ProexpMapper {
    @Insert("insert into proexp values(null,#{resumeid},#{proname},#{position},#{describe},#{startyear},#{startmonth}," +
            "#{endyaer},#{endmonth})")
    int addproexp(@Param("resumeid")int resumeid,@Param("proname")String proname,
                  @Param("position")String position,@Param("describe")String describe,
                  @Param("startyaer")String startyear,@Param("startmonth")String startmonth,
                  @Param("endyear")String endyear,@Param("endmonth")String endmonth);

    @Select("select * from proexp where resumeid=#{resumeid}")
    int findproexp(@Param("resumeid")int resumeid);

    @Delete("delete from proexp where proid=#{proid}")
    int delProexp(@Param("proid")int proid);
}
