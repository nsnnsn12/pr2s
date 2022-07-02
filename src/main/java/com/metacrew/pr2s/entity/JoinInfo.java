package com.metacrew.pr2s.entity;

import com.metacrew.pr2s.entity.base.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * JoinInfo 테이블과 매핑되는 엔티티이다.
 * @author nahyun
 * @since 2022.06.23
 */

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class JoinInfo extends BaseEntity {
    @Id
    @Column(name = "join_info_id") @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "institution_id")
    private Institution institution;

    @Column
    private Boolean isApproved;

    @Column
    private LocalDateTime joinDate;

    @Column
    private LocalDateTime resignDate;

    @Column
    private Boolean isForceResigned;

    public static JoinInfo createJoinInfo(Member member, Institution institution){
        JoinInfo joinInfo = new JoinInfo();
        joinInfo.member = member;
        joinInfo.institution = institution;
        return joinInfo;
    }
}
