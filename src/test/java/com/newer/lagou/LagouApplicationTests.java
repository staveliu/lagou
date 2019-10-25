package com.newer.lagou;

import com.newer.lagou.domain.Founders;
import com.newer.lagou.domain.Pinformation;
import com.newer.lagou.domain.Users;
import com.newer.lagou.mapper.UsersMapper;
import com.newer.lagou.security.domain.JwtUser;
import com.newer.lagou.security.domain.JwtUserFactory;
import com.newer.lagou.service.FounderService;
import com.newer.lagou.service.JobService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LagouApplicationTests {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private FounderService founderService;
    @Autowired
    private JobService jobService;

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

    @Test
    public void test5(){
        List<Pinformation> list=jobService.findAllPinformation();
        list.forEach(pinformation -> System.out.println(pinformation.getSalaryMax()) );
    }

    @Test
    public void test6(){
        List<Founders> list2=founderService.findFounderById(1);
        System.out.println(list2.get(0));
        List<Pinformation> list=jobService.findAllPinformation();
        List<Pinformation> list1=new ArrayList<>();
        if(list.size()>10){
            for(int i=0;i<10;i++){
                list1.add(list.get(i));
            }
        }else{
            list1=list;
        }
        list1.forEach(pinformation -> System.out.println(pinformation+"======="));
    }
}
