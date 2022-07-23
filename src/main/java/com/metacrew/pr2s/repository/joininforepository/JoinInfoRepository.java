package com.metacrew.pr2s.repository.joininforepository;

import com.metacrew.pr2s.entity.Institution;
import com.metacrew.pr2s.entity.JoinInfo;
import com.metacrew.pr2s.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * JoinInfo 테이블과 매핑되는 레퍼지토리이다.
 * @author hyeonwoo
 * @since 2022.06.26
 */
public interface JoinInfoRepository extends JpaRepository<JoinInfo, Long>, JoinInfoQueryRepository {
    List<JoinInfo> findByMemberAndInstitutionAndIsDeleted(Member member, Institution institution, boolean isDeleted);
}
