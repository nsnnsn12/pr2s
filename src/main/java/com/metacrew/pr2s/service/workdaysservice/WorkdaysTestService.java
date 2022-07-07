package com.metacrew.pr2s.service.workdaysservice;

import com.metacrew.pr2s.dto.InstitutionDto;
import com.metacrew.pr2s.entity.Institution;
import com.metacrew.pr2s.repository.institutionrepository.InstitutionTestRepository;
import com.metacrew.pr2s.repository.workdaysrepository.WorkdaysTestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * JPQL 학습을 위한 테스트 서비스 클래스이다.
 * @author hyeonwoo
 * @since 2022.07.07
 */
@Service
@Transactional
@RequiredArgsConstructor
public class WorkdaysTestService implements WorkdaysService {
    private final WorkdaysTestRepository workdaysTestRepository;


}
