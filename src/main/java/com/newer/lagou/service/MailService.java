package com.newer.lagou.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.newer.lagou.domain.Users;
import com.newer.lagou.domain.VerifyMail;
import com.newer.lagou.mapper.UsersMapper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Configuration configuration;

    @Value("${spring.mail.username}")
    private String username;

    @Autowired
    private UsersMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 发送邮件的参数
     * @param verifyCode 动态数据
     * @param title 标题
     * @param templateName 模版名
     */
    public void send(String verifyCode,String password, String title, String templateName){
        JsonObject jsonObject = new Gson().fromJson(new String(Base64.decode(verifyCode)),JsonObject.class);
        if (!isExistEmail(jsonObject.get("email").getAsString())){
            userMapper.addUser(jsonObject.get("email").getAsString());
            userMapper.changePassword(passwordEncoder.encode(password),jsonObject.get("email").getAsString());

        }
        MimeMessage mimeMessage=javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper=new MimeMessageHelper(mimeMessage,true);

            helper.setFrom(username+"@qq.com");
            helper.setTo(InternetAddress.parse(jsonObject.get("email").getAsString()));
            helper.setSubject("["+title+"]"+ LocalDate.now()+" "+ LocalTime.now().withNano(0));

            //配置模板
            Template template=configuration.getTemplate(templateName);
            //准备模型数据
            Map<String,Object> map=new HashMap<>();
            VerifyMail verifyMail = new VerifyMail();
            verifyMail.setEmail(jsonObject.get("email").getAsString());
            verifyMail.setVerifyCode(verifyCode);
            map.put("verifyMail",verifyMail);
            //将模板引擎和动态数据渲染成html字符串
            String text= FreeMarkerTemplateUtils.processTemplateIntoString(template,map);

            helper.setText(text,true);
            //发送邮件
            javaMailSender.send(mimeMessage);

        }catch (MessagingException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    //是否已存在数据库
    public boolean isExistEmail(String email){
        System.out.println(email);
        List<Users> users=userMapper.findAll();
        for (Users user : users) {
            if (email.equals(user.getEmail()))
                return true;//邮件存在于数据库
        }
        return false;
    }

    //查看该邮箱状态
    public int findState(Users user){
        System.out.println("查看邮箱状态："+user);
        System.out.println(user.getEmail());
        Users user2=userMapper.findByEmail2(user.getEmail());
        System.out.println("user2"+user2);
        if(user2==null)return 3;
        System.out.println("邮箱状态是："+user2.getState());
        return user2.getState();
    }

    //改变用户状态
    public void changeState(String email,int state){
        System.out.println(email+"---状态："+state);
        userMapper.changeState(state,email);
    }

    //修改密码
    public void changePassword(String email,String password){
        System.out.println(email+"--密码:"+password);
        userMapper.changePassword(password,email);
    }

    //添加权限
    public void addAuthority(Users user,int type){
        Users user2=userMapper.findByEmail2(user.getEmail());
        userMapper.addUserAuthority(user2.getId(),type);
    }

    public Integer userAuth(String email){
        Users users=userMapper.findByEmail(email);
        System.out.println(users);
        if("ROLE_JOBHUNTER".equals(users.getAuthority().get(0).getName())){
            return 1;
        }else {
            if(users.getState()==1){
                return 2;
            }else{
                return 3;
            }
        }
    }

}
