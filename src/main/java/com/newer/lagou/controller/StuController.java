package com.newer.lagou.controller;

import com.newer.lagou.security.JwtTokenUtil;
import com.newer.lagou.security.domain.JwtUser;
import com.newer.lagou.service.ResumeService;
import com.newer.lagou.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/stu")
public class StuController {
    @Autowired
    private ResumeService resumeService;

    @Autowired
    private StuService stuService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    @Qualifier("jwtUserService")
    private UserDetailsService userDetailsService;

    @GetMapping("findstu")
    public ResponseEntity<?> findStu(HttpServletRequest request){
        String token=request.getHeader(tokenHeader).substring(7);
        String username=jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user=(JwtUser)userDetailsService.loadUserByUsername(username);
        int resumeid=resumeService.findResumeId(user.getId());
        resumeService.updateTime(resumeid);
        return ResponseEntity.ok(stuService.findStu(resumeid));
    }
    @PostMapping("/addstu")
    public ResponseEntity<?> addStu(@RequestParam("school")String school, @RequestParam("degree")String degree,
                                    @RequestParam("major")String major, @RequestParam("startyear")String startyear,
                                    @RequestParam("endyear")String endyear,HttpServletRequest request){
        String token=request.getHeader(tokenHeader).substring(7);
        String username=jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user=(JwtUser)userDetailsService.loadUserByUsername(username);
        int resumeid=resumeService.findResumeId(user.getId());
        return ResponseEntity.ok(stuService.addStu(resumeid,school,degree,major,startyear,endyear));
    }
    @DeleteMapping("/delstu")
    public ResponseEntity<?> delStu(@RequestParam("stuid")int stuid){
        return ResponseEntity.ok(stuService.delStu(stuid));
    }

}
