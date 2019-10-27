package com.newer.lagou.controller;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.newer.lagou.domain.Pinformation;
import com.newer.lagou.security.JwtTokenUtil;
import com.newer.lagou.security.domain.JwtUser;
import com.newer.lagou.service.CompanyService;
import com.newer.lagou.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class JobController {
    @Autowired
    private JobService jobService;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    @Qualifier("jwtUserService")
    private UserDetailsService userDetailsService;

    @Autowired
    private CompanyService companyService;

    @PostMapping("/job/add")
    public ResponseEntity<?> addPinformation(Pinformation pinformation,HttpServletRequest request){
        //获取token
        System.out.println("进入添加");
        String token=request.getHeader(tokenHeader).substring(7);
        //从token解析出用户名
        String username=jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user=(JwtUser)userDetailsService.loadUserByUsername(username);
        pinformation.setCompanyid(companyService.findCompanyid(user.getId()));//默认为1  从token里拿companyid再set进去
        return ResponseEntity.ok(jobService.addPinformation(pinformation));
    }

    //10.25
    @GetMapping("/job/findAll")
    public ResponseEntity<?> findAllPinformation(HttpServletRequest request){
        //获取token
        String token=request.getHeader(tokenHeader).substring(7);
        //从token解析出用户名
        String username=jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user=(JwtUser)userDetailsService.loadUserByUsername(username);
        return ResponseEntity.ok(jobService.findAllPinformationByCompanyid(companyService.findCompanyid(user.getId())));
    }

    @GetMapping("/auth/findNewJob")
    public ResponseEntity<?> findNewJob(){
        List<Pinformation> list=jobService.findAllPinformation();
        List<Pinformation> list1=new ArrayList<>();
        if(list.size()>10){
            for(int i=0;i<10;i++){
                list1.add(list.get(i));
            }
        }else{
            list1=list;
        }

        return ResponseEntity.ok(list1);
    }

    @GetMapping("/auth/job/findJobInformation")
    public ResponseEntity<?> findPinformationById(@RequestParam("positionid") int positionid){
        Map<String,Object> result=new HashMap<>();
        result.put("position",jobService.findPinformationById(positionid));
        result.put("stage",companyService.findByCompanyId(jobService.findPinformationById(positionid).getCompanyid()));
        return ResponseEntity.ok(result);
    }



}
