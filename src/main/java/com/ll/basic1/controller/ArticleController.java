package com.ll.basic1.controller;

import com.ll.basic1.entity.Article;
import com.ll.basic1.entity.ArticleDto;
import com.ll.basic1.repository.ArticleRepository;
import com.ll.basic1.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService service;

    @GetMapping("/write")
    @ResponseBody
    public ArticleDto write(String title, String body) {

        // setter 대신 builder 를 통해 stream 형식으로 값을 채워줌
        Article article = Article
                .builder()
                .title(title)
                .body(body)
                .build();

        service.write(article);

        return new ArticleDto("S-1", "생성 성공");
    }
}
