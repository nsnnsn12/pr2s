package com.metacrew.pr2s.repository.joininforepository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class JoinInfoQueryRepositoryImplTest {
    @Autowired
    EntityManager em;
    JoinInfoQueryRepositoryImpl joinInfoQueryRepository;

    @BeforeEach
    public void init(){
        joinInfoQueryRepository = new JoinInfoQueryRepositoryImpl(em);
    }

    @BeforeAll
    static void insertTestData(){
        //초기 데이터를 넣을 때 다른 서비스를 이용해서 넣을 때의 단점
        //사용하고 있는 서비스가 수정되었을 때 연관된 테스트 코드를 다 수정해야 한다.
        //고로 코드의 리팩토링이 자유롭지 못 하다.
        //다른 서비스와의 연관관계를 줄일 수 있는 방법이 무엇이 있는가?
        //필요한 정보를 직접 스크립트 문을 이용하여 작성해넣는 것이다.
        //하지만 스크립트를 작성하여 넣는 것에 단점
        //DB의 스키마가 변경되었을 때 하나 하나 다 수정을 해야 한다.
        //곧 db의 종속적이게 된다.
        //이게 더 큰 문제가 아닌가?
    }
}