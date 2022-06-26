package com.metacrew.pr2s.repository;

import com.metacrew.pr2s.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Address 테이블과 매핑되는 레퍼지토리이다.
 * 주소를 수정 및 조회할 수 있다.
 * @author hyeonwoo
 * @since 2022.06.26
 */
public interface AddressRepository extends JpaRepository<Address, Long>{
}
