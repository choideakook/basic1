package com.ll.basic1.repository;

import com.ll.basic1.entity.Member;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MemberRepository {

    //-- 회원 정보 저장 공간 --//
    private final Map<Integer, Member> list = new HashMap<>();
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
    public Member update(Member member, String name) {
        member.setName(name);
        int id = member.getId();
        list.put(id, member);
        return member;
    }

    //-- 획원 삭제 --//
    public int delete(int id) {
        list.remove(id);
        return id;
    }
}
