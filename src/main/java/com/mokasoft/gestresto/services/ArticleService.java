package com.mokasoft.gestresto.services;

import com.mokasoft.gestresto.dtos.ArticleRequest;
import com.mokasoft.gestresto.dtos.ArticleResponse;

import java.util.List;

public interface ArticleService {
    ArticleResponse saveArticle(ArticleRequest articleRequest);
    ArticleResponse updateArticle(ArticleRequest articleRequest,Long articleId);
    void deleteArticle(Long articleId);
    List<ArticleResponse> getAllArticles();
    ArticleResponse getArticleById(Long articleId);
}
