package com.metacrew.pr2s;

import com.metacrew.pr2s.dto.InstitutionDto;
import com.metacrew.pr2s.entity.Institution;
import com.metacrew.pr2s.entity.enums.WorkDay;
import com.metacrew.pr2s.repository.institutionrepository.InstitutionRepository;
import com.metacrew.pr2s.repository.institutionrepository.InstitutionTestRepository;
import com.metacrew.pr2s.service.institutionservice.InstitutionService;
import com.metacrew.pr2s.service.institutionservice.InstitutionTestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
@Rollback(false)
public class HyeonwooTest {

    @Autowired
    EntityManager em;

    @Test
    void contextLoads() {
        InstitutionTestRepository testRepository = new InstitutionTestRepository(em);
        InstitutionTestService institutionService = new InstitutionTestService(testRepository);

        InstitutionDto testDto = new InstitutionDto();
        testDto.setName("테스트기관1");
        testDto.setTelNumber("010-1234-5678");
        testDto.setWorkday(WorkDay.FRI);
        Long insertedId = institutionService.joinPr2s(testDto);

        testDto.setId(insertedId);
        testDto.setName("변경된테스트기관명");
        testDto.setTelNumber("010-2345-6789");
        institutionService.updateInstitutionInfo(testDto);

        institutionService.deleteInstitution(insertedId);
    }
}
