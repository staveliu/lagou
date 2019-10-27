package com.newer.lagou.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.newer.lagou.domain.AuthorityCode;
import com.newer.lagou.domain.Company;
import com.newer.lagou.domain.Users;
import com.newer.lagou.mapper.UsersMapper;
import com.newer.lagou.security.JwtTokenUtil;
import com.newer.lagou.security.domain.JwtAuthenticationResponse;
import com.newer.lagou.security.domain.JwtUser;
import com.newer.lagou.service.*;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.newer.lagou.controller.MailController.genRandomNum;

@RestController
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    @Autowired
    @Qualifier("jwtUserService")
    private UserDetailsService userDetailsService;

    @Autowired
    private MailService mailService;

    @Autowired
    private AuthorityCodeService authorityCodeService;

    @Autowired
    private MyhomeService myhomeService;

    @PutMapping("/company/changelabel")
    public ResponseEntity<?> changeLabel(Company company,
                                         HttpServletRequest request){
        //获取token
        String token=request.getHeader(tokenHeader).substring(7);
        //从token解析出用户名
        String username=jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user=(JwtUser)userDetailsService.loadUserByUsername(username);
        company.setCompanyid(companyService.findCompanyid(user.getId()));
        mailService.changeState(user.getEmail(),6);
        return ResponseEntity.ok(companyService.changeLabel(company.getCompanyid(),company.getLabel()));
    }

    /**
     * 存储一个新的公司信息
     * @param company
     * @param request
     * @return
     */
    @PostMapping("/company/saveNewCompany")
    public ResponseEntity<?> addNewCompany(Company company,
                                           HttpServletRequest request){
        //获取token
        String token=request.getHeader(tokenHeader).substring(7);
        //从token解析出用户名
        String username=jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user=(JwtUser)userDetailsService.loadUserByUsername(username);
        Map<String,Object> result=new HashMap<>();
        int rows=companyService.addNewCompany(user.getId(),company.getEmail(),company.getPhone(),company.getCompanyname());
        if(rows>0){
            //修改用户状态为申请开通招聘服务第三步
            mailService.changeState(user.getEmail(),3);
//        user=(JwtUser)userDetailsService.loadUserByUsername(username);
//        //生成新token
//        String newtoken=jwtTokenUtil.generateToken(user);


            JsonObject jsonObject = new JsonObject();

                AuthorityCode authorityCode = new AuthorityCode();
                authorityCode.setEmail(company.getEmail());
                authorityCode.setCode(genRandomNum());
                authorityCode.setType(user.getType());
                authorityCode.setCreate_time(new Date());
                if (authorityCodeService.addVerify(authorityCode)>0){
                    JsonObject jsonVerify = new JsonObject();
                    jsonVerify.addProperty("email",authorityCode.getEmail());
                    jsonVerify.addProperty("code",authorityCode.getCode());
                    String verifyCode = new String(Base64.encode(jsonVerify.toString().getBytes()));
                    mailService.send(verifyCode, user.getPassword(), "邮件验证", "message2.ftl");
                    result.put("success",true);
                }else{
                    result.put("success",false);
                    result.put("msg","数据库错误,验证信息添加失败");
                }
            return ResponseEntity.ok(result);
        }else{
            result.put("success",false);
            return ResponseEntity.ok(result);
        }

    }


    /**
     * 获取企业邮箱返回前台
     * @param request
     * @return
     */
    @GetMapping("/company/getEmail")
    public ResponseEntity<?> getEmail(HttpServletRequest request){
        //获取token
        String token=request.getHeader(tokenHeader).substring(7);
        //从token解析出用户名
        String username=jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user=(JwtUser)userDetailsService.loadUserByUsername(username);
        String email=companyService.findEmailByUserid(user.getId());
        Map<String,Object> result=new HashMap<>();
        result.put("email",email);
        return ResponseEntity.ok(result);
    }

    /**
     * 获取企业邮箱返回前台
     * @param request
     * @return
     */
    @GetMapping("/company/getCompanyName")
    public ResponseEntity<?> getCompanyName(HttpServletRequest request){
        //获取token
        String token=request.getHeader(tokenHeader).substring(7);
        //从token解析出用户名
        String username=jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user=(JwtUser)userDetailsService.loadUserByUsername(username);
        String companyName=companyService.findByUserid(user.getId()).getCompanyname();
        Map<String,Object> result=new HashMap<>();
        result.put("companyName",companyName);
        return ResponseEntity.ok(result);
    }



    /**
     * 重新填写
     * @param request
     * @return
     */
    @DeleteMapping("/company/doagain")
    public ResponseEntity<?> doagain(HttpServletRequest request){
        //获取token
        String token=request.getHeader(tokenHeader).substring(7);
        //从token解析出用户名
        String username=jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user=(JwtUser)userDetailsService.loadUserByUsername(username);
        int rows=companyService.delCompany(user.getId());
        mailService.changeState(user.getEmail(),1);
        return ResponseEntity.ok(true);
    }

    /**
     * 企业邮箱认证
     * @param verify
     * @return
     */
    @PostMapping("auth/company/verify")
    public ResponseEntity<?> zhuceMail(@RequestParam("verify") String verify){
        JsonObject jsonObject = new JsonObject();
        JsonObject jsonVerify;
        jsonVerify = new Gson().fromJson(new String(Base64.decode(verify)),JsonObject.class);
        String email = jsonVerify.get("email").getAsString();
        String code = jsonVerify.get("code").getAsString();
        AuthorityCode authorityCode = authorityCodeService.verify(email,code);
        if (authorityCode==null){
            jsonObject.addProperty("status",402);
            jsonObject.addProperty("message","验证失败,验证码错误");
        }else{
            Integer type = authorityCode.getType();
            Users user = new Users();
            user.setEmail(email);
            user.setType(type);
            mailService.changeState(user.getEmail(),1);
            //mailService.changePassword(user.getEmail(),passwordEncoder.encode(user.getPassword()));
            mailService.changeState(user.getEmail(),4);
            jsonObject.addProperty("status",200);
            jsonObject.addProperty("message","验证成功");
        }
        return ResponseEntity.ok(jsonObject.toString());
    }


    @PutMapping("/company/changedetailed")
    public ResponseEntity<?> changeDetailed(Company company,
                                            HttpServletRequest request){//companyid 现在是默认值1 后续修改
        //获取token
        String token=request.getHeader(tokenHeader).substring(7);
        //从token解析出用户名
        String username=jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user=(JwtUser)userDetailsService.loadUserByUsername(username);
        company.setCompanyid(companyService.findCompanyid(user.getId()));
        mailService.changeState(user.getEmail(),7);
        return ResponseEntity.ok(companyService.changeDetailed(company));
    }

    @PutMapping("/company/changeinformation")
    public ResponseEntity<?> changeInformation(Company company,
                                               @RequestParam("stagelist")String stagelist,
                                               HttpServletRequest request){

        //获取token
        String token=request.getHeader(tokenHeader).substring(7);
        //从token解析出用户名
        String username=jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user=(JwtUser)userDetailsService.loadUserByUsername(username);
        company.setCompanyid(companyService.findCompanyid(user.getId()));
        mailService.changeState(user.getEmail(),5);
        return ResponseEntity.ok(companyService.changeCinformation(company,stagelist,username));
    }

    @PostMapping("/company/test")
    public ResponseEntity<?> changeInformation(){
        return ResponseEntity.ok("ok");
    }

    @PostMapping("/company/resendOpenService")
    public ResponseEntity<?> resendOpenService(HttpServletRequest request){

        JsonObject jsonObject = new JsonObject();

        //获取token
        String token=request.getHeader(tokenHeader).substring(7);
        //从token解析出用户名
        String username=jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user=(JwtUser)userDetailsService.loadUserByUsername(username);
        Company company=companyService.findByUserid(user.getId());
        AuthorityCode authorityCode = new AuthorityCode();
        authorityCode.setEmail(company.getEmail());
        authorityCode.setCode(genRandomNum());
        authorityCode.setType(user.getType());
        authorityCode.setCreate_time(new Date());
        Map<String,Object> result=new HashMap<>();
        if (authorityCodeService.addVerify(authorityCode)>0){
            JsonObject jsonVerify = new JsonObject();
            jsonVerify.addProperty("email",authorityCode.getEmail());
            jsonVerify.addProperty("code",authorityCode.getCode());
            String verifyCode = new String(Base64.encode(jsonVerify.toString().getBytes()));
            mailService.send(verifyCode, user.getPassword(), "邮件验证", "message2.ftl");
            result.put("success",true);
        }else{
            result.put("success",false);
            result.put("msg","数据库错误,验证信息添加失败");
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/company/findCinformation")
    public ResponseEntity<?> findCinformation(HttpServletRequest request){
        //获取token
        String token=request.getHeader(tokenHeader).substring(7);
        //从token解析出用户名
        String username=jwtTokenUtil.getUsernameFromToken(token);
        return ResponseEntity.ok(myhomeService.findCinformation(username));
    }


}
