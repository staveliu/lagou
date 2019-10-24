package com.newer.lagou.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.newer.lagou.domain.AuthorityCode;
import com.newer.lagou.domain.Users;
import com.newer.lagou.service.AuthorityCodeService;
import com.newer.lagou.service.MailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.bouncycastle.util.encoders.Base64;

import java.util.Date;
import java.util.Random;

/**
 * @author shining
 */
@RestController
public class MailController {

    @Autowired
    private MailService mailService;
    @Autowired
    private AuthorityCodeService authorityCodeService;

    @Autowired
    private PasswordEncoder passwordEncoder;




    public static String genRandomNum(){
        int  maxNum = 36;
        int i;
        int count = 0;
        char[] str = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
                'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
                'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
        StringBuffer pwd = new StringBuffer("");
        Random r = new Random();
        while(count < 8){
            i = Math.abs(r.nextInt(maxNum));
            if (i >= 0 && i < str.length) {
                pwd.append(str[i]);
                count ++;
            }
        }
        return pwd.toString();
    }



    @GetMapping("auth/send")
    public ResponseEntity<?> sendMail(Users user){
        JsonObject jsonObject = new JsonObject();

        //调用邮件发送服务 0未激活 1已激活注册未填信息 2已激活且填好信息d
        if (mailService.findState(user)==1){
            jsonObject.addProperty("status",401);
            jsonObject.addProperty("message","邮件已被注册");
        }else {
            AuthorityCode authorityCode = new AuthorityCode();
            authorityCode.setEmail(user.getEmail());
            authorityCode.setCode(genRandomNum());
            authorityCode.setType(user.getType());
            authorityCode.setCreate_time(new Date());
            if (authorityCodeService.addVerify(authorityCode)>0){
                JsonObject jsonVerify = new JsonObject();
                jsonVerify.addProperty("email",authorityCode.getEmail());
                jsonVerify.addProperty("code",authorityCode.getCode());
                String verifyCode = new String(Base64.encode(jsonVerify.toString().getBytes()));
                mailService.send(verifyCode, user.getPassword(), "邮件验证", "message.ftl");
                jsonObject.addProperty("status", 200);
                jsonObject.addProperty("message", "邮件发送成功");
            }else{
                jsonObject.addProperty("status", 500);
                jsonObject.addProperty("message", "数据库错误,验证信息添加失败");
            }
//            jsonObject.addProperty("status", 200);
//            jsonObject.addProperty("message", "邮件发送成功");
//            System.out.println(user.getEmail() + "已发送");
//            mailService.send(user, "邮件验证", "message.ftl");
        }
        return ResponseEntity.ok(jsonObject.toString());
    }

    @PostMapping("auth/verify")
    public ResponseEntity<?> zhuceMail(@RequestParam("verify") String verify){
        JsonObject jsonObject = new JsonObject();
        JsonObject jsonVerify;
        jsonVerify = new Gson().fromJson(new String(Base64.decode(verify)),JsonObject.class);
        String email = jsonVerify.get("email").getAsString();


        String code = jsonVerify.get("code").getAsString();
        System.out.println(email+"ee1e1e==="+code);
        System.out.println(email+"====="+code);
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
            mailService.addAuthority(user,user.getType());
            jsonObject.addProperty("status",200);
            jsonObject.addProperty("message","验证成功");
        }
        return ResponseEntity.ok(jsonObject.toString());
    }

    @GetMapping("auth/wxqrcode")
    public ResponseEntity<?> wxqrcode(){
        JsonObject jsonObject = new JsonObject();
        String code = genRandomNum();
        System.out.println(code);
        jsonObject.addProperty("status",true);
        jsonObject.addProperty("message","成功获取");
        jsonObject.addProperty("url","http://lagou.bjwch.net.cn/wxapi.php?code="+code);
        jsonObject.addProperty("code",code);
        return ResponseEntity.ok(jsonObject.toString());
    }
//    @GetMapping("auth/wxauth")
//    public ResponseEntity<?> wxauth(@RequestParam("code") String code){
//        JsonObject jsonObject = new JsonObject();
//        String host = "http://lagou.bjwch.net.cn/";
//        String path = "wx_poll.php";
//        String method = "GET";
//        Map<String, String> headers = new HashMap<String, String>();
//        Map<String, String> querys = new HashMap<String, String>();
//        querys.put("verify", code);
//        System.out.println(code);
//
//        try {
//            HttpResponse resp = HttpUtils.doGet(host, path, method, headers, querys);
//            JsonObject retJson;
//            String body =EntityUtils.toString(resp.getEntity());
//            System.out.println(body);
//            retJson = new Gson().fromJson(body,JsonObject.class);
//            if (!retJson.has("status")){
//                jsonObject.addProperty("status",true);
//                String email = new String(Base64.encode(retJson.get("nickname").getAsString().getBytes()))+"@wechat.auth";
//
//                Users user = new Users();
//                user.setType(0);
//                user.setEmail(email);
//                user.setPassword(genRandomNum()+new Date().toString());
//                user.setBy2(retJson.get("openid").toString());
//                if (mailService.adduser(user)){
//                    mailService.addAuthority(user,user.getType());
//                    jsonObject.addProperty("type","new");
//
//                }else{
//
//                }
//
//
//                jsonObject.addProperty("status",true);
//            }else{
//                jsonObject.addProperty("status",false);
//            }
//            //获取response的body
//            //System.out.println(EntityUtils.toString(response.getEntity()));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return ResponseEntity.ok(jsonObject.toString());
//    }
    @GetMapping("auth/role")
    public ResponseEntity<?> userAuth(Users user ){
        System.out.println(user);

        return ResponseEntity.ok(mailService.userAuth(user.getEmail()));
    }
}
