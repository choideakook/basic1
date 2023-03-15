package com.ll.basic1.entity;

import lombok.Data;

@Data
public class UpdateDto {

    private String mes;
    private Member member;

    public UpdateDto(String mes, Member member) {
        this.mes = mes;
        this.member = member;
    }
}