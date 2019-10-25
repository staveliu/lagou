package com.newer.lagou.mapper;

import com.newer.lagou.domain.Workexp;
import org.apache.ibatis.annotations.*;
import org.springframework.security.core.parameters.P;

import java.lang.annotation.Inherited;
import java.util.List;

public interface WorkexpMapper {

    @Select("select * from workexp where resumeid=#{resumeid}")
    List<Workexp> findWorkexp(@Param("resumeid")int resumeid);


    @Insert("insert into workexp values(null,#{resumeid},#{companyname},#{position},#{startyear},#{startmonth},#{endyear},#{endmonth})")
    int addWorkexp(@Param("companyname")String companyname, @Param("position")String position,
                   @Param("startyear")String startyear, @Param("startmonth")String startmonth,
                   @Param("endyear")String endyear, @Param("endmonth")String endmonth,
                   @Param("resumeid")int resumeid);

    @Delete("delete from workexp where workexpid=#{workexpid}")
    int delWorkexp(@Param("workexpid")int workexpid);
}
