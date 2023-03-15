package com.ll.basic1;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Controller
public class HomeController {

    private int i = 0;
    private int countCookie = 0;
    private Map<Integer, Dto> list = new HashMap<>();

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

    //-- 파라미터로 이름, 나이 받아오기 --//
    // http://localhost:8080/home/addPerson?name=임꺽정&age=22
    @GetMapping("/home/addPerson")
    @ResponseBody
    public String showMain2(
            @RequestParam String name,
            @RequestParam int age
    ) {
        i++;
        Dto dto = new Dto();
        dto.setId(i);
        dto.setName(name);
        dto.setAge(age);

        list.put(i, dto);
        String log = i + "번 사람이 추가되었습니다.";
        return log;
    }

    //-- 받아온 파라미터 확인하기 --//
    // http://localhost:8080/home/people
    @GetMapping("/home/people")
    @ResponseBody
    public Map<Integer, Dto> showMain3() {

        return list;
//        return list;
    }

    //-- 특정 파라미터 삭제 --//
    // http://localhost:8080/home/updatePerson?id=2
    @GetMapping("/home/updatePerson")
    @ResponseBody
    public Map<Integer, Dto> showMain5(
            @RequestParam int id
    ) {
        list.remove(id);
        return list;
    }

    //-- 특정 파라미터 수정 --//
    // http://localhost:8080/home/removePerson?id=2&name=홍길동&age=40
    @GetMapping("/home/removePerson")
    @ResponseBody
    public Map<Integer, Dto> showMain6(
            @RequestParam int id,
            @RequestParam String name,
            @RequestParam int age
    ) {
        Dto dto = list.get(id);
        dto.setName(name);
        dto.setAge(age);
        list.put(id, dto);
        return list;
    }
}

@Getter
@Setter
class Dto {
    private int id;
    private String name;
    private int age;
}
