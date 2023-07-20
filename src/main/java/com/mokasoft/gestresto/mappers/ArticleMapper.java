package com.mokasoft.gestresto.mappers;

import com.mokasoft.gestresto.dtos.ArticleRequest;
import com.mokasoft.gestresto.dtos.ArticleResponse;
import com.mokasoft.gestresto.entities.Article;
import org.springframework.stereotype.Component;

@Component
public class ArticleMapper {
    public Article articleRequestToArticle(ArticleRequest articleRequest){
        Article article = Article.builder()
                .designation(articleRequest.getDesignation())
                .price(articleRequest.getPrice())
                .quantity(articleRequest.getQuantity())
                .alertStock(articleRequest.getAlertStock())
                .unite(articleRequest.getUnite())
                .build();
        return article;
    }

    public ArticleResponse articleToArticleResponse(Article article){
        ArticleResponse articleResponse = ArticleResponse.builder()
                .articleId(article.getArticleId())
                .designation(article.getDesignation())
                .quantity(article.getQuantity())
                .alertStock(article.getAlertStock())
                .price(article.getPrice())
                .unite(article.getUnite())
                .build();
        return articleResponse;
    }
}
