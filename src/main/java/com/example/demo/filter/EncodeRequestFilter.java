package com.example.demo.filter;

import com.example.demo.controller.SalarySurveyController;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import java.util.logging.Level;
import java.util.logging.Logger;

public class EncodeRequestFilter implements Filter {

    private static final Logger logger = Logger.getLogger(EncodeRequestFilter.class.getName());

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        logger.log(Level.INFO, "doFilter");

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String queryString = httpRequest.getQueryString();

        if (queryString != null && (queryString.contains("[") || queryString.contains("]"))) {
            String fixedQueryString = queryString.replace("[", URLEncoder.encode("[", StandardCharsets.UTF_8))
                    .replace("]", URLEncoder.encode("]", StandardCharsets.UTF_8));


            HttpServletRequestWrapper requestWrapper = new HttpServletRequestWrapper(httpRequest) {
                @Override
                public String getQueryString() {
                    return fixedQueryString;
                }
            };
            chain.doFilter(requestWrapper, response);
        } else {
            chain.doFilter(request, response);
        }
    }
}
