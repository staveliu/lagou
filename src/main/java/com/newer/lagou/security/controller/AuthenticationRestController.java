package com.newer.lagou.security.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.newer.lagou.controller.MailController;
import com.newer.lagou.domain.Users;
import com.newer.lagou.security.JwtTokenUtil;
import com.newer.lagou.security.domain.AuthenticationException;
import com.newer.lagou.security.domain.JwtAuthenticationRequest;
import com.newer.lagou.security.domain.JwtAuthenticationResponse;
import com.newer.lagou.security.domain.JwtUser;
import com.newer.lagou.service.MailService;
import com.newer.lagou.util.HttpUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.bouncycastle.util.encoders.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

/**
 * 授权控制器
 *
 *
 */
@RestController
public class AuthenticationRestController {

  @Value("${jwt.header}")
  private String tokenHeader;

  @Autowired
  private MailService mailService;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  @Autowired
  @Qualifier("jwtUserService")
  private UserDetailsService userDetailsService;

  /**
   * 创建授权令牌 (登录)
   *
   * @param authenticationRequest
   * @return
   * @throws AuthenticationException
   */
  @RequestMapping(value = "${jwt.route.authentication.path}", method = RequestMethod.POST)
  public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException {

    System.out.println("jinru----controller");

    authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

    // Reload password post-security so we can generate the token
    final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
    final String token = jwtTokenUtil.generateToken(userDetails);

    // Return the token
    return ResponseEntity.ok(new JwtAuthenticationResponse(token));
  }

  @GetMapping("auth/wxauth")
  public ResponseEntity<?> wxauth(@RequestParam("code") String code){
    JsonObject jsonObject = new JsonObject();
    String host = "http://lagou.bjwch.net.cn/";
    String path = "wx_poll.php";
    String method = "GET";
    Map<String, String> headers = new HashMap<String, String>();
    Map<String, String> querys = new HashMap<String, String>();
    querys.put("verify", code);
    System.out.println(code);

    try {
      HttpResponse resp = HttpUtils.doGet(host, path, method, headers, querys);
      JsonObject retJson;
      String body = EntityUtils.toString(resp.getEntity());
      System.out.println(body);
      retJson = new Gson().fromJson(body,JsonObject.class);
      if (!retJson.has("status")){
        jsonObject.addProperty("status",true);
        String email = new String(Base64.encode(retJson.get("nickname").getAsString().getBytes()))+"@wechat.auth";

        Users user = new Users();
        user.setType(0);
        user.setEmail(email);
        user.setPassword(new String(Base64.encode((MailController.genRandomNum()+new Date().toString()).getBytes())));
        user.setBy1(retJson.get("openid").getAsString());
        user.setBy2(user.getPassword());
        if (mailService.adduser(user)){
          mailService.addAuthority(user,user.getType());
          jsonObject.addProperty("type","new");

          authenticate(user.getEmail(), user.getPassword());

          // Reload password post-security so we can generate the token
          final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
          final String token = jwtTokenUtil.generateToken(userDetails);
          jsonObject.addProperty("token",token);
          // Return the token
        }else{
          Users users = mailService.findByOpenid(retJson.get("openid").getAsString());
          authenticate(users.getEmail(), users.getBy2());

          // Reload password post-security so we can generate the token
          final UserDetails userDetails = userDetailsService.loadUserByUsername(users.getEmail());
          final String token = jwtTokenUtil.generateToken(userDetails);
          jsonObject.addProperty("token",token);
        }


        jsonObject.addProperty("status",true);
      }else{
        jsonObject.addProperty("status",false);
      }
      //获取response的body
      //System.out.println(EntityUtils.toString(response.getEntity()));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return ResponseEntity.ok(jsonObject.toString());
  }

  /**
   * 刷新
   *
   * @param request
   * @return
   */
  @RequestMapping(value = "${jwt.route.authentication.refresh}", method = RequestMethod.GET)
  public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
    String authToken = request.getHeader(tokenHeader);
    final String token = authToken.substring(7);
    String username = jwtTokenUtil.getUsernameFromToken(token);
    JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);

    if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
      String refreshedToken = jwtTokenUtil.refreshToken(token);
      return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
    } else {
      return ResponseEntity.badRequest().body(null);
    }
  }

  @ExceptionHandler({AuthenticationException.class})
  public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
  }

  /**
   * Authenticates the user. If something is wrong, an {@link AuthenticationException} will be thrown
   */
  private void authenticate(String username, String password) {
    System.out.println(username+"---"+password);
    Objects.requireNonNull(username);
    Objects.requireNonNull(password);

    try {
      UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
      System.out.println("77");
      authenticationManager.authenticate(usernamePasswordAuthenticationToken);
      System.out.println("jiaoyantongguo");
    } catch (DisabledException e) {
      throw new AuthenticationException("User is disabled!", e);
    } catch (BadCredentialsException e) {
      throw new AuthenticationException("Bad credentials!", e);
    }
  }

}
