package com.example.jpaapp.domain;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import static org.junit.jupiter.api.Assertions.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Slf4j
public class AddressTest {

    @PersistenceContext
    private EntityManager em;

    @Test
    public void test() {
        assertNotNull(em);
    }

    @Test
    @Rollback(false)
    public void test1() {

        Team team = new Team();
        team.setName("team1");

        em.persist(team);

        Member member = new Member();
        member.setUsername("아이유");
        member.setTeam(team);
        member.setAddress(new Address("서울", "역삼로", "12345"));

        em.persist(member);

    }

}
