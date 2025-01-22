package com.example.jpaapp.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Slf4j
public class ItemTest {

    @Autowired
    private EntityManager em;

    @Test
    public void test() {
        assertNotNull(em);
    }

    @Test
    @Rollback(false)
    public void test아이템() {

        // given
        // when

        Book book = new Book();
        book.setName("book1");
        book.setIsbn("isbn1");
        book.setAuthor("author1");

        em.persist(book);

        Movie movie = new Movie();
        movie.setName("movie1");
        movie.setDirector("director1");
        movie.setActor("actor1");

        em.persist(movie);

        // then
        Book foundBook = em.find(Book.class, book.getId());

        assertEquals(foundBook.getAuthor(), book.getAuthor());

    }

}
