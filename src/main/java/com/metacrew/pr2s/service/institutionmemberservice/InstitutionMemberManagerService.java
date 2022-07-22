package com.metacrew.pr2s.service.institutionmemberservice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 각 기관의 속한 회원 정보를 기관 관리자가 관리하기 위한 서비스 로직을 가지고 있는 클래스이다.
 * @author sunggyu
 * @since 2022.07.22
 */
@Service
@Transactional
@RequiredArgsConstructor
public class InstitutionMemberManagerService implements InstitutionMemberService{

}
