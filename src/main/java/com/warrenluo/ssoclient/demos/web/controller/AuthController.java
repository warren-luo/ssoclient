package com.warrenluo.ssoclient.demos.web.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {
    @GetMapping("/login")
    public String login(@RequestParam(required = false) String token, HttpServletResponse response,
            HttpSession session) {
        if (token != null) {
            // 如果从 SSO 系统中获取到了 token，将其存储在 Cookie 或 Session 中
            Cookie cookie = new Cookie("token", token);
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            cookie.setMaxAge(3600);
            response.addCookie(cookie);

            session.setAttribute("token", token);

            return "redirect:/user";
        }

        return "redirect:http://127.0.0.1:5000/login?next=http://localhost:8080/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletResponse response, HttpSession session) {
        Cookie cookie = new Cookie("token", null);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        session.removeAttribute("token");

        return "redirect:/";
    }
}
