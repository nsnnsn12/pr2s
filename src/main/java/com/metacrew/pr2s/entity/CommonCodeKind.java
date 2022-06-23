package com.metacrew.pr2s.entity;

import com.metacrew.pr2s.entity.base.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
/**
 * CommonCodeKind 테이블과 매핑되는 엔티티이다.
 * 공통코드를 수정 및 조회할 수 있다.
 * @author dongmin
 * @since 2022.06.23
 */
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CommonCodeKind extends BaseEntity {
    @Id
    @Column(name = "common_code_knd_cd_id") @GeneratedValue
    private Long id;

    @Column
    private String kndName;

    @Column
    private String kndExplain;
}
