package com.ll.basic1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Article {

    @Id
    private long id;
    private String title;
    private String body;

}
