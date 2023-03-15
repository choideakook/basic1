package com.ll.basic1.controller;

import com.ll.basic1.entity.Member;
import com.ll.basic1.entity.MemberDto;
import com.ll.basic1.entity.UpdateDto;
import com.ll.basic1.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService service;

    //-- 쿠키 삭제 하기 --//
    // http://localhost:8080/member/logout
    @GetMapping("/member/logout")
    @ResponseBody
    public MemberDto logout(HttpServletResponse resp) {
        service.deleteCookie(resp);
        return new MemberDto("S-1", "로그아웃 되었습니다.");
    }

    //-- 쿠키로 로그인 정보 확인하기 --//
    // http://localhost:8080/member/me
    @GetMapping("/member/me")
    @ResponseBody
    public MemberDto showMe(HttpServletRequest req) {
        String value = service.findCookie(req);

        if (value.equals("null"))
            return new MemberDto("F-1", "로그인후 이용해 주세요.");

        return new MemberDto("S-1", "당신의 username(은)는 " + value +" 입니다.");
    }


    //-- 저장 --//
    // http://localhost:8080/home/addPerson?name=홍길동&password=1234
    @GetMapping("/home/addPerson")
    @ResponseBody
    public String showMain2(
            @RequestParam String name,
            @RequestParam int password
    ) {
        Member member = new Member();
        member.setName(name);
        member.setPassword(password);

        return service.save(member) + "번 사람이 추가되었습니다.";
    }

    //-- 로그인 하기 --//
    // http://localhost:8080/member/login?username=홍길동&password=1234
    @GetMapping("/member/login")
    @ResponseBody
    public MemberDto showLogin(
            @RequestParam String username,
            @RequestParam int password,
            HttpServletRequest req,
            HttpServletResponse resp
    ) {
        MemberDto result = service.login(username, password);
        service.createCookie(resp, username);
        return result;
    }

    //-- 모든 회원 조회 --//
    // http://localhost:8080/home/people
    @GetMapping("/home/people")
    @ResponseBody
    public List<Member> showMain3() {

        return service.findAll();
    }

    //-- 특정 파라미터 수정 --//
    // http://localhost:8080/home/updatePerson?id=1&name=홍반장
    @GetMapping("/home/updatePerson")
    @ResponseBody
    public UpdateDto showMain6(
            @RequestParam int id,
            @RequestParam String name,
            HttpServletRequest req
    ) {
        String value = service.findCookie(req);

        if (value == "null")
            return new UpdateDto("로그인 후 이용해주세요.", "F-1");
        else {
            Member member = service.update(id, name);
            return new UpdateDto(member.getId() + "번 화원 수정이 완료됬습니다.", member);
        }
    }

    //-- 특정 파라미터 삭제 --//
    // http://localhost:8080/home/removePerson?id=2
    @GetMapping("/home/removePerson")
    @ResponseBody
    public String showMain5(
            @RequestParam int id,
            HttpServletRequest req
    ) {
        String value = service.findCookie(req);

        if (value == "null") return "로그인 후 이용해주세요.";

        return service.delete(id) + " 번 회원이 삭제되었습니다.";
    }
}
