package com.newer.lagou.mapper;

import com.newer.lagou.domain.Authority;
import com.newer.lagou.domain.AuthorityCode;
import com.newer.lagou.domain.Users;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface AuthorityCodeMapper {

    @Select("select * from authority_code where email=#{email} and code=#{code}")
    AuthorityCode getVerify(String email,String code);

    @Insert("insert into authority_code(id,email,code,type,create_time) values(null,#{email},#{code},#{type},#{create_time})")
    int addVerify(AuthorityCode authorityCode);
}
