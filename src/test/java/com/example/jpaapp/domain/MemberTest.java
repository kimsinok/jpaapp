package com.example.jpaapp.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Transactional
@Slf4j
public class MemberTest {

    // @PersistenceContext
    @Autowired
    private EntityManager em;

    @Test
    public void test() {
        assertNotNull(em);
    }

    @Test
    @Rollback(false)
    public void test1() {
        // given
        Team team1 = new Team();
        team1.setName("A");
        em.persist(team1);

        Team team2 = new Team();
        team2.setName("B");
        em.persist(team2);

        for (int i = 1; i <= 10; i++) {
            Member member = new Member();
            member.setUsername("member" + i);
            if (i % 2 == 0) {
                member.setTeam(team1);
            } else {
                member.setTeam(team2);
            }

            em.persist(member);
        }

        // when
        Member foundMember = em.find(Member.class, 1L);

        // then
        log.info("id : {},  username : {}, name : {}", foundMember.getId(), foundMember.getUsername(),
                foundMember.getTeam().getName());

        log.info("foundMember : {}", foundMember.getClass());
        log.info("foundMember.getTeam() : {}", foundMember.getTeam().getClass());

    }
}
