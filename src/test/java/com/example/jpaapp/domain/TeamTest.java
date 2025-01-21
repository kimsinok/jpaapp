package com.example.jpaapp.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Slf4j
public class TeamTest {

    @Autowired
    private EntityManager em;

    @Test
    public void test() {
        assertNotNull(em);
    }

    @Test
    @Rollback(false)
    public void test연관관계주인() {
        // given
        Member member = new Member();
        member.setUsername("Alice");
        member.setRoleType(RoleType.MEMBER);
        member.setDescription("간단한 회원정보입니다.");
        member.setCreateDate(LocalDateTime.now());

        em.persist(member);

        // when
        Team team = new Team();
        team.setName("A");
        team.addMember(member);

        em.persist(team);

        // then

    }

    @Test
    @Rollback(false)
    public void test연관관계주인1() {
        // given
        Team team = new Team();
        team.setName("B");

        em.persist(team);

        // when
        Member member = new Member();
        member.setUsername("Bob");
        member.setRoleType(RoleType.ADMIN);
        member.setDescription("간단한 회원정보입니다.");
        member.setCreateDate(LocalDateTime.now());

        member.setTeam(team);

        em.persist(member);

        // then
        assertEquals(member.getTeam().getName(), team.getName());

        log.info("username : {}, name : {}", member.getUsername(), member.getTeam().getName());

    }

}
