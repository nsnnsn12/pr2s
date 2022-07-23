package com.metacrew.pr2s.service.institutionmemberservice;

import com.metacrew.pr2s.dto.condition.JoinInfoConditionDto;
import com.metacrew.pr2s.entity.JoinInfo;
import com.metacrew.pr2s.repository.joininforepository.JoinInfoRepository;
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
    private final JoinInfoRepository joinInfoRepository;
    // TODO: 2022-07-22 가입기관정보를 조회한다.
    //조회 조건은? 가입요청승인여부, 가입일자
    public void searchJoinInfo(JoinInfoConditionDto joinInfoConditionDto){
        JoinInfo joinInfo = joinInfoRepository.findById(joinInfoConditionDto.getInstitutionId()).orElseThrow(() -> new IllegalStateException("존재하지 않는 기관정보입니다."));
        if(joinInfo.isDeleted()) throw new IllegalStateException("존재하지 않는 기관정보입니다.");

    }
}
