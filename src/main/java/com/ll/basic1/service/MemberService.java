package com.ll.basic1.service;

import com.ll.basic1.entity.Member;
import com.ll.basic1.entity.MemberDto;
import com.ll.basic1.repository.MemberRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository repository;

    //-- save --//
    public int save(Member member) {
        return repository.save(member);
    }

    //-- 쿠키 생성 --//
    public void createCookie(HttpServletResponse resp, String username) {

        resp.addCookie(new Cookie("yes", username));
    }

    //-- 쿠키 확인 --//
    public String findCookie(HttpServletRequest req) {
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
        return "null";
    }

    //-- 쿠키 삭제 --//
    public void deleteCookie(HttpServletResponse resp) {
        Cookie cookie = new Cookie("yes", null);
        cookie.setMaxAge(0);
        resp.addCookie(cookie);
    }



    //-- login ---//
    public MemberDto login(String username, int password) {
        List<Member> members = repository.findAll();
        for (Member dto : members) {
            if (dto.getName().equals(username)) {
                if (dto.getPassword() == password) {
                    return new MemberDto("S-1", username + "님 환영합니다.");

                } else return new MemberDto("F-1", "비밀번호가 일치하지 않습니다.");
            }
        }
        return new MemberDto("F-2", username + " (은)는 존재하지 않는 회원입니다.");
    }

    //-- id 찾기 --//
    public Member findOne(int id) {
        return repository.findMember(id);
    }

    //-- 모든 회원 찾기 --//
    public List<Member> findAll() {
        return repository.findAll();
    }

    //-- 회원 업데이트 --//
    public Member update(int id, String name) {
        Member member = repository.findMember(id);
        return repository.update(member, name);
    }

    //-- 회원 삭제 --//
    public int delete(int id) {
        return repository.delete(id);
    }
}
