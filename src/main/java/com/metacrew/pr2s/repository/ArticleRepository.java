package com.metacrew.pr2s.repository;

import com.metacrew.pr2s.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long>{
}
