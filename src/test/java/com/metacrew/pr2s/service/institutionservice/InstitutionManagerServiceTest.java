package com.metacrew.pr2s.service.institutionservice;

import com.metacrew.pr2s.dto.AddressDto;
import com.metacrew.pr2s.dto.InstitutionCreateDto;
import com.metacrew.pr2s.dto.WorkdaysDto;
import com.metacrew.pr2s.entity.Address;
import com.metacrew.pr2s.entity.Institution;
import com.metacrew.pr2s.entity.Workdays;
import com.metacrew.pr2s.repository.institutionrepository.InstitutionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class InstitutionManagerServiceTest {
    @Autowired
    private InstitutionManagerService institutionManagerService;
    @Autowired
    private InstitutionRepository institutionRepository;

    @Test
    @DisplayName("기관 삽입 테스트")
    public void joinPr2sTest() {
        //given
        InstitutionCreateDto institutionCreateDto = getTestInstitutionDtoByInsertTestData();
        WorkdaysDto workdaysDto = getTestWorkdaysDtoByInsertTestData();
        AddressDto addressDto = getTestAddressDtoByInsertTestData();

        //when
        Institution insertedInstitution = institutionManagerService.joinPr2s(institutionCreateDto, workdaysDto, addressDto);

        //then
        Institution findInstitution = institutionRepository.findById(insertedInstitution.getId()).orElseThrow(IllegalArgumentException::new);
        assertThat(findInstitution.getName()).isEqualTo(insertedInstitution.getName());
        assertThat(findInstitution.isApprovedRegistration()).isEqualTo(false);
    }

    @Test
    @DisplayName("기관 수정 테스트")
    public void updateInstitutionInfoTest() {
        // given
        Institution insertedInstitution = institutionManagerService.joinPr2s(getTestInstitutionDtoByInsertTestData(), getTestWorkdaysDtoByInsertTestData(), getTestAddressDtoByInsertTestData());

        // when
        Institution updatedInstitution = institutionManagerService.updateInstitutionInfo(getTestInstitutionDtoByUpdateTestData()
                ,getTestWorkdaysDtoByUpdateTestData(), getTestAddressDtoByUpdateTestData(), insertedInstitution.getId());

        // then
        Institution findInstitution = institutionRepository.findById(updatedInstitution.getId()).orElseThrow(IllegalArgumentException::new);

        assertThat(insertedInstitution.getId()).isEqualTo(updatedInstitution.getId());
        assertThat(updatedInstitution.getName()).isEqualTo("수정테스트1");
        assertThat(updatedInstitution.getTelNumber()).isEqualTo("010-2345-6789");
        /// TODO: 2022-07-16 Period 타입 분리 후 비교 테스트 필요
        assertThat(findInstitution.getWorkdays().getIsTuesday()).isEqualTo(true);
        assertThat(findInstitution.getWorkdays().getIsThursday()).isEqualTo(true);
        assertThat(findInstitution.getAddress().getRn()).isEqualTo("수정비고");
        assertThat(findInstitution.getAddress().getZipNo()).isEqualTo("48940");
    }

    @Test
    @DisplayName("기관 삭제 테스트")
    public void deleteInstitutionTest() {
        // given
        InstitutionCreateDto institutionCreateDto = getTestInstitutionDtoByInsertTestData();
        Workdays workdays = Workdays.createWorkdays(getTestWorkdaysDtoByInsertTestData());
        Address address = Address.createAddressByAddressDto(getTestAddressDtoByInsertTestData());

        Institution insertedInstitution = institutionRepository.save(Institution.createInstitution(institutionCreateDto, workdays, address));

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
        /// TODO: 2022-07-16 Period 분리 후 입력 필요
        return institutionCreateDto;
    }

    public InstitutionCreateDto getTestInstitutionDtoByUpdateTestData() {
        InstitutionCreateDto institutionCreateDto = new InstitutionCreateDto();
        institutionCreateDto.setName("수정테스트1");
        institutionCreateDto.setTelNumber("010-2345-6789");
        /// TODO: 2022-07-16 Period 분리 후 입력 필요
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