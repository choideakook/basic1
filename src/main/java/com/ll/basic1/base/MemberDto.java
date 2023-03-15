package com.ll.basic1.base;

import lombok.Data;

@Data
public class MemberDto {
    private String resultCode;
    private String msg;

    public MemberDto(String resultCode, String msg) {
        this.resultCode = resultCode;
        this.msg = msg;
    }
}
