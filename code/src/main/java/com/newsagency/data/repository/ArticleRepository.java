package com.newsagency.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.newsagency.data.entity.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {

	public Article save(Article article);

	public Article findByArticleTitle(String articleTitle);

}
