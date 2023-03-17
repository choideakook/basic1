package com.ll.basic1.controller;

import com.ll.basic1.cookie.Rq;
import com.ll.basic1.entity.Member;
import com.ll.basic1.entity.MemberDto;
import com.ll.basic1.entity.UpdateDto;
import com.ll.basic1.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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


    //-- session check 용 메소드 --//
    //http://localhost:8080/member/session
    @GetMapping("/member/session")
    @ResponseBody
    public String showSession() {
        return rq.getSessionDebugContents();
    }


    //-- 로그 아웃 하기 --//
    // http://localhost:8080/member/logout
    @GetMapping("/member/logout")
    @ResponseBody
    public MemberDto logout() {
        boolean result = rq.removeSession("loginedMemberId");

        if (!result)
            return new MemberDto("S-2", "이미 로그아웃 상태입니다.");

        else {
            return new MemberDto("S-1", "로그아웃 되었습니다.");
        }
    }

    //-- 사용자 정보 확인하기 --//
    // http://localhost:8080/member/me
    @GetMapping("/member/me")
    @ResponseBody
    public MemberDto showMe() {
        long loginedMemberId = rq.getSessionAsLong("loginedMemberId", 0);
        boolean isLogined = loginedMemberId > 0;

        if (!isLogined)
            return new MemberDto("F-1", "로그인후 이용해 주세요.");

        Member member = service.findOne(loginedMemberId);

        return new MemberDto("S-1", "당신의 username(은)는 " + member.getName() +" 입니다.");
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

    //-- 로그인 HTML Form --//
    // http://localhost:8080/member/login
    @GetMapping("/member/login")
    public String showLogin() {
        return "usr/member/login_form";
    }

    //-- 파라미터로 로그인 하기 --//
    // http://localhost:8080/member/login?username=홍길동&password=1234
    @PostMapping("/member/login")
    public String doLogin(String username, int password, Model model) {
        MemberDto dto = service.login(username, password);
        Member member = dto.getMember();
        rq.setSession("loginedMemberId", member.getId());

        model.addAttribute("member", member);
        return "usr/member/login";
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
