package com.metacrew.pr2s.service.institutionservice;

import com.metacrew.pr2s.dto.AddressDto;
import com.metacrew.pr2s.dto.InstitutionCreateDto;
import com.metacrew.pr2s.dto.WorkdaysDto;
import com.metacrew.pr2s.entity.Institution;

/**
 * 기관 정보를 관리하기 위한 메소드를 정의한 인터페이스이다.
 * @author hyeonwoo
 * @since 2022.07.14
 */
public interface InstitutionService {

    /**
     * 기관 정보 DTO 운영요일 DTO 주소 DTO를 입력받아 기관 가입처리 후 기관 정보를 리턴
     * @author hyeonwoo
     * @since 2022.07.14
     * @param institutionCreateDto 등록할 기관 정보.
     * @param workdaysDto 등록할 기관의 운영 요일 정보
     * @param addressDto 등록할 기관의 주소 정보
     * @return 등록한 기관 정보
     */
    Institution joinPr2s(InstitutionCreateDto institutionCreateDto, WorkdaysDto workdaysDto, AddressDto addressDto);

    /**
     * 수정하려는 기관에 대한 수정 정보를 입력받아 수정한 후 기관 정보 리턴
     * @author hpyeonwoo
     * @since 2022.07.14
     * @param institutionCreateDto 변경할 기관 정보
     * @return 변경한 기관 정보
     */
    Institution updateInstitutionInfo(InstitutionCreateDto institutionCreateDto, WorkdaysDto workdaysDto, AddressDto addressDto, Long id);

    /**
     * 삭제하려는 기관의 ID를 받아 삭제여부와 삭제일자를 업데이트
     * @author hyeonwoo
     * @since 2022.07.14
     * @param id 삭제할 기관 id
     */
    void deleteInstitution(Long id);
}
