package com.metacrew.pr2s.repository.joininforepository;

import com.metacrew.pr2s.dto.AddressDto;
import com.metacrew.pr2s.dto.InstitutionCreateDto;
import com.metacrew.pr2s.dto.JoinMemberDto;
import com.metacrew.pr2s.dto.WorkdaysDto;
import com.metacrew.pr2s.entity.*;
import com.metacrew.pr2s.repository.AddressRepository;
import com.metacrew.pr2s.repository.institutionrepository.InstitutionRepository;
import com.metacrew.pr2s.repository.memberrepository.MemberRepository;
import com.metacrew.pr2s.repository.workdaysrepository.WorkdaysRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

@SpringBootTest
@Transactional
class JoinInfoRepositoryTest {
    @Autowired
    private JoinInfoRepository joinInfoRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private InstitutionRepository institutionRepository;
    @Autowired
    private WorkdaysRepository workdaysRepository;

    @Autowired
    private AddressRepository addressRepository;
    @Test
    public void findByMemberAndInstitutionAndIsDeletedTest(){
        //given
        Member joinMember = Member.createJoinMember(getJoinMemberDtoByTestData());
        memberRepository.save(joinMember);

        Institution institution = getInstitutionTestData();
        institutionRepository.save(institution);

        JoinInfo joinInfo = JoinInfo.createJoinInfo(joinMember, institution);
        joinInfoRepository.save(joinInfo);

        //when
        boolean notDeleted = false;
        List<JoinInfo> result = joinInfoRepository.findByMemberAndInstitutionAndIsDeleted(joinMember, institution, notDeleted);

        //then
        JoinInfo findJoinInfo = result.get(0);
        assertThat(findJoinInfo.getInstitution().getId()).isEqualTo(institution.getId());
        assertThat(findJoinInfo.getMember().getId()).isEqualTo(joinMember.getId());
    }

    public JoinMemberDto getJoinMemberDtoByTestData(){
        JoinMemberDto joinMemberDto = new JoinMemberDto();
        joinMemberDto.setName("노성규");
        joinMemberDto.setEmail("shtjdrb");
        joinMemberDto.setPassword("shtjdrb123");
        return joinMemberDto;
    }

    public Institution getInstitutionTestData(){
        Workdays workdays = Workdays.createWorkdays(getTestWorkdaysDtoByTestData());
        workdaysRepository.save(workdays);

        Address testAddress = Address.createAddressByAddressDto(getTestAddressDtoByTestData());
        addressRepository.save(testAddress);

        InstitutionCreateDto institutionCreateDto = new InstitutionCreateDto();
        institutionCreateDto.setName("우리은행");
        institutionCreateDto.setTelNumber("010-3013-8124");
        return Institution.createInstitution(institutionCreateDto,workdays, testAddress);
    }

    public WorkdaysDto getTestWorkdaysDtoByTestData() {
        WorkdaysDto workdaysDto = new WorkdaysDto();
        workdaysDto.setIsMonday(true);
        workdaysDto.setIsWednesday(true);
        workdaysDto.setIsFriday(true);
        return workdaysDto;
    }

    public AddressDto getTestAddressDtoByTestData() {
        AddressDto addressDto = new AddressDto();
        addressDto.setRn("비고");
        return addressDto;
    }
}