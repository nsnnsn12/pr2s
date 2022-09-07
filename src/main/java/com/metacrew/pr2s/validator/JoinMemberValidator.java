package com.metacrew.pr2s.validator;

import com.metacrew.pr2s.dto.JoinMemberDto;
import com.metacrew.pr2s.repository.memberrepository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@RequiredArgsConstructor
@Component
public class JoinMemberValidator extends AbstractValidator<JoinMemberDto> {
    private final MemberRepository memberRepository;

    @Override
    protected void doValidate(JoinMemberDto dto, Errors errors) {
        if(!dto.getPassword().equals(dto.getRepeatPassword())){
            errors.rejectValue("repeatPassword", "이메일 중복 오류", "비밀번호가 동일하지 않습니다.");
        }
        if(memberRepository.findByEmail(dto.getEmail()).isPresent()){
            errors.rejectValue("email", "이메일 중복 오류", "이미 사용중인 이메일 입니다.");
        }
    }
}
