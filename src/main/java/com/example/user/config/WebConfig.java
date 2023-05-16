package com.example.user.config;

import com.example.user.filter.UserFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {
    @Bean
    public FilterRegistrationBean<UserFilter> userFilterRegistration() {
        FilterRegistrationBean<UserFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new UserFilter());
        registration.addUrlPatterns("/admin.html", "/search.html", "/add.html");
        return registration;
    }

    @Bean
    public UserFilter userFilter() {
        return new UserFilter();
    }
}

