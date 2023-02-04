package com.hackaton.project.configurations;

import com.hackaton.project.filters.JWTInterceptorFilter;
import com.hackaton.project.utils.JwtUtilImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Autowired
    private JwtUtilImpl jwtUtil;
    @Bean
    public FilterRegistrationBean<JWTInterceptorFilter> someFilterRegistration() {
        FilterRegistrationBean<JWTInterceptorFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new JWTInterceptorFilter(this.jwtUtil));
        registration.addUrlPatterns("/*");
        registration.addInitParameter("paramName", "paramValue");
        registration.setName("someFilter");
        registration.setOrder(1);
        return registration;
    }
}
