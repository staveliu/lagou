package com.newer.lagou.controller;


import com.newer.lagou.domain.Pinformation;
import com.newer.lagou.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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
}
