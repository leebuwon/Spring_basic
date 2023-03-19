package com.ll.basic1.boundedContext.article.controller;

import com.ll.basic1.base.rsData.RsData;
import com.ll.basic1.boundedContext.article.entity.Article;
import com.ll.basic1.boundedContext.article.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/article")
@RequiredArgsConstructor // 필드 중에서 final 붙은것만 인자로 생성자를 생성
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/write")
    @ResponseBody
    public RsData write(@RequestParam String title,
                        @RequestParam String body){

        if (title == null || title.trim().length() == 0){
            return RsData.of("F-1", "title(을)를 입력해주세요");
        }

        if (body == null || body.trim().length() == 0){
            return RsData.of("F-2", "body(을)를 입력해주세요");
        }

        Article createArticle = articleService.write(title, body);

        return RsData.of("S-1", "1번 글이 생성되었습니다.", createArticle);

    }
}
