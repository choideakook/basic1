package com.ll.basic1.entity;

import lombok.Data;

@Data
public class MemberDto {
    private String resultCode;
    private String msg;

    private Member member;

    public MemberDto(String resultCode, String msg) {
        this.resultCode = resultCode;
        this.msg = msg;
    }

}
