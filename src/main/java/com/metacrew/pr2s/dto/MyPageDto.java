package com.metacrew.pr2s.dto;

import com.metacrew.pr2s.entity.Member;
import lombok.Getter;
import lombok.Setter;

/**
 * MyPage 정보를 담고 있는 DTO
 * @author sunggyu
 * @since 2022.07.01
 */
@Getter @Setter
public class MyPageDto {
    private String name;
    private String email;
    private String telNo;
    private String birthDay;
    /**
     * Member ->  MyPageDto
     * @author sunggyu
     * @since 2022.07.01
     * @param member 회원정보
     */
    public MyPageDto(Member member) {
        name = member.getName();
        email = member.getEmail();
        telNo = member.getTelNo();
        birthDay = member.getBirthDay();
    }
}
