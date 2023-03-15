package com.ll.basic1;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
public class HomeController {

    private int i = 0;
    private int countCookie = 0;


    //-- 쿠키 확인 --//
    // http://localhost:8080/home/reqAndResp
    @GetMapping("/home/reqAndResp")
    @ResponseBody
    public int showReqAndResp(HttpServletRequest req, HttpServletResponse resp) {
        // 고객의 쿠키에서 count 를 찾아서 i 에 저장
        if (req.getCookies() != null) {
            countCookie = Arrays.stream(req.getCookies())
                    .filter(cookie -> cookie.getName().equals("count"))
                    .map(Cookie::getValue)
                    .mapToInt(Integer::parseInt)
                    .findFirst()
                    .orElse(0);
        }
        int newCountCookie = countCookie + 1;
        resp.addCookie(new Cookie("count", newCountCookie + ""));
        return newCountCookie;
    }


    //-- 새로고침 할 때 숫자 증가 시키기 --//
    // http://localhost:8080/home/increase
    @GetMapping("/home/increase")
    @ResponseBody
    public String showMain4() {
        ++i;
        return "응답 : " + i;
    }

    //-- Param 값으로 연산하기 --//
    // http://localhost:8080/home/plus?a=3&b=2
    @GetMapping("/home/plus")
    @ResponseBody
    public int showMain(
            @RequestParam int a,
            @RequestParam int b
    ) {
        return a + b;
    }
}
