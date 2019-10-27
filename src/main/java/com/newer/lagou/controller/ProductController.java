package com.newer.lagou.controller;

import com.newer.lagou.domain.Founders;
import com.newer.lagou.domain.Product;
import com.newer.lagou.security.JwtTokenUtil;
import com.newer.lagou.security.domain.JwtUser;
import com.newer.lagou.service.MailService;
import com.newer.lagou.service.MyhomeService;
import com.newer.lagou.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    @Qualifier("jwtUserService")
    private UserDetailsService userDetailsService;

    @Autowired
    private MyhomeService myhomeService;


    @Autowired
    private MailService mailService;

    @PostMapping("/product/add")
    public ResponseEntity<?> addFounder(Product product, HttpServletRequest request){
        String token = request.getHeader(tokenHeader).substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user=(JwtUser)userDetailsService.loadUserByUsername(username);
        mailService.changeState(user.getEmail(),8);
        return ResponseEntity.ok(productService.addProduct(product,username));
    }

    //10.24
    @GetMapping("/product/findproduct")
    public ResponseEntity<?> findProduct(HttpServletRequest request){
        //获取token
        String token=request.getHeader(tokenHeader).substring(7);
        //从token解析出用户名
        String username=jwtTokenUtil.getUsernameFromToken(token);
        return ResponseEntity.ok(myhomeService.findProduct(username));
    }
}
