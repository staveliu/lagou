package com.newer.lagou.controller;

import com.newer.lagou.security.JwtTokenUtil;
import com.newer.lagou.security.domain.JwtUser;
import com.newer.lagou.service.AuthorityCodeService;
import com.newer.lagou.service.CompanyService;
import com.newer.lagou.service.MailService;
import com.newer.lagou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private CompanyService companyService;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    @Qualifier("jwtUserService")
    private UserDetailsService userDetailsService;


    @GetMapping("/getUsername")
    public ResponseEntity<?> getUsername(HttpServletRequest request){
        //获取token
        String token=request.getHeader(tokenHeader).substring(7);
        //从token解析出用户名
        String name=jwtTokenUtil.getUsernameFromToken(token);
        Map<String,Object> result=new HashMap<>();
        JwtUser user=(JwtUser)userDetailsService.loadUserByUsername(name);
        String username=user.getName();
        result.put("username",username);
        return ResponseEntity.ok(result);
    }
}
