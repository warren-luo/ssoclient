package com.warrenluo.ssoclient.demos.web.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class CallbackController {
    @GetMapping("callback")
    public String callback(@RequestParam String token, @RequestParam String next, HttpServletResponse response, HttpSession session) {
        // 设置 JWT 到 Cookie 和 Session
        Cookie cookie = new Cookie("token", token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(3600); // 1 hour
        response.addCookie(cookie);

        session.setAttribute("token", token);

        // 重定向到用户请求的原始页面
        return "redirect:" + next;
    }
    
}
