package com.metacrew.pr2s.entity;

import com.metacrew.pr2s.dto.JoinMemberDto;
import com.metacrew.pr2s.dto.MyPageDto;
import com.metacrew.pr2s.entity.base.BaseEntity;
import com.metacrew.pr2s.entity.enums.Gender;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Member 테이블과 매핑되는 엔티티이다.
 * @author sunggyu
 * @since 2022.06.17
 */
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member extends BaseEntity {
    @Id
    @Column(name = "member_id") @GeneratedValue
    private Long id;

    @JoinColumn(name = "address_id")
    @OneToOne(fetch = FetchType.LAZY)
    private Address address;

    @JoinColumn(name = "file_id")
    @OneToOne(fetch = FetchType.LAZY)
    private FileInfo imageFileInfo;

    @Column
    private String email;
    @Column
    private String telNo;

    @Column
    private String loginId;
    @Column
    private String password;
    @Column
    private String name;

    @Column
    private String birthDay;

    @Enumerated(EnumType.STRING)
    @Column
    private Gender gender;

    /**
     * 수정해야 할 마이페이지 정보를 입력받아 수정한다.
     * @author sunggyu
     * @since 2022.07.10
     * @param myPageDto 수정해야 할 정보
     */
    public void updateForMyPage(MyPageDto myPageDto){
        name = myPageDto.getName();
        email = myPageDto.getEmail();
        telNo = myPageDto.getTelNo();
        birthDay = myPageDto.getBirthDay();
    }

    /**
     * JoinMemberDto -> Member
     * @author sunggyu
     * @since 2022.07.02
     * @param joinMemberDto 엔티티로 변환할 값
     * @return Member 엔티티로 변환한 값
     */
    public static Member createJoinMember(JoinMemberDto joinMemberDto, Address address, FileInfo fileInfo){
        Member member = new Member();
        member.email = joinMemberDto.getEmail();
        member.telNo = joinMemberDto.getTelNo();
        member.loginId = joinMemberDto.getLoginId();
        member.password = joinMemberDto.getPassword();
        member.name = joinMemberDto.getName();
        member.birthDay = joinMemberDto.getBirthDay();
        member.gender = joinMemberDto.getGender();
        member.imageFileInfo = fileInfo;
        member.address = address;
        return member;
    }
}
