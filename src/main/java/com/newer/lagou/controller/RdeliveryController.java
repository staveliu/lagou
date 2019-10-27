package com.newer.lagou.controller;

import com.newer.lagou.domain.Resume;
import com.newer.lagou.security.JwtTokenUtil;
import com.newer.lagou.security.domain.JwtUser;
import com.newer.lagou.service.RdeliveryService;
import com.newer.lagou.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Response;

@RestController
@RequestMapping("/rdelivery")
public class RdeliveryController {

    @Autowired
    private RdeliveryService rdeliveryService;

    @Autowired
    private ResumeService resumeService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    @Qualifier("jwtUserService")
    private UserDetailsService userDetailsService;

    @PostMapping("/addrdelivery")
    public ResponseEntity<?> addRdelivery(@RequestParam("positionid")int positionid, HttpServletRequest request){
        String token=request.getHeader(tokenHeader).substring(7);
        String username=jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user=(JwtUser)userDetailsService.loadUserByUsername(username);
        Resume resume=resumeService.findByAccountid(user.getId());
        return ResponseEntity.ok(rdeliveryService.addRdelivery(positionid,resume.getResumeid()));
    }
}
