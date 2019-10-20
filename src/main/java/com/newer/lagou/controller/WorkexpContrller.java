package com.newer.lagou.controller;

import com.newer.lagou.domain.Workexp;
import com.newer.lagou.security.JwtTokenUtil;
import com.newer.lagou.security.domain.JwtUser;
import com.newer.lagou.service.ResumeService;
import com.newer.lagou.service.WorkexpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.soap.SAAJResult;
import java.util.List;

@RestController
@RequestMapping("/workexp")
public class WorkexpContrller {
    @Autowired
    private ResumeService resumeService;

    @Autowired
    private WorkexpService workexpService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    @Qualifier("jwtUserService")
    private UserDetailsService userDetailsService;

    @GetMapping("/findworkexp")
    public ResponseEntity<?> findWorkexp(HttpServletRequest request){
        String token=request.getHeader(tokenHeader).substring(7);
        String username=jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user=(JwtUser)userDetailsService.loadUserByUsername(username);
        int resumeid=resumeService.findResumeId(user.getId());
        return ResponseEntity.ok(workexpService.fendWorkexp(resumeid));
    }

    @PostMapping("/addworkexp")
    public ResponseEntity<?> addWorkexp(@RequestParam("companyname")String companyname, @RequestParam("position")String position,
                                        @RequestParam("startyear")String startyear, @RequestParam("startmonth")String startmonth,
                                        @RequestParam("endyear")String endyear,@RequestParam("endmonth")String endmonth,HttpServletRequest request){
        String token=request.getHeader(tokenHeader).substring(7);
        String username=jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user=(JwtUser)userDetailsService.loadUserByUsername(username);
        int resumeid=resumeService.findResumeId(user.getId());
        return ResponseEntity.ok( workexpService.addWorkexp(resumeid,companyname,position,startyear,startmonth,endyear,endmonth));
    }

    @DeleteMapping("/delworkexp")
    public ResponseEntity delWorkexp(@RequestParam("workexpid")int workexpid){
        return ResponseEntity.ok(workexpService.delWorkexp(workexpid));
    }
}
