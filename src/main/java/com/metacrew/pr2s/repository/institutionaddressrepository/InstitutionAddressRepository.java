package com.metacrew.pr2s.repository.institutionaddressrepository;

import com.metacrew.pr2s.entity.Institution;
import com.metacrew.pr2s.entity.InstitutionAddress;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * InstitutionAddress 테이블과 매핑되는 레퍼지토리이다.
 * @author hyeonwoo
 * @since 2022.09.20
 */
public interface InstitutionAddressRepository extends JpaRepository<InstitutionAddress, Long>{
}
