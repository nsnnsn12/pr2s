package com.metacrew.pr2s.service.institutionservice;

import com.metacrew.pr2s.dto.InstitutionDto;
import com.metacrew.pr2s.dto.WorkdaysDto;
import com.metacrew.pr2s.entity.Institution;
import com.metacrew.pr2s.entity.Workdays;
import com.metacrew.pr2s.repository.AddressRepository;
import com.metacrew.pr2s.repository.institutionrepository.InstitutionTestRepository;
import com.metacrew.pr2s.repository.workdaysrepository.WorkdaysTestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.Optional;

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
    private final WorkdaysTestRepository workdaysTestRepository;

    /**
     * 기관 정보를 입력받아 엔터티를 DB에 저장하고 key 값을 반환
     * @author hyeonwoo
     * @since 2022.07.01
     * @param institutionDto 등록할 기관 정보.
     * @param workdaysDto 등록할 기관의 운영 요일 정보
     * @return 등록한 회원 key 값.
     */
    public Institution joinPr2s(InstitutionDto institutionDto, WorkdaysDto workdaysDto){
        Workdays workdays = workdaysTestRepository.save(Workdays.createWorkdays(workdaysDto));

        Institution institution = Institution.createInstitution(institutionDto, workdays);
        institutionTestRepository.save(institution);

        return institution;
    }

    /**
     * 수정하려는 기관에 대한 수정 정보를 입력받아 DB에 반영한 후 key 값 리턴
     * @author hpyeonwoo
     * @since 2022.07.07
     * @param institutionDto 변경할 기관 정보
     * @return 수정한 회원 key 값
     */
    public Long updateInstitutionInfo(InstitutionDto institutionDto, Long id){
        Institution findInstitution = institutionTestRepository.getInstitution(id).orElseThrow(IllegalArgumentException::new);
        findInstitution.setForUpdate(institutionDto);

        return findInstitution.getId();
    }

    /**
     * 삭제하려는 기관의 ID를 받아 삭제여부와 삭제일자를 업데이트
     * @author hyeonwoo
     * @since 2022.07.01
     * @param id 변경할 기관 id
     */
    public void deleteInstitution(Long id) {
        Institution findInstitution = institutionTestRepository.getInstitution(id).orElseThrow(IllegalArgumentException::new);
        if(!findInstitution.isDeleted()) findInstitution.deleted();
    }
}
