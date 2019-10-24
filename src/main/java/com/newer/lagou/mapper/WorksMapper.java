package com.newer.lagou.mapper;

import com.newer.lagou.domain.Works;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface WorksMapper {
    @Insert("insert into works values(null,#{resumeid},#{link},#{describe})")
    int addWorks(@Param("resumeid")int resumeid,@Param("link")String link,@Param("describe")String describe);

    @Select("select * from works where resumeid=#{resumeid}")
    List<Works> findWorks(@Param("resumeid")int resumeid);

    @Delete("delete from works where id=#{id}")
    int delWorks(@Param("id")int id);
}
