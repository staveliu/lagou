package com.newer.lagou.controller;

import com.newer.lagou.security.JwtTokenUtil;
import com.newer.lagou.security.domain.JwtUser;
import com.newer.lagou.service.ResumeService;
import com.newer.lagou.service.WorksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/works")
public class WorksController {

    @Autowired
    private ResumeService resumeService;

    @Autowired
    private WorksService worksSerivce;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    @Qualifier("jwtUserService")
    private UserDetailsService userDetailsService;

    @GetMapping("/findworks")
    public ResponseEntity<?> findWorks(HttpServletRequest request){
        String token=request.getHeader(tokenHeader).substring(7);
        String username=jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user=(JwtUser)userDetailsService.loadUserByUsername(username);
        int resumeid=resumeService.findResumeId(user.getId());
        System.out.println(resumeid);
        return ResponseEntity.ok(worksSerivce.findWorks(resumeid));
    }

    @PostMapping("/addworks")
    public ResponseEntity<?> addWorks(@RequestParam("link")String link,@RequestParam("describe")String describe,HttpServletRequest request){
        String token=request.getHeader(tokenHeader).substring(7);
        String username=jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user=(JwtUser)userDetailsService.loadUserByUsername(username);
        return ResponseEntity.ok(worksSerivce.addWorks(user.getId(),link,describe));
    }

    @DeleteMapping("/delworks")
    public ResponseEntity<?> delWorks(@RequestParam("id")int id){
        return ResponseEntity.ok(worksSerivce.delWorks(id));
    }
}
