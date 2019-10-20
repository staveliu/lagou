package com.newer.lagou.controller;

import com.newer.lagou.domain.Resume;
import com.newer.lagou.security.JwtTokenUtil;
import com.newer.lagou.security.domain.JwtUser;
import com.newer.lagou.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/resume")
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    @Qualifier("jwtUserService")
    private UserDetailsService userDetailsService;

    @GetMapping("/findResume")
    public ResponseEntity<?> findResume(HttpServletRequest request){
        String token=request.getHeader(tokenHeader).substring(7);
        String username=jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user=(JwtUser)userDetailsService.loadUserByUsername(username);
        //查找简历数
        int num=resumeService.findResumeNum(user.getId());
        if(num>0){

            Resume resume=resumeService.findByAccountid(user.getId());
            System.out.println("查找====="+resume);
           return ResponseEntity.ok(resume);
        }else {
            int row=resumeService.addResume(user.getId());
            return ResponseEntity.ok(resumeService.findByAccountid(user.getId()));
        }
    }
    @PostMapping("/updateBasicInfo")
    public ResponseEntity<?> updateBasicInfo(@RequestParam("name")String name,@RequestParam("sex")String sex,
                                             @RequestParam("dergee")String dergee,
                                             @RequestParam("mobile")String mobile,@RequestParam("email")String email,
                                             @RequestParam("state")String state,@RequestParam("exp")String exp,
                                             HttpServletRequest request){
        String token=request.getHeader(tokenHeader).substring(7);
        String username=jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user=(JwtUser)userDetailsService.loadUserByUsername(username);
        return ResponseEntity.ok(resumeService.updateBasicInfo(name,sex,dergee,mobile,email,state,exp,user.getId())) ;
    }

}
