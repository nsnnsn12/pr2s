package com.metacrew.pr2s;

import com.metacrew.pr2s.dto.AddressDto;
import com.metacrew.pr2s.dto.InstitutionCreateDto;
import com.metacrew.pr2s.dto.JoinMemberDto;
import com.metacrew.pr2s.dto.WorkdaysDto;
import com.metacrew.pr2s.entity.*;
import com.metacrew.pr2s.entity.enums.FileType;
import com.metacrew.pr2s.repository.AddressRepository;
import com.metacrew.pr2s.repository.FileRepository;
import com.metacrew.pr2s.repository.institutionrepository.InstitutionRepository;
import com.metacrew.pr2s.repository.joininforepository.JoinInfoRepository;
import com.metacrew.pr2s.repository.memberrepository.MemberRepository;
import com.metacrew.pr2s.repository.workdaysrepository.WorkdaysRepository;
import com.metacrew.pr2s.service.institutionservice.InstitutionService;
import com.metacrew.pr2s.service.memberservice.ClientMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
@RequiredArgsConstructor
public class TestDataLoader implements CommandLineRunner {
    private final MemberRepository memberRepository;
    private final InstitutionRepository institutionRepository;
    private final JoinInfoRepository joinInfoRepository;
    private final WorkdaysRepository workdaysRepository;
    private final FileRepository fileRepository;
    private final AddressRepository addressRepository;

    static final int ADD_SIZE = 10;
    public void run(String... args)  {
        init();
    }
    public void init(){
        addAddress();
        addFiles();
        addWordDays();
        addMembers();
        addInstitutions();
        addJoinInfo();
    }

    public void addMembers(){
        List<Address> addresses = addressRepository.findAll();
        List<File> files = fileRepository.findAll();
        for(int i = 0; i < ADD_SIZE; i++){
            JoinMemberDto joinMemberDto = new JoinMemberDto();
            joinMemberDto.setName("박현우"+i);
            joinMemberDto.setEmail("kqrgusdn"+i + "@naver.com");
            joinMemberDto.setPassword("qkrgusdn"+i);
            joinMemberDto.setBirthDay("1995010"+i);
            Member joinMember = Member.createJoinMember(joinMemberDto, addresses.get(i), files.get(i));
            memberRepository.save(joinMember);
        }
    }

    public void addInstitutions(){
        List<Workdays> workdays = workdaysRepository.findAll();
        List<Address> addresses = addressRepository.findAll();
        for(int i = 0; i < ADD_SIZE; i++) {
            InstitutionCreateDto institutionCreateDto = new InstitutionCreateDto();
            institutionCreateDto.setName("우리은행"+i);
            institutionCreateDto.setTelNumber("010-1234-1234");
            Institution institution = Institution.createInstitution(institutionCreateDto, workdays.get(i), addresses.get(i));
            institutionRepository.save(institution);
        }
    }

    public void addAddress(){
        for(int i = 0; i < ADD_SIZE; i++){
            AddressDto addressDto = new AddressDto();
            addressDto.setSggNm("서울시 마포구"+i);
            addressRepository.save(Address.createAddressByAddressDto(addressDto));
        }
    }

    public void addFiles(){
        for(int i = 0; i < ADD_SIZE; i++){
            File file = File.createFile("file" + i, "/path/" + i, FileType.img);
            fileRepository.save(file);
        }
    }

    public void addWordDays(){
        WorkdaysDto workdaysDto = new WorkdaysDto();
        workdaysDto.setIsFriday(true);
        workdaysDto.setIsMonday(true);
        for (int i = 0; i < ADD_SIZE; i++) {
            Workdays workdays = Workdays.createWorkdays(workdaysDto);
            workdaysRepository.save(workdays);
        }
    }

    public void addJoinInfo(){
        List<Member> members = memberRepository.findAll();
        List<Institution> institutions = institutionRepository.findAll();

        for (int i = 0; i < ADD_SIZE/2; i++) {
            JoinInfo joinInfo = JoinInfo.createJoinInfo(members.get(i), institutions.get(i));
            joinInfo.accept();
            joinInfoRepository.save(joinInfo);
        }

        for (int i = ADD_SIZE/2; i < ADD_SIZE; i++) {
            JoinInfo joinInfo = JoinInfo.createJoinInfo(members.get(i), institutions.get(i));
            joinInfoRepository.save(joinInfo);
        }
    }
}
