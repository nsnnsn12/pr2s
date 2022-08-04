package com.metacrew.pr2s.service.memberservice;

import com.metacrew.pr2s.dto.AddressDto;
import com.metacrew.pr2s.dto.JoinMemberDto;
import com.metacrew.pr2s.dto.MyPageDto;
import com.metacrew.pr2s.entity.*;
import com.metacrew.pr2s.repository.AddressRepository;
import com.metacrew.pr2s.repository.FileRepository;
import com.metacrew.pr2s.repository.joininforepository.JoinInfoRepository;
import com.metacrew.pr2s.repository.institutionrepository.InstitutionRepository;
import com.metacrew.pr2s.repository.memberrepository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * 사용자가 자신의 정보를 관리하기 위한 서비스 로직을 가지고 있는 클래스이다.
 * @author sunggyu
 * @since 2022.07.07
 */
@Service
@Transactional
@RequiredArgsConstructor
public class ClientMemberService implements MemberService{
    private final MemberRepository memberRepository;
    private final AddressRepository addressRepository;
    private final InstitutionRepository institutionRepository;
    private final JoinInfoRepository joinInfoRepository;
    private final FileRepository fileRepository;

    @Override
    public Member join(JoinMemberDto joinMember, AddressDto addressDto, Long fileId){
        validateDuplicateLoginId(joinMember.getLoginId());
        Address address = addressRepository.save(Address.createAddressByAddressDto(addressDto));
        File file = null;
        if(fileId != null){
            file = fileRepository.findById(fileId).orElseThrow(() -> new IllegalStateException("존재하지 않는 파일정보입니다."));
        }

        Member member = Member.createJoinMember(joinMember, address, file);
        return memberRepository.save(member);
    }

    private void validateDuplicateLoginId(String loginId){
        Optional<Member> findMember = memberRepository.findByLoginId(loginId);
        if(findMember.isPresent()) throw new IllegalStateException("이미 존재하는 로그인 ID입니다.");
    }
    
    // 로그인 검사
    public boolean validateLoginCheck(String loginId, String password){
        Member findMember = memberRepository.findByLoginId(loginId).orElseThrow(() -> new IllegalStateException("존재하지 않는 아이디입니다."));
        return findMember.getPassword().equals(password);
    }

    //존재하고 삭제되지 않은 회원정보 조회
    public Member getExistedMember(Long id){
        Member findMember = memberRepository.findById(id).orElseThrow(() -> new IllegalStateException("존재하지 않는 회원정보입니다."));
        if(findMember.isDeleted()) throw new IllegalStateException("존재하지 않는 회원정보입니다.");
        return findMember;
    }

    public Institution getExistedInstitution(Long id){
        Institution institution = institutionRepository.findById(id)
                .orElseThrow(()-> new IllegalStateException("기관정보가 존재하지 않습니다."));
        if(institution.isDeleted()) throw new IllegalStateException("기관정보가 존재하지 않습니다.");
        return institution;
    }

    // TODO: 2022-07-21 회원 인증 로직 추가 필요
    // TODO: 2022-07-21 회원 인증에 따른 회원 중복 가입 여부 처리 필요

    @Override
    public MyPageDto getMyPageInfo(Long id) {
        return new MyPageDto(getExistedMember(id));
    }

    @Override
    public Long updateForMyPage(MyPageDto myPageDto, Long id){
        Member member = getExistedMember(id);
        member.updateForMyPage(myPageDto);
        return member.getId();
    }

    @Override
    public void removeAccount(Long id){
        Member findMember = getExistedMember(id);
        if(!findMember.isDeleted()) findMember.deleted();
    }

    @Override
    public Long requestJoinOfInstitution(Long memberId, Long institutionId){
        Member member = getExistedMember(memberId);
        Institution institution = getExistedInstitution(institutionId);

        //회원정보와 기관정보를 조회한다.
        //해당 가입 기관 정보가 존재하고 삭제처리가 되어 있지 않다면 등록할 수 없다.
        validateDuplicateJoinInfo(member, institution);
        JoinInfo joinInfo = JoinInfo.createJoinInfo(member, institution);
        joinInfoRepository.save(joinInfo);
        return joinInfo.getId();
    }

    /**
     * 기관가입요청 중복 확인
     * 삭제여부가 false인 joinfo 정보 중
     * 동일한 member와 institution 정보를 가진 데이터가 있다면
     * 예외를 던진다.
     * @author sunggyu
     * @since 2022.07.02
     * @param member Member 정보
     * @param institution Institution 정보
     */
    private void validateDuplicateJoinInfo(Member member, Institution institution){
        List<JoinInfo> result = joinInfoRepository.findByMemberAndInstitutionAndIsDeleted(member, institution, false);
        if(result.size() > 0) throw new IllegalStateException("이미 존재하는 가입 요청입니다.");
    }

    @Override
    public void cancelRequestedJoinOfInstitution(Long id) {
        JoinInfo joinInfo = joinInfoRepository.findById(id).orElseThrow(() -> new IllegalStateException("존재하지 않는 가입정보입니다."));
        if(!joinInfo.isDeleted()) joinInfo.deleted();
    }
}
