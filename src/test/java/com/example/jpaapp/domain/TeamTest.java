package com.example.jpaapp.domain;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Transactional
@Slf4j
public class TeamTest {

    @PersistenceContext
    private EntityManager em;

    @Test
    public void test() {
        assertNotNull(em);
    }

    @Test
    @Rollback(false)
    public void test1() {

        // given
        Team team = new Team();
        team.setName("Trainer");

        em.persist(team);

        // when
        Member member1 = new Member();
        member1.setUsername("일길동");
        em.persist(member1);

        Member member2 = new Member();
        member2.setUsername("이길동");
        em.persist(member2);

        // // then
        // team.addMember(member1);
        // team.addMember(member2);

    }

}
