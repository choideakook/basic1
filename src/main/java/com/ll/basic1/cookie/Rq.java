package com.ll.basic1.cookie;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
@RequiredArgsConstructor
public class Rq {

    private final HttpServletRequest req;
    private final HttpServletResponse resp;

    /**
     * 세션 관련 로직
     */

    public boolean isLogined() {
        long loginedMemberId = getSessionAsLong("loginedMemberId", 0);
        return loginedMemberId > 0;
    }

    public boolean isLogout() {
        return !isLogined();
    }

    //-- 세션 생성 --//
    public void setSession(String name, long value) {
        HttpSession session = req.getSession();
        session.setAttribute(name, value);
    }

    //-- 세션 조회 V.long --//
    public long getSessionAsLong(String name, long defaultValue) {
        try {
            long value = (long) req.getSession().getAttribute(name);
            return value;
        } catch (Exception e) {
            return defaultValue;
        }
    }

    //-- 세션 조회 V.String --//
    private String getSessionAsStr(String name, String defaultValue) {
        try {
            String value = (String) req.getSession().getAttribute(name);
            if (value == null) return defaultValue;
            return value;
        } catch (Exception e) {
            return defaultValue;
        }
    }

    //-- 세션 삭제 --//
    public boolean removeSession(String name) {
        HttpSession session = req.getSession();

        if (session.getAttribute(name) == null)
            return false;

        session.removeAttribute(name);
        return true;
    }

    /**
     *  쿠키 관련 로직
     */
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

    public String getSessionDebugContents() {
        return "";
    }
}
