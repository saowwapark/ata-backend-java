package com.example.demo.config;

import com.example.demo.filter.EncodeRequestFilter;
import com.example.demo.requestInterceptor.DecodeRequestFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final DecodeRequestFilter decodeRequestFilter;

    WebConfig(DecodeRequestFilter decodeRequestFilter) {
        this.decodeRequestFilter = decodeRequestFilter;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(decodeRequestFilter);
    }

    @Bean
    public FilterRegistrationBean<EncodeRequestFilter> bracketFixFilter() {
        System.out.println("bracketFixFilter");
        FilterRegistrationBean<EncodeRequestFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new EncodeRequestFilter());
        registrationBean.addUrlPatterns("/*"); // Apply filter to all URLs

        return registrationBean;
    }


}
