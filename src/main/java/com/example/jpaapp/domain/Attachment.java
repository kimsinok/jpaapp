package com.example.jpaapp.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

// Value Object

@Embeddable
@Getter
@Setter
public class Attachment {

    private String filename;
    private String filepath;
    private int size;

}