package com.metacrew.pr2s.service.institutionservice;

import com.metacrew.pr2s.dto.AddressDto;
import com.metacrew.pr2s.dto.InstitutionCreateDto;
import com.metacrew.pr2s.dto.WorkdaysDto;
import com.metacrew.pr2s.entity.Address;
import com.metacrew.pr2s.entity.Institution;
import com.metacrew.pr2s.entity.Workdays;
import com.metacrew.pr2s.repository.AddressRepository;
import com.metacrew.pr2s.repository.institutionrepository.InstitutionRepository;
import com.metacrew.pr2s.repository.workdaysrepository.WorkdaysRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 사용자가 자신의 정보를 관리하기 위한 서비스 로직을 가지고 있는 클래스이다.
 * @author sunggyu
 * @since 2022.07.07
 */
@Service
@Transactional
@RequiredArgsConstructor
public class InstitutionManagerService implements InstitutionService {
    private final InstitutionRepository institutionRepository;
    private final WorkdaysRepository workdaysRepository;
    private final AddressRepository addressRepository ;

    /**
     * 기관 정보를 입력받아 엔터티를 DB에 저장하고 key 값을 반환
     * @author hyeonwoo
     * @since 2022.07.01
     * @param institutionCreateDto 등록할 기관 정보.
     * @param workdaysDto 등록할 기관의 운영 요일 정보
     * @return 등록한 회원 key 값.
     */
    public Institution joinPr2s(InstitutionCreateDto institutionCreateDto, WorkdaysDto workdaysDto, AddressDto addressDto){
        // 유효성 검사
        validateInstitution(institutionCreateDto, workdaysDto, addressDto);
        
        // 2. 기관 생성
        Workdays workdays = workdaysRepository.save(Workdays.createWorkdays(workdaysDto));
        Address address = addressRepository.save(Address.createAddressByAddressDto(addressDto));
        Institution institution = Institution.createInstitution(institutionCreateDto, workdays, address);
        institutionRepository.save(institution);

        return institution;
    }

    /**
     * 수정하려는 기관에 대한 수정 정보를 입력받아 DB에 반영한 후 key 값 리턴
     * @author hpyeonwoo
     * @since 2022.07.07
     * @param institutionCreateDto 변경할 기관 정보
     * @return 수정한 회원 엔터티
     */
    public Institution updateInstitutionInfo(InstitutionCreateDto institutionCreateDto, WorkdaysDto workdaysDto, AddressDto addressDto, Long updateInstitutionId){
        // 유효성 검사
        validateInstitution(institutionCreateDto, workdaysDto, addressDto);

        // 2. 기관 조회
        Institution findInstitution = getValidInstitution(updateInstitutionId);

        // 3. 기관 정보 수정
        Workdays findWorkdays = workdaysRepository.findById(findInstitution.getWorkdays().getId()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 운영 요일입니다."));
        Address findAddress = getValidAddress(findInstitution.getAddress().getId());
        findWorkdays.updateWorkdays(workdaysDto);
        findAddress.updateAddressByAddressDto(addressDto);

        findInstitution.updateInstitution(institutionCreateDto);

        return findInstitution;
    }

    /**
     * 삭제하려는 기관의 ID를 받아 삭제여부와 삭제일자를 업데이트
     * @author hyeonwoo
     * @since 2022.07.01
     * @param id 변경할 기관 id
     */
    public void deleteInstitution(Long id) {
        Institution findInstitution = institutionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 기관입니다."));
        if(!findInstitution.isDeleted()) findInstitution.deleted();
    }

    /**
     * 삽입 및 수정하려는 기관 정보 유효성 검사
     * 필요 시 구현
     * @author hyeonwoo
     * @since 2022.07.01
     * @param institutionCreateDto 삽입 및 변경할 기관정보
     * @param workdaysDto 삽입 및 변경할 기관의 운영 요일
     * @param addressDto 삽입 및 변경할 기관의 주소 정보
     */
    public void validateInstitution(InstitutionCreateDto institutionCreateDto, WorkdaysDto workdaysDto, AddressDto addressDto) {
        // 추후 유효성 검사 필요 시 작성
    }

    /**
     * 기관 id로 유효한 기관 조회
     * @author hyeonwoo
     * @since 2022.07.22
     * @param institutionId 조회할 기관 Id
     * @return 조회한 기관 엔터티
     */
    public Institution getValidInstitution(Long institutionId) {
        Institution findInstitution = institutionRepository.findById(institutionId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 기관입니다."));
        if (findInstitution.isDeleted()) throw new IllegalArgumentException("삭제 처리된 기관입니다.");
        return findInstitution;
    }

    /**
     * 주소 id로 유효한 주소 조회
     * @author hyeonwoo
     * @since 2022.07.22
     * @param addressId 조회할 주소 Id
     * @return 조회한 주소 엔터티
     */
    public Address getValidAddress(Long addressId) {
        Address findAddress = addressRepository.findById(addressId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 기관 주소 정보입니다."));
        if(findAddress.isDeleted()) throw new IllegalArgumentException("삭제 처리된 주소입니다.");
        return findAddress;
    }
}
