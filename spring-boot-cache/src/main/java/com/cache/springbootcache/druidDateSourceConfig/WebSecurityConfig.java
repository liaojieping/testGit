package com.cache.springbootcache.druidDateSourceConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
/**
 26      * 获取用户的验证配置类
 27      */
    @Resource(name = "signUserDetailService")
     private UserDetailsService userDetailsService;
     /**
       * 加密配置
       */
     @Autowired
     private PasswordEncoder passwordEncoder;

             /**
       * 密码验证处理器
       */
             @Resource(name = "myCustomerAuthenticationProvider")
     private CustomerAuthenticationProvider customerAuthenticationProvider;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()     //认证请求
                .antMatchers("/", "/home").permitAll()  //路径匹配，这行表示允许所有人访问
                .anyRequest().authenticated()  //这些路径必须有必须认证才能访问
                .and()
                .formLogin()  //开启自动配置的登陆功能
                .loginPage("/login")//来到登录页
                .permitAll()
                .usernameParameter("username")
                .passwordParameter("password")
                .and()
                .csrf().disable()//防止跨站请求  spring security中默认开启
                .logout()
                .permitAll();
    }

    /*@Bean  将用户信息存在了内存里
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("password")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }*/
    /**
     63      * 权限管理器  AuthorizationServerConfigurerAdapter认证中心需要的AuthenticationManager需要
     64      */

     protected void configure(AuthenticationManagerBuilder auth) throws Exception {
                 //目的是为了前端获取数据时获取到整个form-data的数据,提供验证器
                auth.authenticationProvider(customerAuthenticationProvider);
                //配置登录user验证处理器  以及密码加密器  好让认证中心进行验证
                auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
             }

             /**
 74      * 需要配置这个支持password模式
 75      * support password grant type
 76      *
 77      * @return
 78      * @throws Exception
 79      */
             @Override
             @Bean
              public AuthenticationManager authenticationManagerBean() throws Exception {
            return authenticationManager();
          }
}
