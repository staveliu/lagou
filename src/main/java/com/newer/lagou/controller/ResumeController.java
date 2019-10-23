package com.newer.lagou.controller;

import com.newer.lagou.domain.Resume;
import com.newer.lagou.security.JwtTokenUtil;
import com.newer.lagou.security.domain.JwtUser;
import com.newer.lagou.service.ResumeService;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/resume")
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    @Qualifier("jwtUserService")
    private UserDetailsService userDetailsService;

    @GetMapping("/findResume")
    public ResponseEntity<?> findResume(HttpServletRequest request){
        String token=request.getHeader(tokenHeader).substring(7);
        String username=jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user=(JwtUser)userDetailsService.loadUserByUsername(username);
        //查找简历数
        int num=resumeService.findResumeNum(user.getId());
        if(num>0){

            Resume resume=resumeService.findByAccountid(user.getId());
           return ResponseEntity.ok(resume);
        }else {
            int row=resumeService.addResume(user.getId());
            return ResponseEntity.ok(resumeService.findByAccountid(user.getId()));
        }
    }
    @PostMapping("/updateBasicInfo")
    public ResponseEntity<?> updateBasicInfo(@RequestParam("name")String name,@RequestParam("sex")String sex,
                                             @RequestParam("degree")String dergee,
                                             @RequestParam("mobile")String mobile,@RequestParam("email")String email,
                                             @RequestParam("state")String state,@RequestParam("exp")String exp,
                                             HttpServletRequest request){
        System.out.println("信息修改");
        String token=request.getHeader(tokenHeader).substring(7);
        String username=jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user=(JwtUser)userDetailsService.loadUserByUsername(username);
        resumeService.updateBasicInfo(name,sex,dergee,mobile,email,state,exp,user.getId());

        return ResponseEntity.ok(resumeService.findByAccountid(user.getId())) ;
    }

    @PostMapping("/updateExpectposit")
    public ResponseEntity<?> updateExpectposition(@RequestParam("city")String city,@RequestParam("worktype")String worktype,
                                               @RequestParam("expectposition")String expectposition,@RequestParam("money")String money,
                                               HttpServletRequest request){
        String token=request.getHeader(tokenHeader).substring(7);
        String username=jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user=(JwtUser)userDetailsService.loadUserByUsername(username);
        return ResponseEntity.ok(resumeService.updateExpect(user.getId(),city,worktype,expectposition,money));
    }
//更新自我介绍
    @PostMapping("/updatezwms")
    public ResponseEntity<?> updatezwms(@RequestParam("describe")String describe,
                                               HttpServletRequest request){
        String token=request.getHeader(tokenHeader).substring(7);
        String username=jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user=(JwtUser)userDetailsService.loadUserByUsername(username);
        return ResponseEntity.ok(resumeService.updatezwms(user.getId(),describe));
    }

    //简历名
    @PostMapping("/resumename")
    public ResponseEntity<?> updateresumename(@RequestParam("resumename")String resumename,
                                        HttpServletRequest request){
        String token=request.getHeader(tokenHeader).substring(7);
        String username=jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user=(JwtUser)userDetailsService.loadUserByUsername(username);
        return ResponseEntity.ok(resumeService.updateRname(user.getId(),resumename));
    }


}
