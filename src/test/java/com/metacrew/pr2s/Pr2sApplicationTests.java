package com.metacrew.pr2s;

import com.metacrew.pr2s.domain.Hello;
import com.metacrew.pr2s.domain.QHello;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
class Pr2sApplicationTests {

	@Autowired
	EntityManager em;
	@Test
	void contextLoads() {
		Hello hello = new Hello();
		em.persist(hello);

		JPAQueryFactory query = new JPAQueryFactory(em);
		QHello qHello = QHello.hello;

		Hello result = query.selectFrom(qHello).fetchOne();

		Assertions.assertThat(result).isEqualTo(hello);

		Assertions.assertThat(result.getId()).isEqualTo(hello.getId());
	}

}
