package com.metacrew.pr2s.repository;

import com.metacrew.pr2s.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long>{
}
