package com.example.demo.requestInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
public class ModifyRequestUrl implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        System.out.println("Pre Handler");
        String queryString = request.getQueryString();
        System.out.println("queryString: " + queryString);
        if (queryString != null && (queryString.contains("[") || queryString.contains("]"))) {
            String fixedQueryString = fixQueryString(queryString);
            String requestURL = request.getRequestURL().toString() + "?" + fixedQueryString;
            System.out.println("Request URL: " + requestURL);

            response.sendRedirect(requestURL);
            return true;
        }
        return true;
    }

    private String fixQueryString(String queryString) throws UnsupportedEncodingException {
        String decodedQuery = URLDecoder.decode(queryString, StandardCharsets.UTF_8);
        String encodedQuery = URLEncoder.encode(decodedQuery, StandardCharsets.UTF_8);
        return encodedQuery.replace("%3D", "=").replace("%26", "&").replace("%2C", ",");
    }
}