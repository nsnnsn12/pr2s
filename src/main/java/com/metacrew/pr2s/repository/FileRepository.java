package com.metacrew.pr2s.repository;

import com.metacrew.pr2s.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long>{
}
