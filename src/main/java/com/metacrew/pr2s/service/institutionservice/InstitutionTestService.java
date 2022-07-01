package com.metacrew.pr2s.service.institutionservice;

import com.metacrew.pr2s.dto.InstitutionDto;
import com.metacrew.pr2s.entity.Institution;
import com.metacrew.pr2s.entity.Member;
import com.metacrew.pr2s.repository.institutionrepository.InstitutionTestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * JPQL 학습을 위한 테스트 서비스 클래스이다.
 * @author hyeonwoo
 * @since 2022.07.01
 */
@Service
@Transactional
@RequiredArgsConstructor
public class InstitutionTestService {
    private final InstitutionTestRepository institutionTestRepository;

    /**
     * 입력한 기관정보를 DB에 저장하고 key 값을 리턴한다.
     * @author hyeonwoo
     * @since 2022.07.01
     */
    public Long joinPr2s(InstitutionDto institutionDto){
        //institutionTestRepository.save(institutionDto);
        return institutionDto.getId();
    }

    /**
     * 수정하려는 기관에 대한 수정 정보를 입력받아 DB에 반영한 후 key 값 리턴
     * @author hyeonwoo
     * @since 2022.07.01
     */
    public Long updateInstitutionInfo(InstitutionDto institutionDto){
        Institution findInstitution = institutionTestRepository.getInstitution(institutionDto.getId());
        //findInstitution.update(institutionDto);
        return institutionDto.getId();
    }

    /**
     * 삭제하려는 기관의 ID를 받아 삭제 여부와 삭제 일자를 업데이트
     * @author hyeonwoo
     * @since 2022.07.01
     */
    public void deleteInstitution(InstitutionDto institutionDto) {
        // TODO: 2022-07-01 BaseEntity 변경 후 구현
    }
}
