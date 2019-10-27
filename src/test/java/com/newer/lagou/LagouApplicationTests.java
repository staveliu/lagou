package com.newer.lagou;

import com.newer.lagou.domain.Users;
import com.newer.lagou.mapper.UsersMapper;
import com.newer.lagou.security.domain.JwtUser;
import com.newer.lagou.security.domain.JwtUserFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LagouApplicationTests {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UsersMapper usersMapper;

    private JwtUserFactory jwtUserFactory;
    @Test
    public void contextLoads() {
    }

    @Test
    public void test2(){
        String pwd= passwordEncoder.encode("111111");
        System.out.println(pwd);
    }

    @Test
    public void test3(){
        Users users=usersMapper.findByEmail("793863973@qq.com");
        System.out.println(users);
    }

    @Test
    public void test4(){
        JwtUser jwtUser=jwtUserFactory.create(usersMapper.findByEmail("3374202040@qq.com"));
        System.out.println(jwtUser.getUsername()+":"+jwtUser.isEnabled());
    }
}
