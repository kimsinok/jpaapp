package com.example.jpaapp.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

import static org.junit.jupiter.api.Assertions.*;

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
    public void testSave() {
        // 1. New
        Member member = new Member();
        member.setUsername("lee");

        em.persist(member);

        log.info("id : {}", member.getId());

        // 1차 캐시에서 엔티티 조회
        Member foundMember = em.find(Member.class, member.getId());

        assertEquals(foundMember.getUsername(), member.getUsername());
    }

    @Test
    public void testFind() {
        // given
        Long id = 1L;

        // when
        // DB
        Member foundMember1 = em.find(Member.class, id);

        // 1차 케시
        Member foundMember2 = em.find(Member.class, id);

        // then
        assertNotNull(foundMember1);

        assertEquals(foundMember2.getUsername(), foundMember1.getUsername());

    }

    @Test
    public void test앤티티동일성보장() {
        // given
        // when
        Member foundMember1 = em.find(Member.class, 1L);

        Member foundMember2 = em.find(Member.class, 1L);

        // then
        assertEquals(foundMember1, foundMember2);

        log.info("foundMember1 == foundMember2 : {}", foundMember1 == foundMember2);

    }

    @Test
    @Rollback(false)
    public void test엔티티변경() {

        // given
        Long id = 1L;

        // when
        Member member = em.find(Member.class, id);

        member.setUsername("앨리스");

        // then
        assertEquals(member.getUsername(), "앨리스");

    }

    @Test
    @Rollback(false)
    public void test엔티티변경1() {

        // given
        Member member = new Member();
        member.setUsername("park");
        em.persist(member);

        // when
        member.setUsername("박");

        Member foundMember = em.find(Member.class, member.getId());

        // then
        assertTrue(member.getUsername().equals("박"));

        assertEquals(foundMember.getUsername(), member.getUsername());

    }

    @Test
    @Rollback(false)
    public void test엔티티삭제() {
        // given
        Long id = 1L;

        // when
        Member member = em.find(Member.class, id);

        if (member != null) {
            em.remove(member);
        }

        Member foundMember = em.find(Member.class, id);

        // then
        assertNull(foundMember);

    }

}
