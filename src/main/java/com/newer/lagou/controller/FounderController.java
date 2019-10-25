package com.newer.lagou.controller;

import com.newer.lagou.domain.Company;
import com.newer.lagou.domain.Founders;
import com.newer.lagou.security.JwtTokenUtil;
import com.newer.lagou.security.domain.JwtUser;
import com.newer.lagou.service.FounderService;
import com.newer.lagou.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class FounderController {
    @Autowired
    private FounderService founderService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    @Qualifier("jwtUserService")
    private UserDetailsService userDetailsService;

    @Autowired
    private MailService mailService;

    @PostMapping("/founder/add")
    public ResponseEntity<?> addFounder(Founders founder, HttpServletRequest request){
        String token = request.getHeader(tokenHeader).substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user=(JwtUser)userDetailsService.loadUserByUsername(username);
        mailService.changeState(user.getEmail(),7);
        return ResponseEntity.ok(founderService.addFounder(founder,username));
    }
}
