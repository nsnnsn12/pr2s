package com.metacrew.pr2s;

import com.metacrew.pr2s.dto.InstitutionDto;
import com.metacrew.pr2s.dto.WorkdaysDto;
import com.metacrew.pr2s.entity.Workdays;
import com.metacrew.pr2s.repository.institutionrepository.InstitutionTestRepository;
import com.metacrew.pr2s.repository.workdaysrepository.WorkdaysTestRepository;
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
        InstitutionTestRepository institutionTestRepository = new InstitutionTestRepository(em);
        WorkdaysTestRepository workdaysTestRepository = new WorkdaysTestRepository(em);
        InstitutionTestService institutionService = new InstitutionTestService(institutionTestRepository, workdaysTestRepository);

        InstitutionDto testDto = new InstitutionDto();
        testDto.setName("테스트기관1");
        testDto.setTelNumber("010-1234-5678");

        WorkdaysDto workdaysDto = new WorkdaysDto();
        workdaysDto.setIsMonday(true);
        workdaysDto.setIsWednesday(true);
        workdaysDto.setIsFriday(true);

        Long insertedId = institutionService.joinPr2s(testDto, workdaysDto);

        testDto.setName("변경된테스트기관명");
        testDto.setTelNumber("010-2345-6789");
        institutionService.updateInstitutionInfo(testDto, insertedId);

        institutionService.deleteInstitution(insertedId);
    }
}
