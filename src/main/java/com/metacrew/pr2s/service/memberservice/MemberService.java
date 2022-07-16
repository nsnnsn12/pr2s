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
     * 회원가입용 DTO와 Address Dto를 입력받아 회원가입 처리하고 회원 정보를 리턴한다.
     *
     * @param joinMember 회원 정보.
     * @param addressDto 주소 정보
     * @return 회원정보
     * @author sunggyu
     * @since 2022.07.07
     */
    Member join(JoinMemberDto joinMember, AddressDto addressDto, Long fileId);

    /**
     * 마이페이지 정보 조회
     * 입력받은 id로 MyPage 정보를 조회하여 리턴한다.
     * @author sunggyu
     * @since 2022.07.07
     * @param id Member table id
     * @return MyPageDto 마이페이지 정보
     */
    MyPageDto getMyPageInfo(Long id);

    /**
     * 마이페이지 수정
     * 마이페이지용 DTO와 Member table id 입력받아 정보 수정 후 Member table id를 리턴한다.
     * @author sunggyu
     * @since 2022.07.07
     * @param myPageDto 마이페이지 정보.
     * @param id Member table id
     * @return Member table id
     */
    Long updateForMyPage(MyPageDto myPageDto, Long id);

    /**
     * 회원탈퇴
     * Member table id 입력받아 회원탈퇴처리한다.
     * @author sunggyu
     * @since 2022.07.07
     * @param id Member table id
     */
    void removeAccount(Long id);

    /**
     * 기관가입요청
     * Member table id와  institution table id를 입력받아
     * 기관 가입 처리 후 JoinInfo Table id값을 리턴한다.
     * @author sunggyu
     * @since 2022.07.07
     * @param memberId Member table id
     * @param institutionId institution table id
     * @return JoinInfo Table id
     */
    Long requestJoinOfInstitution(Long memberId, Long institutionId);

    /**
     * 기관가입요청 취소
     * JoinInfo Table id 입력받아 기관 가입요청을 취소한다.
     * @author sunggyu
     * @since 2022.07.07
     * @param id JoinInfo table id
     */
    void cancelRequestedJoinOfInstitution(Long id);



}
