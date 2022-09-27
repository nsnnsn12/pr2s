package com.metacrew.pr2s.entity;

import com.metacrew.pr2s.dto.JoinMemberDto;
import com.metacrew.pr2s.dto.MyPageDto;
import com.metacrew.pr2s.entity.base.BaseEntity;
import com.metacrew.pr2s.entity.enums.MemberAuthority;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

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

    @JoinColumn(name = "file_id")
    @OneToOne(fetch = FetchType.LAZY)
    private FileInfo imageFileInfo;

    @Column
    private String email;
    @Column
    private String telNo;

    @Column
    private String password;

    @Column
    private String name;

    @Column
    private String nickname;

    @Column @Enumerated(EnumType.STRING)
    private MemberAuthority memberAuthority;


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
    }

    /**
     * JoinMemberDto -> Member
     * @author sunggyu
     * @since 2022.07.02
     * @param joinMemberDto 엔티티로 변환할 값
     * @return Member 엔티티로 변환한 값
     */
    public static Member createJoinMember(JoinMemberDto joinMemberDto){
        Member member = new Member();
        member.email = joinMemberDto.getEmail();
        member.telNo = joinMemberDto.getTelNo();
        member.password = joinMemberDto.getPassword();
        member.name = joinMemberDto.getName();
        member.nickname = joinMemberDto.getNickname();
        member.memberAuthority = MemberAuthority.ROLE_USER;
        return member;
    }

    public void encryptPassword(PasswordEncoder passwordEncoder) {
        password = passwordEncoder.encode(password);
    }
}
