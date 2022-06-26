package com.metacrew.pr2s.repository;

import com.metacrew.pr2s.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Article 테이블과 매핑되는 레퍼지토리이다.
 * 게시글을 수정 및 조회할 수 있다.
 * @author hyeonwoo
 * @since 2022.06.26
 */
public interface ArticleRepository extends JpaRepository<Article, Long>{
}
