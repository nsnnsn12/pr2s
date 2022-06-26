package com.metacrew.pr2s.repository;

import com.metacrew.pr2s.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long>{
}
