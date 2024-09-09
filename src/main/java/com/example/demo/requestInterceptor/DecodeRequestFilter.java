package com.example.demo.requestInterceptor;

import com.example.demo.controller.SalarySurveyController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class DecodeRequestFilter implements HandlerInterceptor {
    private static final Logger logger = Logger.getLogger(DecodeRequestFilter.class.getName());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        logger.log(Level.INFO, "Pre Handler");
        String queryString = request.getQueryString();

        logger.log(Level.INFO, "queryString: " + queryString);
        if (queryString != null && (queryString.contains("%5B") || queryString.contains("%5D"))) {
            String decodedQuery = URLDecoder.decode(queryString, StandardCharsets.UTF_8);
            String requestURL = request.getRequestURL().toString() + "?" + decodedQuery;

            logger.log(Level.INFO, "Request URL:: " + requestURL);
            request.setAttribute("queryString", requestURL);

            return true;
        }
        return true;
    }
}