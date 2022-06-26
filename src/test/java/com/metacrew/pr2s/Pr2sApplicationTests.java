package com.metacrew.pr2s;


import com.metacrew.pr2s.entity.Address;
import com.metacrew.pr2s.entity.Institution;
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
		Institution institution = em.find(Institution.class, 1L);
	}

}
