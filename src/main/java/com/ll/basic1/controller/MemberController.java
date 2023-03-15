package com.ll.basic1.controller;

import com.ll.basic1.base.Member;
import com.ll.basic1.base.MemberDto;
import com.ll.basic1.base.UpdateDto;
import com.ll.basic1.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
@Controller
public class MemberController {

    MemberService service = new MemberService();

    //-- 파라미터로 이름, 나이 받아오기 --//
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

    //-- 받아온 파라미터 확인하기 --//
    // http://localhost:8080/home/people
    @GetMapping("/home/people")
    @ResponseBody
    public List<Member> showMain3() {
        return service.findAll();
    }

    //-- 로그인 정보 확인하기 --//
    // http://localhost:8080/member/login?username=홍길동&password=1234
    @GetMapping("/member/login")
    @ResponseBody
    public MemberDto showLogin(
            @RequestParam String username,
            @RequestParam int password
    ) {
        List<Member> members = service.findAll();
        for (Member dto : members) {
            if (dto.getName().equals(username)) {
                if (dto.getPassword() == password) {
                    return new MemberDto("S-1", username + "님 환영합니다.");

                } else return new MemberDto("F-1", "비밀번호가 일치하지 않습니다.");
            }
        }
        return new MemberDto("F-2", username + " (은)는 존재하지 않는 회원입니다.");
    }

    //-- 특정 파라미터 수정 --//
    // http://localhost:8080/home/updatePerson?id=1&name=홍반장
    @GetMapping("/home/updatePerson")
    @ResponseBody
    public UpdateDto showMain6(
            @RequestParam int id,
            @RequestParam String name
    ) {
        Member member = service.update(id, name);
        return new UpdateDto(member.getId() + "번 화원 수정이 완료됬습니다.", member);
    }

    //-- 특정 파라미터 삭제 --//
    // http://localhost:8080/home/removePerson?id=2
    @GetMapping("/home/removePerson")
    @ResponseBody
    public String showMain5(
            @RequestParam int id
    ) {
        return service.delete(id) + " 번 회원이 삭제되었습니다.";
    }
}
