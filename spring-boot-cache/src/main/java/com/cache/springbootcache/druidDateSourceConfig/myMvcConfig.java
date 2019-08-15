package com.cache.springbootcache.druidDateSourceConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*Web应用程序基于Spring MVC。因此，您需要配置Spring MVC并设置视图控制器以公开这些模板。这是在应用程序中配置Spring MVC的配置类。*/
@Configuration
public class myMvcConfig implements WebMvcConfigurer {
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/login").setViewName("login");
    }
}
