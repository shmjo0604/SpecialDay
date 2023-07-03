package com.example.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.filter.UrlFilter;

@Configuration
public class FilterConfig {
    
    @Bean
    public FilterRegistrationBean<UrlFilter> filterRegistrationBean(UrlFilter urlFilter) {

        FilterRegistrationBean<UrlFilter> filterReg = new FilterRegistrationBean<>();
        filterReg.setFilter(urlFilter);

        filterReg.addUrlPatterns("/member/*");
        filterReg.addUrlPatterns("/class/*");
        filterReg.addUrlPatterns("/apply/*");
        filterReg.addUrlPatterns("/classunit/*");
        filterReg.addUrlPatterns("/community/*");

        return filterReg;
    }

}
