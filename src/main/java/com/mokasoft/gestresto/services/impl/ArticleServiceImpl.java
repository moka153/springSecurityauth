package com.mokasoft.gestresto.services.impl;

import com.mokasoft.gestresto.dtos.ArticleRequest;
import com.mokasoft.gestresto.dtos.ArticleResponse;
import com.mokasoft.gestresto.entities.Article;
import com.mokasoft.gestresto.exceptions.ConflictException;
import com.mokasoft.gestresto.exceptions.NotFoundException;
import com.mokasoft.gestresto.mappers.ArticleMapper;
import com.mokasoft.gestresto.repositories.ArticleRepository;
import com.mokasoft.gestresto.services.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;

    @Override
    public ArticleResponse saveArticle(ArticleRequest articleRequest) {
        try{
            Article article = articleMapper.articleRequestToArticle(articleRequest);
            Article savedArticle = articleRepository.save(article);
            return articleMapper.articleToArticleResponse(savedArticle);
        }catch (DataIntegrityViolationException ex){
            throw new ConflictException("article already exists");
        }
    }

    @Override
    public ArticleResponse updateArticle(ArticleRequest articleRequest,Long articleId) {
        if(!articleRepository.existsById(articleId)){
            throw new NotFoundException("article not found");
        }
        try{
            Article article = articleMapper.articleRequestToArticle(articleRequest);
            article.setArticleId(articleId);
            Article savedArticle = articleRepository.save(article);
            return articleMapper.articleToArticleResponse(savedArticle);
        }catch (DataIntegrityViolationException ex){
            throw new ConflictException("article already exists");
        }
    }

    @Override
    public void deleteArticle(Long articleId) {
        if(!articleRepository.existsById(articleId)){
            throw new NotFoundException("article not found");
        }
        try{
            articleRepository.deleteById(articleId);
        }catch (DataIntegrityViolationException ex){
            throw new ConflictException("article already used");
        }
    }

    @Override
    public List<ArticleResponse> getAllArticles() {
        List<Article> articles = articleRepository.findAll();
        List<ArticleResponse> articleResponseList = articles.stream()
                .map(article -> articleMapper.articleToArticleResponse(article))
                .collect(Collectors.toList());
        return articleResponseList;
    }

    @Override
    public ArticleResponse getArticleById(Long articleId) {
        if(!articleRepository.existsById(articleId)){
            throw new NotFoundException("article not found");
        }
        Article article = articleRepository.findById(articleId).get();
        return articleMapper.articleToArticleResponse(article);
    }
}
