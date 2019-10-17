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

    @Update("update users set state=#{state} where email=#{email}")
    int changeState(@Param("state") int state, @Param("email") String email);

    @Insert("insert into users(email) values(#{email})")
    int addUser(String email);

    @Select("select * from users where email=#{email}")
    Users findByEmail2(String email);

    @Update("update users set password=#{password} where email=#{email}")
    int changePassword(@Param("password") String password, @Param("email") String email);

    @Insert("insert into user_authority values(#{userid},#{auid})")
    int addUserAuthority(@Param("userid")int userid,@Param("auid")int auid);
}
