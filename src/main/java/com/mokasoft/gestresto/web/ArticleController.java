package com.mokasoft.gestresto.web;

import com.mokasoft.gestresto.dtos.ArticleRequest;
import com.mokasoft.gestresto.responses.ResponseHandler;
import com.mokasoft.gestresto.services.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping
    public ResponseEntity<Object> saveArticle(@Valid @RequestBody ArticleRequest articleRequest){
        return ResponseHandler.responseBuilder("article created", HttpStatus.CREATED,
                articleService.saveArticle(articleRequest));
    }
}
