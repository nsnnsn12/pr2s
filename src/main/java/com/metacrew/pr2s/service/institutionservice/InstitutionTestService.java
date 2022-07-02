package com.metacrew.pr2s.service.institutionservice;

import com.metacrew.pr2s.dto.InstitutionDto;
import com.metacrew.pr2s.entity.Institution;
import com.metacrew.pr2s.entity.Member;
import com.metacrew.pr2s.repository.institutionrepository.InstitutionTestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * JPQL 학습을 위한 테스트 서비스 클래스이다.
 * @author hyeonwoo
 * @since 2022.07.01
 */
@Service
@Transactional
@RequiredArgsConstructor
public class InstitutionTestService implements InstitutionService {
    private final InstitutionTestRepository institutionTestRepository;

    /**
     * 기관 정보를 입력받아 중복을 확인하고
     * 중복일 경우 예외를 던지고
     * 중복된 경우는 예외를 던지고
     * 중복이 아닌 경우는 엔티티를 DB에 저장하고 key값을 리턴한다.
     * @author hyeonwoo
     * @since 2022.07.01
     * @param institutionDto 등록할 기관 정보.
     * @return 등록한 회원 key 값.
     */
    public Long joinPr2s(InstitutionDto institutionDto){
        Institution institution = Institution.setForInsertInstitution(institutionDto);
        validateDuplicateInstitution();
        institutionTestRepository.save(institution);
        return institution.getId();
    }

    /**
     * 임시
     * @author hyeonwoo
     * @since 2022.07.02
     */
    private void validateDuplicateInstitution(){

    }

    /**
     * 수정하려는 기관에 대한 수정 정보를 입력받아 DB에 반영한 후 key 값 리턴
     * @author hyeonwoo
     * @since 2022.07.01
     */
    public Long updateInstitutionInfo(InstitutionDto institutionDto){
        Institution findInstitution = institutionTestRepository.getInstitution(institutionDto.getId());
        findInstitution.setForUpdate(institutionDto);
        return institutionDto.getId();
    }

    /**
     * 삭제하려는 기관의 ID를 받아 삭제 여부와 삭제 일자를 업데이트
     * @author hyeonwoo
     * @since 2022.07.01
     */
    public void deleteInstitution(Long id) {
        Institution findInstitution = institutionTestRepository.getInstitution(id);
        if(!findInstitution.isDeleted()) findInstitution.deleted();
    }
}
