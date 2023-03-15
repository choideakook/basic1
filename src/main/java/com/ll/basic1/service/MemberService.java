package com.ll.basic1.service;

import com.ll.basic1.base.Member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberService {

    private Map<Integer, Member> list = new HashMap<>();
    private int id = 0;

    //-- 저장 --//
    public int save(Member member) {
        id = id + 1;
        member.setId(id);
        list.put(id, member);
        return id;
    }

    //-- 특정 회원 검색 --//
    public Member findMember(int id) {
        return list.get(id);
    }

    //-- 모든 회원 찾기 --//
    public List<Member> findAll() {
        List<Member> findAll = new ArrayList<>();
        for (Member member : list.values()) {
            findAll.add(member);
        }
        return findAll;
    }

    //-- 회원 정보 업데이트 --//
    public Member update(int id, String name) {
        Member member = list.get(id);
        member.setName(name);
        list.put(id, member);
        return member;
    }

    //-- 획원 삭제 --//
    public int delete(int id) {
        list.remove(id);
        return id;
    }


}
