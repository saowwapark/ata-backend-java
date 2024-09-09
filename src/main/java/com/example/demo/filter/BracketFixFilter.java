package com.example.demo.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


public class BracketFixFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("doFilter");
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String queryString = httpRequest.getQueryString();

        if (queryString != null && (queryString.contains("[") || queryString.contains("]"))) {
            String fixedQueryString = queryString.replace("[", URLEncoder.encode("[", StandardCharsets.UTF_8))
                    .replace("]", URLEncoder.encode("]", StandardCharsets.UTF_8));
            System.out.println("fixedQueryString: " + fixedQueryString);


            HttpServletRequestWrapper requestWrapper = new HttpServletRequestWrapper(httpRequest) {
                @Override
                public String getQueryString() {
                    return fixedQueryString;
                }

                @Override
                public String getRequestURI() {
                    System.out.println("getRequestURI: " + super.getRequestURI() + "?" + fixedQueryString);
                    return super.getRequestURI() + "?" + fixedQueryString;
                }
            };

            chain.doFilter(requestWrapper, response);
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization logic if needed
    }

    @Override
    public void destroy() {
        // Cleanup logic if needed
    }
}
