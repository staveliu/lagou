package com.newer.lagou.security.conf;


import com.newer.lagou.security.JwtAuthenticationTokenFilter;
import com.newer.lagou.security.JwtTokenUtil;
import com.newer.lagou.security.domain.JwtAuthenticationEntryPoint;
import com.newer.lagou.security.service.JwtUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Web 安全配置类
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private JwtAuthenticationEntryPoint unauthorizedHandler;

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  @Autowired
  private JwtUserService jwtUserService;

  @Value("${jwt.header}")
  private String tokenHeader;

  @Value("${jwt.route.authentication.path}")
  private String authenticationPath;

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth
        .userDetailsService(jwtUserService)
        .passwordEncoder(passwordEncoderBean());
  }

  @Bean
  public PasswordEncoder passwordEncoderBean() {
    return new BCryptPasswordEncoder();
  }


  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }




  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
        // we don't need CSRF because our token is invulnerable
        .csrf().disable()
        .cors().and() // 跨域

        .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()

        // don't create session
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

        .authorizeRequests()

        .antMatchers("/auth/**").permitAll()
            .antMatchers("/test/**").permitAll()
        .anyRequest().authenticated();

    // Custom JWT based security filter
    JwtAuthenticationTokenFilter authenticationTokenFilter=new JwtAuthenticationTokenFilter(userDetailsService(), jwtTokenUtil, tokenHeader);
    httpSecurity
        .addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

    // disable page caching
    httpSecurity
        .headers()
        .frameOptions().sameOrigin()  // required to set for H2 else H2 Console will be blank.
        .cacheControl();
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    // AuthenticationTokenFilter will ignore the below paths
    web
        .ignoring()
        .antMatchers(
            HttpMethod.POST,
            authenticationPath//auth
        )

        // allow anonymous resource requests
        .and()
        .ignoring()
        .antMatchers(
            HttpMethod.GET,
            "/",
            "/*.html",
            "/favicon.ico",
            "/**/*.html",
            "/**/*.css",
            "/**/*.js"
        );
  }
}
