package com.newer.lagou.controller;

import com.newer.lagou.security.JwtTokenUtil;
import com.newer.lagou.security.domain.JwtUser;
import com.newer.lagou.service.ProexpService;
import com.newer.lagou.service.ResumeService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/proexp")
public class ProexpController {
    @Autowired
    private ResumeService resumeService;

    @Autowired
    private ProexpService proexpService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    @Qualifier("jwtUserService")
    private UserDetailsService userDetailsService;

    @GetMapping("/findproexp")
    public ResponseEntity<?> findProexp(HttpServletRequest request){
        String token=request.getHeader(tokenHeader).substring(7);
        String username=jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user=(JwtUser)userDetailsService.loadUserByUsername(username);
        int resumeid=resumeService.findResumeId(user.getId());

        return  ResponseEntity.ok(proexpService.findProexp(resumeid));
    }

    @PostMapping("/addproexp")
    public ResponseEntity<?> addProexp(@RequestParam("proname")String proname,@RequestParam("position")String position,@RequestParam("describe")String describe,
                                       @RequestParam("startyear")String startyear,@RequestParam("startmonth")String startmonth,
                                       @RequestParam("endyear")String endyear,@RequestParam("endmonth")String endmonth,HttpServletRequest request){
        String token=request.getHeader(tokenHeader).substring(7);
        String username=jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user=(JwtUser)userDetailsService.loadUserByUsername(username);
        int resumeid=resumeService.findResumeId(user.getId());

        return ResponseEntity.ok(proexpService.addProexp(resumeid,proname,position,describe,startyear,startmonth,endmonth,endmonth));
    }

    @DeleteMapping("/delproexp")
    public ResponseEntity<?> delProexp(@Param("proexpid")int proexpid){
        return ResponseEntity.ok(proexpService.delProexp(proexpid));
    }

}
