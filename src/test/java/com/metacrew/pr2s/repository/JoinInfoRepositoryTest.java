package com.metacrew.pr2s.repository;

import com.metacrew.pr2s.dto.InstitutionDto;
import com.metacrew.pr2s.dto.JoinMemberDto;
import com.metacrew.pr2s.entity.Institution;
import com.metacrew.pr2s.entity.JoinInfo;
import com.metacrew.pr2s.entity.Member;
import com.metacrew.pr2s.repository.institutionrepository.InstitutionRepository;
import com.metacrew.pr2s.repository.memberrepository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

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

    @Test
    public void findByMemberAndInstitutionAndIsDeletedTest(){
        //given
        Member joinMember = Member.createJoinMember(getJoinMemberDtoByTestData(), null);
        memberRepository.save(joinMember);

        Institution institution = new Institution(getInstitutionDtoTestData());
        institutionRepository.save(institution);

        boolean deleted = true;
        List<JoinInfo> result = joinInfoRepository.findByMemberAndInstitutionAndIsDeleted(joinMember, institution, deleted);
    }

    public JoinMemberDto getJoinMemberDtoByTestData(){
        JoinMemberDto joinMemberDto = new JoinMemberDto();
        joinMemberDto.setName("노성규");
        joinMemberDto.setLoginId("shtjdrb");
        joinMemberDto.setPassword("shtjdrb123");
        joinMemberDto.setBirthDay("19950914");
        return joinMemberDto;
    }

    public InstitutionDto getInstitutionDtoTestData(){
        InstitutionDto institutionDto = new InstitutionDto();
        institutionDto.setName("우리은행");
        institutionDto.setTelNumber("010-3013-8124");
        return institutionDto;
    }

}