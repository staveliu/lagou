package com.newer.lagou.mapper;

import com.newer.lagou.domain.Collect;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface CollectMopper {

    @Select("select * from collect where userid=#{userid}")
    Collect findCollect(@Param("userid")int userid);

    @Insert("intsert into collect valuse(null,#{positionid},#{userid})")
    int addCollect(@Param("positionid")String positionid,@Param("userid")int userid);

    @Update("update collect set positionid=#{positionid} where userid=#{userid}")
    int updateCollect(@Param("positionid")String positionid,@Param("userid")int userid);


}
