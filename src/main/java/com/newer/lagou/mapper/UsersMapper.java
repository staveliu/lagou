package com.newer.lagou.mapper;

import com.newer.lagou.domain.Users;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


public interface UsersMapper {


    Users findByEmail(String email);

    @Select("select * from users")
    List<Users> findAll();

    @Select("select * from users where by1=#{openid}")
    Users findByOpenid(String openid);

    @Update("update users set state=#{state} where email=#{email}")
    int changeState(@Param("state") int state, @Param("email") String email);

    @Insert("insert into users(email) values(#{email})")
    int addUser(String email);

    @Insert("insert into users(email,password,state,name,by1,by2) values(#{email},#{password},1,#{name},#{openid},#{password_pre})")
    int addUserWechat(String email,String password,String name,String openid,String password_pre);

    @Select("select * from users where email=#{email}")
    Users findByEmail2(String email);

    @Update("update users set password=#{password} where email=#{email}")
    int changePassword(@Param("password") String password, @Param("email") String email);

    @Insert("insert into user_authority values(#{userid},#{auid})")
    int addUserAuthority(@Param("userid")int userid,@Param("auid")int auid);
}
