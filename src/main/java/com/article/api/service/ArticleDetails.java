package com.article.api.service;


import com.article.api.beans.Article;
import com.article.api.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleDetails {
    @Autowired
    ArticleRepository articleRepository;

    public List<Article> getArticle() {
        List<Article> articleList = new ArrayList<Article>();
        Iterable<Article> itr =articleRepository.findAll();
        itr.forEach( article ->{
            articleList.add(article);
                });
        return articleList;
    }

    public Optional<Article> findById(Integer ArticleId) {
        Optional<Article> article = articleRepository.findById(ArticleId);
        return article;
    }

    public Optional<Article> saveArticle(Article article){
        Article saved = articleRepository.save(article);
        return this.findById(saved.getArticleId());
    }
}
