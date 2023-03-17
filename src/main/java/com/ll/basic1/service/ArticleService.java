package com.ll.basic1.service;

import com.ll.basic1.entity.Article;
import com.ll.basic1.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository repository;

    public Article write(Article article) {

        return repository.save(article);
    }


}
