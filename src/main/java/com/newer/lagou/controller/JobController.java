package com.newer.lagou.controller;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.newer.lagou.domain.Pinformation;
import com.newer.lagou.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class JobController {
    @Autowired
    private JobService jobService;

    @PostMapping("/job/add")
    public ResponseEntity<?> findProduct(Pinformation pinformation, HttpServletRequest request){
        System.out.println(pinformation);
        pinformation.setCompanyid(1);//默认为1  从token里拿companyid再set进去
        return ResponseEntity.ok(jobService.addPinformation(pinformation));
    }

    @GetMapping("/auth/findNewJob")
    public ResponseEntity<?> findNewJob(){
        List<Pinformation> list=jobService.findAllPinformation();
        List<Pinformation> list1=new ArrayList<>();
        if(list.size()>10){
            for(int i=0;i<10;i++){
                list1.add(list.get(i));
            }
        }else{
            list1=list;
        }

        return ResponseEntity.ok(list1);
    }
}
