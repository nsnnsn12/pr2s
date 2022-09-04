package com.metacrew.pr2s.dto;

import com.metacrew.pr2s.entity.enums.Gender;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 회원 가입 시 필요한 정보를 담고 있는 DTO
 * @author sunggyu
 * @since 2022.07.01
 */
@Getter @Setter
public class JoinMemberDto {
    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식에 맞지 않습니다.")
    private String email;
    private String telNo;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    private String password;

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;
    private String nickname;
    private String birthDay;
    private Gender gender;
}
