package com.newer.lagou.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.newer.lagou.domain.*;
import com.newer.lagou.security.JwtTokenUtil;
import com.newer.lagou.security.domain.JwtUser;
import com.newer.lagou.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/export")
public class ExportResumeController {
    @Autowired
    private ResumeService resumeService;

    @Autowired
    private WorksService worksService;
    @Autowired
    private WorkexpService workexpService;

    @Autowired
    private ProexpService proexpService;

    @Autowired
    private StuService stuService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    @Qualifier("jwtUserService")
    private UserDetailsService userDetailsService;

    @GetMapping("/get")
    public ResponseEntity<?> getCompleteResume(HttpServletRequest request){
        String token=request.getHeader(tokenHeader).substring(7);
        String username=jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user=(JwtUser)userDetailsService.loadUserByUsername(username);
        JsonObject jsonObject = new JsonObject();
        Resume resume = resumeService.findByAccountid(user.getId());
        if (resume == null){
            jsonObject.addProperty("status","401");
            jsonObject.addProperty("msg","简历信息没有填写！");
        }else {
            List<Workexp> workexps = workexpService.findWorkexp(resume.getResumeid());
            List<Proexp> proexps = proexpService.findProexp(resume.getResumeid());
            List<Stu> stus = stuService.findStu(resume.getResumeid());
            List<Works> works = worksService.findWorks(resume.getResumeid());
            String workexp = new Gson().toJson(workexps);
            String proexp = new Gson().toJson(proexps);
            String stu = new Gson().toJson(stus);
            String work = new Gson().toJson(works);
            jsonObject.addProperty("status",200);
            jsonObject.addProperty("resume",new Gson().toJson(resume));
            jsonObject.addProperty("workexp",workexp);
            jsonObject.addProperty("proexp",proexp);
            jsonObject.addProperty("stu",stu);
            jsonObject.addProperty("work",work);
        }
        return ResponseEntity.ok(jsonObject.toString());
}
}
