package com.example.jpaapp.domain;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Slf4j
public class PostTest {

    @PersistenceContext
    private EntityManager em;

    @Test
    public void test() {
        assertNotNull(em);
    }

    @Test
    @Rollback(false)
    public void test1() {

        // Entity
        Post post = new Post();
        post.setTitle("title");
        post.setContent("content");

        // Value Object
        Attachment attachment1 = new Attachment();
        attachment1.setFilename("a.txt");
        attachment1.setFilepath("/upload");
        attachment1.setSize(10000);
        post.addAttachment(attachment1);

        Attachment attachment2 = new Attachment();
        attachment2.setFilename("a.txt");
        attachment2.setFilepath("/upload");
        attachment2.setSize(20000);
        post.addAttachment(attachment2);

        em.persist(post);

        post.getAttachments().stream().forEach(attachment -> {

            log.info("filename : {}, size : {}, filepath : {}", attachment.getFilename(), attachment.getSize(),
                    attachment.getFilepath());

        });

    }
}
