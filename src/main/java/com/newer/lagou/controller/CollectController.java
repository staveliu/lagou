package com.newer.lagou.controller;


import com.newer.lagou.domain.Collect;
import com.newer.lagou.domain.Pinformation;
import com.newer.lagou.security.JwtTokenUtil;
import com.newer.lagou.security.domain.JwtUser;
import com.newer.lagou.service.CollectService;
import com.newer.lagou.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/collect")
public class CollectController {
    @Autowired
    private CollectService collectService;

    @Autowired
    private JobService jobService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    @Qualifier("jwtUserService")
    private UserDetailsService userDetailsService;


    //获取收藏
    @GetMapping("/positions")
    public ResponseEntity<?> findPinformation(HttpServletRequest request){
        String token=request.getHeader(tokenHeader).substring(7);
        String username=jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user=(JwtUser)userDetailsService.loadUserByUsername(username);
        List<Pinformation> pinformations=collectService.findPinformation(user.getId());
        return ResponseEntity.ok(pinformations);
    }
    //添加收藏
    @PostMapping("/addcollect")
    public ResponseEntity<?> addCollect(@RequestParam("positionid") int positionid,HttpServletRequest request){
        String token=request.getHeader(tokenHeader).substring(7);
        String username=jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user=(JwtUser)userDetailsService.loadUserByUsername(username);
        return ResponseEntity.ok(collectService.addCollect(positionid,user.getId())) ;
    }

    //取消收藏
    @DeleteMapping("/delcollect")
    public ResponseEntity<?> delCollect(@RequestParam("positionid") int positionid,HttpServletRequest request){
        String token=request.getHeader(tokenHeader).substring(7);
        String username=jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user=(JwtUser)userDetailsService.loadUserByUsername(username);
        return ResponseEntity.ok(collectService.delCollect(user.getId(),positionid));
    }
}
