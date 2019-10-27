package com.newer.lagou.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface RdeliveryMapper {
    @Insert("insert into rdelivery(positionid,resumeid) values(#{positionid},#{resumeid})")
    int addRdelivery(@Param("positionid")int positionid,@Param("resumeid")int resumeid);
}
