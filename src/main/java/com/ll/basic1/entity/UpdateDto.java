package com.ll.basic1.entity;

import lombok.Data;

@Data
public class UpdateDto {

    private String mes;
    private Member member;
    private String resultCode;

    public UpdateDto(String mes, Member member) {
        this.mes = mes;
        this.member = member;
    }

    public UpdateDto(String mes, String resultCode) {
        this.mes = mes;
        this.resultCode = resultCode;
    }
}
