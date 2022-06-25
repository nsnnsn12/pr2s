package com.metacrew.pr2s.repository;

import com.metacrew.pr2s.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long>{
}
