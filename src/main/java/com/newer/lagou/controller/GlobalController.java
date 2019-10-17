package com.newer.lagou.controller;

import com.newer.lagou.domain.LagouException;
import com.newer.lagou.domain.Users;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author shining
 */
@RestController
//@CrossOrigin(origins = "http://localhost")
public class GlobalController {

    @GetMapping("/usersession")
    public ResponseEntity<?> usersession(HttpSession session){
        //返回登录用户对象
        Users user=(Users) session.getAttribute("user");
        System.out.println(session.getId());
        return ResponseEntity.ok(user);
    }

    @GetMapping("/testError")
    public ResponseEntity<?> testError() throws LagouException {
        throw new LagouException("后台系统对接异常");
    }


}
