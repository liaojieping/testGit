package com.cache.springbootcache.druidDateSourceConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
 @EnableResourceServer //开启资源提供服务的配置  是默认情况下spring security oauth2的http配置   会被WebSecurityConfigurerAdapter的配置覆盖
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
     public void configure(HttpSecurity http) throws Exception {
                http
                         .authorizeRequests()
                         .antMatchers("/**").permitAll();
           }
}
