package com.metacrew.pr2s.service.memberservice;

import com.metacrew.pr2s.dto.AddressDto;
import com.metacrew.pr2s.dto.JoinMemberDto;
import com.metacrew.pr2s.dto.MyPageDto;
import com.metacrew.pr2s.entity.Member;

/**
 * 회원 정보를 관리하기 위한 메소드를 정의한 인터페이스이다.
 * @author sunggyu
 * @since 2022.06.25
 */
public interface MemberService {
    /**
     * 회원가입
     * 회원, 주소, 파일 정보를 입력받아 회원가입 처리를 하고 회원 정보를 리턴한다.
     *
     * @param joinMember 회원 정보.
     * @param addressDto 주소 정보
     * @param fileId 파일 정보
     * @return 회원정보
     * @throws IllegalStateException 존재하지 않는 파일정보에 대해 요청할 경우
     * @throws IllegalStateException 이미 존재하는 login id를 사용하려는 경우
     * @since 2022.07.07
     * @author sunggyu
     */
    Member join(JoinMemberDto joinMember, AddressDto addressDto, Long fileId);

    /**
     * 마이페이지 정보 조회
     * Member table id를 입력받아 MyPage 정보를 조회하여 리턴한다.
     * @param id Member table id
     * @return MyPageDto 마이페이지 정보
     * @throws IllegalStateException 존재하지 않거나 삭제 처리된 회원정보를 조회하는 경우
     * @author sunggyu
     * @since 2022.07.07
     */
    MyPageDto getMyPageInfo(Long id);

    /**
     * 마이페이지 수정
     * 회원 수정 정보와 Member table id를 입력받아 회원 정보를 수정하고 Member table id를 리턴한다.
     * @param myPageDto 회원 수정 정보.
     * @param id Member table id
     * @return Member table id
     * @throws IllegalStateException 존재하지 않거나 삭제 처리된 회원정보를 수정하려는 경우
     * @author sunggyu
     * @since 2022.07.07
     */
    Long updateForMyPage(MyPageDto myPageDto, Long id);

    /**
     * 회원탈퇴
     * Member table id를 입력받아 회원탈퇴처리한다.
     * @param id Member table id
     * @throws IllegalStateException 존재하지 않는 회원정보를 삭제하려는 경우
     * @author sunggyu
     * @since 2022.07.07
     */
    void removeAccount(Long id);

    /**
     * 기관가입요청
     * Member table id와  institution table id를 입력받아
     * 기관 가입 처리 후 JoinInfo Table id값을 리턴한다.
     * @param memberId Member table id
     * @param institutionId institution table id
     * @return JoinInfo Table id
     * @throws IllegalStateException 회원정보 혹은 기관정보가 존재하지 않는 경우
     * @throws IllegalStateException 이미 존재하는 기관가입을 재요청하는 경우
     * @author sunggyu
     * @since 2022.07.07
     */
    Long requestJoinOfInstitution(Long memberId, Long institutionId);

    /**
     * 기관가입요청 취소
     * JoinInfo Table id 입력받아 기관 가입요청을 취소한다.
     * @param id JoinInfo table id
     * @throws IllegalStateException 존재하지 않는 기관가입 요청을 삭제하려는 경우
     * @author sunggyu
     * @since 2022.07.07
     */
    void cancelRequestedJoinOfInstitution(Long id);
}
