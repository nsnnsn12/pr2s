package com.metacrew.pr2s.dto;

import com.metacrew.pr2s.entity.enums.Gender;
import lombok.Getter;
import lombok.Setter;
/**
 * 회원 가입 시 필요한 정보를 담고 있는 DTO
 * @author sunggyu
 * @since 2022.07.01
 */
@Getter @Setter
public class JoinMemberDto {
    private String email;
    private String telNo;
    private String loginId;
    private String password;
    private String name;
    private String birthDay;
    private Gender gender;
}
