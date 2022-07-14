package com.metacrew.pr2s.repository.joininforepository;

import com.metacrew.pr2s.dto.InstitutionDto;
import com.metacrew.pr2s.dto.JoinMemberDto;
import com.metacrew.pr2s.dto.WorkdaysDto;
import com.metacrew.pr2s.entity.Institution;
import com.metacrew.pr2s.entity.JoinInfo;
import com.metacrew.pr2s.entity.Member;
import com.metacrew.pr2s.entity.Workdays;
import com.metacrew.pr2s.repository.institutionrepository.InstitutionRepository;
import com.metacrew.pr2s.repository.joinforepository.JoinInfoRepository;
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

    @Test
    public void findByMemberAndInstitutionAndIsDeletedTest(){
        //given
        Member joinMember = Member.createJoinMember(getJoinMemberDtoByTestData(), null, null);
        memberRepository.save(joinMember);


        Institution institution = getInstitutionDtoTestData();
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
        joinMemberDto.setLoginId("shtjdrb");
        joinMemberDto.setPassword("shtjdrb123");
        joinMemberDto.setBirthDay("19950914");
        return joinMemberDto;
    }

    public Institution getInstitutionDtoTestData(){
        WorkdaysDto workdaysDto = new WorkdaysDto();
        workdaysDto.setIsFriday(true);
        workdaysDto.setIsMonday(true);
        Workdays workdays = Workdays.createWorkdays(workdaysDto);
        workdaysRepository.save(workdays);
        InstitutionDto institutionDto = new InstitutionDto();
        institutionDto.setName("우리은행");
        institutionDto.setTelNumber("010-3013-8124");

        return Institution.createInstitution(institutionDto, workdays);
    }

}