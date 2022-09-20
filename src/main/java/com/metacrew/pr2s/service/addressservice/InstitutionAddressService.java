package com.metacrew.pr2s.service.addressservice;

import com.metacrew.pr2s.dto.AddressDto;
import com.metacrew.pr2s.entity.Address;
import com.metacrew.pr2s.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 기관이 자신의 주소 정보를 관리하기 위한 서비스 로직을 가지고 있는 클래스이다.
 * @author nahyun
 * @since 2022.09.09
 */
@Service
@Transactional
@RequiredArgsConstructor
public class InstitutionAddressService implements AddressService {

    @Autowired
    AddressRepository addressRepository;


    /**
     * 주소 정보를 입력받아 엔터티를 DB에 저장하고 key 값을 반환
     * @author nahyun
     * @since 2022.09.15
     * @param addressDto 등록할 주소 정보.
     * @param addressDto 등록할 기관의 주소 정보.
     * @return 등록한 기관 key 값.
     */

    @Override
    public Address saveAddress(AddressDto addressDto){
        //유효성검사
        if(validateAddress(addressDto)) throw new IllegalStateException("유효하지 않은 주소입니다");

        //중복여부확인
        if(duplicateAddress(addressDto)) throw new IllegalStateException("중복 주소입니다.");

        Address address = addressRepository.save(Address.createAddressByAddressDto(addressDto));
        return address;
    }

    //유효성 검사
    public boolean validateAddress(AddressDto addressDto){
        //주소 입력후 셋팅되는 값 :: zipNo, roadFullAddr
        String zipNo = addressDto.getZipNo();
        String roadFullAddr = addressDto.getRoadFullAddr();

        if(zipNo=="" || roadFullAddr==""){
            return true;
        }else{
            return false;
        }
    }

    public boolean duplicateAddress(AddressDto addressDto){
        String roadFullAddr = addressDto.getRoadFullAddr();

        boolean dupYn = addressRepository.existsByRoadFullAddr(roadFullAddr);

        return dupYn;
    }

}
