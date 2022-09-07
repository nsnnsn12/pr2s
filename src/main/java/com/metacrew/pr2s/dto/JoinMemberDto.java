package com.metacrew.pr2s.dto;

import com.metacrew.pr2s.entity.enums.Gender;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

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

    @NotBlank(message = "전화번호는 필수 입력 값입니다.")
    @Size(min = 11, max = 11, message = "전화번호가 유효하지 않습니다.")
    private String telNo;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    private String password;

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;

    @NotBlank(message = "닉네임은 필수 입력 값입니다.")
    private String nickname;

    @NotBlank(message = "비밀번호를 한 번 더 입력해주세요")
    private String repeatPassword;

    @AssertTrue(message = "약관에 동의해주세요")
   private boolean agreed;
}
