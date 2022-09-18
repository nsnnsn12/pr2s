package com.metacrew.pr2s.service.addressservice;

import com.metacrew.pr2s.dto.AddressDto;
import com.metacrew.pr2s.entity.Address;

/**
 * 주소 정보를 관리하기 위한 메소드를 정의한 인터페이스이다.
 * @author nahyun
 * @since 2022.09.09
 */
public interface AddressService {

    Address saveAddress(AddressDto addressDto);
}
