package com.newer.lagou.mapper;

import com.newer.lagou.domain.Collect;
import com.newer.lagou.domain.Pinformation;
import org.apache.ibatis.annotations.*;


import java.util.List;

public interface CollectMopper {


    @Select("select * from collect c join pinformation p on c.positionid=p.positionid where userid=#{userid}")
    List<Pinformation> findPinfotmation(@Param("userid")int userid);

    @Insert("intsert into collect valuse(null,#{positionid},#{userid})")
    int addCollect(@Param("positionid")int positionid,@Param("userid")int userid);

    @Delete("delete from collect where  positionid=#{positionid} and userid=#{userid}")
    int delCollect(@Param("positionid")int positionid,@Param("userid")int userid);


}
