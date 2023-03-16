package com.ll.basic1.cookie;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

/**
 *  쿠키 관련 로직
 */
@Component
@RequestScope
@RequiredArgsConstructor
public class Rq {

    private final HttpServletRequest req;
    private final HttpServletResponse resp;

    //-- 쿠키 생성 --//
    public void createCookie(String username) {

        resp.addCookie(new Cookie("yes", username));
    }

    //-- 쿠키 확인 --//
    public String findCookie() {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                String value = cookie.getValue();
                if (name.equals("yes")) {
                    return value;
                }
            }
        }
        return "";
    }

    //-- 쿠키 삭제 --//
    public void deleteCookie() {
        Cookie cookie = new Cookie("yes", null);
        cookie.setMaxAge(0);
        resp.addCookie(cookie);

    }
}
