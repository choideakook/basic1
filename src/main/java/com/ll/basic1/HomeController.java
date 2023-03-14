package com.ll.basic1;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    private int i = 0;
        private Map<Integer, Dto> list = new HashMap<>();
//    private List<Dto> list = new ArrayList<>();

    //-- 새로고침 할 때 숫자 증가 시키기 --//
    // http://localhost:8080/home/increase
    @GetMapping("/home/increase")
    @ResponseBody
    public String showMain4() {
        i++;
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
//        list.add(dto);
        String log = i + "번 사람이 추가되었습니다.";
        return log;
    }

    //-- 받아온 파라미터 확인하기 --//
    // http://localhost:8080/home/people
    @GetMapping("/home/people")
    @ResponseBody
    public List<Dto> showMain3() {
        List<Dto> result = new ArrayList<>();

        for (Dto value : list.values()) {
            result.add(value);
        }

        return result;
//        return list;
    }

    //-- 특정 파라미터 삭제 --//
    // http://localhost:8080/home/removePerson?id=2
    @GetMapping("/home/removePerson")
    @ResponseBody
    public Map<Integer, Dto> showMain5(
            @RequestParam int id
    ) {
        list.remove(id);
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
