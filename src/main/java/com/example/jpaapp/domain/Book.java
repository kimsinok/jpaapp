package com.example.jpaapp.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@DiscriminatorValue(value = "book")
public class Book extends Item {

    private String author;

    private String isbn;

}
