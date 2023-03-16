package com.ll.basic1.controller;

import com.ll.basic1.cookie.Rq;
import com.ll.basic1.entity.Member;
import com.ll.basic1.entity.MemberDto;
import com.ll.basic1.entity.UpdateDto;
import com.ll.basic1.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    //-- business logic DI --//
    private final MemberService service;
    //-- cookie logic DI --//
    private final Rq rq;

    //-- 쿠키 삭제 (로그아웃) 하기 --//
    // http://localhost:8080/member/logout
    @GetMapping("/member/logout")
    @ResponseBody
    public MemberDto logout() {
        String cookie = rq.findCookie();

        if (cookie.equals(""))
            return new MemberDto("S-2", "이미 로그아웃 상태입니다.");

        else {
            rq.deleteCookie();
            return new MemberDto("S-1", "로그아웃 되었습니다.");
        }
    }

    //-- 쿠키로 사용자 정보 확인하기 --//
    // http://localhost:8080/member/me
    @GetMapping("/member/me")
    @ResponseBody
    public MemberDto showMe() {
        String value = rq.findCookie();

        if (value.equals(""))
            return new MemberDto("F-1", "로그인후 이용해 주세요.");

        return new MemberDto("S-1", "당신의 username(은)는 " + value +" 입니다.");
    }


    //-- 신규 회원 등록 --//
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

    //-- 로그인 , 쿠키 저장 하기 --//
    // http://localhost:8080/member/login?username=홍길동&password=1234
    @GetMapping("/member/login")
    @ResponseBody
    public MemberDto showLogin(
            @RequestParam String username,
            @RequestParam int password
    ) {
        MemberDto result = service.login(username, password);
        rq.createCookie(username);
        return result;
    }

    //-- 모든 회원 조회 --//
    // http://localhost:8080/home/people
    @GetMapping("/home/people")
    @ResponseBody
    public List<Member> showMain3() {

        return service.findAll();
    }

    //-- 회원 정보 수정 --//
    // http://localhost:8080/home/updatePerson?id=1&name=홍반장
    @GetMapping("/home/updatePerson")
    @ResponseBody
    public UpdateDto showMain6(
            @RequestParam int id,
            @RequestParam String name
    ) {
        String value = rq.findCookie();

        if (value == "null")
            return new UpdateDto("로그인 후 이용해주세요.", "F-1");

            Member member = service.update(id, name);
            return new UpdateDto(member.getId() + "번 화원 수정이 완료됬습니다.", member);
    }

    //-- 회원 삭제 --//
    // http://localhost:8080/home/removePerson?id=2
    @GetMapping("/home/removePerson")
    @ResponseBody
    public String showMain5(@RequestParam int id) {
        String value = rq.findCookie();

        if (value == "null")
            return "로그인 후 이용해주세요.";

        return service.delete(id) + " 번 회원이 삭제되었습니다.";
    }
}
