package com.example.demo.config;

import com.example.demo.filter.BracketFixFilter;
import com.example.demo.requestInterceptor.ModifyRequestUrl;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    /*
    private final ModifyRequestUrl modifyRequestUrl;

    WebConfig(ModifyRequestUrl modifyRequestUrl) {
        this.modifyRequestUrl = modifyRequestUrl;
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(modifyRequestUrl);
    }
    */

    @Bean
    public FilterRegistrationBean<BracketFixFilter> bracketFixFilter() {
        System.out.println("bracketFixFilter");
        FilterRegistrationBean<BracketFixFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new BracketFixFilter());
        registrationBean.addUrlPatterns("/*"); // Apply filter to all URLs

        return registrationBean;
    }


}
