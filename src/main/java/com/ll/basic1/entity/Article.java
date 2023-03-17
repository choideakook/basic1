package com.ll.basic1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)  // 자동으로 생성, 수정 날짜값이 Entity 에 채워지는 어노테이션
public class Article {

    @Id @GeneratedValue (strategy = IDENTITY)
    private Long id;

    @CreatedDate // 생성 시 자동으로 날짜가 들어감
    private LocalDateTime createDate; // data 생성 날짜

    @LastModifiedDate  // 수정시 자동으로 날짜가 들어감
    private LocalDateTime modifyDate; // data 수정 날짜

    private String title;
    private String body;



}
