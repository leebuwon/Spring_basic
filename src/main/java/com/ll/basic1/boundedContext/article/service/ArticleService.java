package com.ll.basic1.boundedContext.article.service;

import com.ll.basic1.boundedContext.article.entity.Article;
import com.ll.basic1.boundedContext.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    public Article write(String title, String body) {
        // builder 패턴
        Article article = Article.builder()
                .title(title)
                .body(body).build();

        // 위 코드는 아래 코드와 비슷한 의미이다.
//        Article article1 = new Article(title, body);
//        article1.setTitle(title);
//        article1.setBody(body);

        return articleRepository.save(article);
    }
}
