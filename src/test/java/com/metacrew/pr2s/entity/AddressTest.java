package com.metacrew.pr2s.entity;

import com.metacrew.pr2s.dto.AddressDto;
import com.metacrew.pr2s.dto.InstitutionCreateDto;
import com.metacrew.pr2s.dto.WorkdaysDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class AddressTest {
    @Test
    @DisplayName("주소 생성 테스트")
    public void createInstitutionTest() {
        //given
        AddressDto testAddressDto = getTestAddressDtoByInsertTestData();

        //when
        Address address = Address.createAddressByAddressDto(testAddressDto);

        //then
        assertThat(address.getRoadFullAddr()).isEqualTo(testAddressDto.getRoadFullAddr());
        assertThat(address.getRoadAddrPart1()).isEqualTo(testAddressDto.getRoadAddrPart1());
        assertThat(address.getRoadAddrPart2()).isEqualTo(testAddressDto.getRoadAddrPart2());
        assertThat(address.getJibunAddr()).isEqualTo(testAddressDto.getJibunAddr());
        assertThat(address.getEngAddr()).isEqualTo(testAddressDto.getEngAddr());
        assertThat(address.getZipNo()).isEqualTo(testAddressDto.getZipNo());
        assertThat(address.getAdmCd()).isEqualTo(testAddressDto.getAdmCd());
        assertThat(address.getRnMgtSn()).isEqualTo(testAddressDto.getRnMgtSn());
        assertThat(address.getBdMgtSn()).isEqualTo(testAddressDto.getBdMgtSn());
        assertThat(address.getDetBdNmList()).isEqualTo(testAddressDto.getDetBdNmList());
        assertThat(address.getBdNm()).isEqualTo(testAddressDto.getBdNm());
        assertThat(address.getBdKdcd()).isEqualTo(testAddressDto.getBdKdcd());
        assertThat(address.getSiNm()).isEqualTo(testAddressDto.getSiNm());
        assertThat(address.getSggNm()).isEqualTo(testAddressDto.getSggNm());
        assertThat(address.getEmdNm()).isEqualTo(testAddressDto.getEmdNm());
        assertThat(address.getLiNm()).isEqualTo(testAddressDto.getLiNm());
        assertThat(address.getRn()).isEqualTo(testAddressDto.getRn());
        assertThat(address.getUdrtYn()).isEqualTo(testAddressDto.getUdrtYn());
        assertThat(address.getBuldMnnm()).isEqualTo(testAddressDto.getBuldMnnm());
        assertThat(address.getBuldSlno()).isEqualTo(testAddressDto.getBuldSlno());
        assertThat(address.getMtYn()).isEqualTo(testAddressDto.getMtYn());
        assertThat(address.getLnbrMnnm()).isEqualTo(testAddressDto.getLnbrMnnm());
        assertThat(address.getLnbrSlno()).isEqualTo(testAddressDto.getLnbrSlno());
        assertThat(address.getEmdNo()).isEqualTo(testAddressDto.getEmdNo());
        assertThat(address.getEntX()).isEqualTo(testAddressDto.getEntX());
        assertThat(address.getEntY()).isEqualTo(testAddressDto.getEntY());

    }

    public AddressDto getTestAddressDtoByInsertTestData() {
        AddressDto testAddressDto = new AddressDto();
        testAddressDto.setRoadFullAddr("서울시 마포구 월드컵북로 434");
        return testAddressDto;
    }
}