package com.warrenluo.ssoclient.demos.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
        
        // 重定向到 SSO 登录页面，并带上当前 URL 作为 next 参数
        String redirectUrl = "http://127.0.0.1:5000/login?next=" + request.getRequestURL();
        response.sendRedirect(redirectUrl);
    }
    
}
