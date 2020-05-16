package com.article.api.repository;

import com.javatechie.jwt.api.beans.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article,Integer> {

}
