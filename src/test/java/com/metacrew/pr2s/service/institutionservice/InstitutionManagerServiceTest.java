package com.metacrew.pr2s.service.institutionservice;

import com.metacrew.pr2s.dto.AddressDto;
import com.metacrew.pr2s.dto.InstitutionCreateDto;
import com.metacrew.pr2s.dto.WorkdaysDto;
import com.metacrew.pr2s.entity.Address;
import com.metacrew.pr2s.entity.Institution;
import com.metacrew.pr2s.entity.Workdays;
import com.metacrew.pr2s.entity.embedded.TimePeriod;
import com.metacrew.pr2s.repository.institutionrepository.InstitutionRepository;
import com.metacrew.pr2s.repository.roomusagerepository.RoomUsageQueryRepositoryImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class InstitutionManagerServiceTest {
    @Autowired
    private InstitutionManagerService institutionManagerService;
    @Autowired
    private InstitutionRepository institutionRepository;
    @Autowired
    EntityManager em;
    private RoomUsageQueryRepositoryImpl roomUsageQueryRepository;

    @Test
    @DisplayName("기관 삽입 테스트")
    public void joinPr2sTest() {
        //given
        InstitutionCreateDto institutionCreateDto = getTestInstitutionDtoByInsertTestData();
        WorkdaysDto workdaysDto = getTestWorkdaysDtoByInsertTestData();
        AddressDto addressDto = getTestAddressDtoByInsertTestData();

        //when
        Institution insertedInstitution = institutionManagerService.joinPr2s(institutionCreateDto, workdaysDto);

        //then
        Institution findInstitution = institutionRepository.findById(insertedInstitution.getId()).orElseThrow(IllegalArgumentException::new);
        assertThat(findInstitution.getName()).isEqualTo(insertedInstitution.getName());
        assertThat(findInstitution.isApprovedRegistration()).isEqualTo(false);
    }

    @Test
    @DisplayName("기관 수정 테스트")
    public void updateInstitutionInfoTest() {
        // given
        Institution insertedInstitution = institutionManagerService.joinPr2s(getTestInstitutionDtoByInsertTestData(), getTestWorkdaysDtoByInsertTestData());

        // when
        InstitutionCreateDto testInstitutionDtoByUpdateTestData = getTestInstitutionDtoByUpdateTestData();
        WorkdaysDto testWorkdaysDtoByUpdateTestData = getTestWorkdaysDtoByUpdateTestData();
        AddressDto testAddressDtoByUpdateTestData = getTestAddressDtoByUpdateTestData();

        Institution updatedInstitution = institutionManagerService.updateInstitutionInfo(testInstitutionDtoByUpdateTestData
                ,testWorkdaysDtoByUpdateTestData, testAddressDtoByUpdateTestData, insertedInstitution.getId());

        // then
        Institution findInstitution = institutionRepository.findById(updatedInstitution.getId()).orElseThrow(IllegalArgumentException::new);

        assertThat(findInstitution.getId()).isEqualTo(insertedInstitution.getId());
        assertThat(findInstitution.getName()).isEqualTo(testInstitutionDtoByUpdateTestData.getName());
        assertThat(findInstitution.getTelNumber()).isEqualTo(testInstitutionDtoByUpdateTestData.getTelNumber());
        assertThat(findInstitution.getTimePeriod().getStartTime()).isEqualTo(testInstitutionDtoByUpdateTestData.getTimePeriod().getStartTime());
        assertThat(findInstitution.getTimePeriod().getEndTime()).isEqualTo(testInstitutionDtoByUpdateTestData.getTimePeriod().getEndTime());
        assertThat(findInstitution.getWorkdays().getIsMonday()).isEqualTo(testWorkdaysDtoByUpdateTestData.getIsMonday());
        assertThat(findInstitution.getWorkdays().getIsTuesday()).isEqualTo(testWorkdaysDtoByUpdateTestData.getIsTuesday());
        assertThat(findInstitution.getWorkdays().getIsWednesday()).isEqualTo(testWorkdaysDtoByUpdateTestData.getIsWednesday());
        assertThat(findInstitution.getWorkdays().getIsThursday()).isEqualTo(testWorkdaysDtoByUpdateTestData.getIsThursday());
        assertThat(findInstitution.getWorkdays().getIsFriday()).isEqualTo(testWorkdaysDtoByUpdateTestData.getIsFriday());

        // TODO: 2022-09-18 기관주소정보 테이블 추가에 따른 테이블 변경으로 인하여 추가 작업 필요

        //assertThat(findInstitution.getAddress().getRn()).isEqualTo(testAddressDtoByUpdateTestData.getRn());
        //assertThat(findInstitution.getAddress().getZipNo()).isEqualTo(testAddressDtoByUpdateTestData.getZipNo());
    }

    @Test
    @DisplayName("존재하지 않는 기관 수정 테스트")
    public void updateNotExistInstitution() {
        // given
        InstitutionCreateDto testInstitutionDtoByUpdateTestData = getTestInstitutionDtoByUpdateTestData();
        WorkdaysDto testWorkdaysDtoByUpdateTestData = getTestWorkdaysDtoByUpdateTestData();
        AddressDto testAddressDtoByUpdateTestData = getTestAddressDtoByUpdateTestData();

        // when
        assertThatThrownBy(() -> institutionManagerService
                .updateInstitutionInfo(
                          testInstitutionDtoByUpdateTestData
                        , testWorkdaysDtoByUpdateTestData
                        , testAddressDtoByUpdateTestData
                        , -1L
                ))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("존재하지 않는 기관입니다.");
    }

    @Test
    @DisplayName("삭제 처리된 기관 수정 테스트")
    public void updateDeletedInstitution() {
        // given
        Institution insertedInstitution = institutionManagerService.joinPr2s(getTestInstitutionDtoByInsertTestData(), getTestWorkdaysDtoByInsertTestData());
        insertedInstitution.deleted();

        // when
        InstitutionCreateDto testInstitutionDtoByUpdateTestData = getTestInstitutionDtoByUpdateTestData();
        WorkdaysDto testWorkdaysDtoByUpdateTestData = getTestWorkdaysDtoByUpdateTestData();
        AddressDto testAddressDtoByUpdateTestData = getTestAddressDtoByUpdateTestData();

        assertThatThrownBy(() -> institutionManagerService
                .updateInstitutionInfo(
                          testInstitutionDtoByUpdateTestData
                        , testWorkdaysDtoByUpdateTestData
                        , testAddressDtoByUpdateTestData
                        , insertedInstitution.getId())
        ).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("삭제 처리된 기관입니다.");
    }

    @Test
    @DisplayName("기관 삭제 테스트")
    public void deleteInstitutionTest() {
        // given
        InstitutionCreateDto institutionCreateDto = getTestInstitutionDtoByInsertTestData();
        Workdays workdays = Workdays.createWorkdays(getTestWorkdaysDtoByInsertTestData());
        Address address = Address.createAddressByAddressDto(getTestAddressDtoByInsertTestData());

        Institution insertedInstitution = institutionRepository.save(Institution.createInstitution(institutionCreateDto, workdays));

        // TODO: 2022-09-18 기관주소정보 테이블 추가에 따른 테이블 변경으로 인하여 추가 작업 필요

        //when
        institutionManagerService.deleteInstitution(insertedInstitution.getId());

        //then
        Institution deletedInstitution = institutionRepository.findById(insertedInstitution.getId()).orElseThrow(IllegalArgumentException::new);
        assertThat(deletedInstitution.isDeleted()).isEqualTo(true);

    }

    public InstitutionCreateDto getTestInstitutionDtoByInsertTestData() {
        InstitutionCreateDto institutionCreateDto = new InstitutionCreateDto();
        institutionCreateDto.setName("테스트1");
        institutionCreateDto.setTelNumber("010-1234-5678");
        institutionCreateDto.setTimePeriod(TimePeriod.createTimePeriod(LocalTime.of(10, 30), LocalTime.of(22, 0)));
        return institutionCreateDto;
    }

    public InstitutionCreateDto getTestInstitutionDtoByUpdateTestData() {
        InstitutionCreateDto institutionCreateDto = new InstitutionCreateDto();
        institutionCreateDto.setName("수정테스트1");
        institutionCreateDto.setTelNumber("010-2345-6789");
        institutionCreateDto.setTimePeriod(TimePeriod.createTimePeriod(LocalTime.of(9, 30), LocalTime.of(21, 0)));
        return institutionCreateDto;
    }

    public WorkdaysDto getTestWorkdaysDtoByInsertTestData() {
        WorkdaysDto workdaysDto = new WorkdaysDto();
        workdaysDto.setIsMonday(true);
        workdaysDto.setIsWednesday(true);
        workdaysDto.setIsFriday(true);
        return workdaysDto;
    }

    public WorkdaysDto getTestWorkdaysDtoByUpdateTestData() {
        WorkdaysDto workdaysDto = new WorkdaysDto();
        workdaysDto.setIsTuesday(true);
        workdaysDto.setIsThursday(true);
        return workdaysDto;
    }
    
    public AddressDto getTestAddressDtoByInsertTestData() {
        AddressDto addressDto = new AddressDto();
        addressDto.setZipNo("87749");
        addressDto.setRn("삽입비고");
        return addressDto;
    }

    public AddressDto getTestAddressDtoByUpdateTestData() {
        AddressDto addressDto = new AddressDto();
        addressDto.setZipNo("48940");
        addressDto.setRn("수정비고");
        return addressDto;
    }

}