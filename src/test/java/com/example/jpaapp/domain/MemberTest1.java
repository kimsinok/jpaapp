package com.example.jpaapp.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.example.jpaapp.respository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Transactional
@Slf4j
public class MemberTest1 {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void test() {
        assertNotNull(memberRepository);
    }

    @Test
    public void test1() {

        // given
        // when
        // List<Member> members = memberRepository.findAll();
        List<Member> members = memberRepository.findMembers();

        // then
        assertTrue(members.size() > 0);

        members.forEach(member -> {
            log.info("id : {}, username : {}, name : {}", member.getId(), member.getUsername(),
                    member.getTeam().getName());
        });

    }

}
