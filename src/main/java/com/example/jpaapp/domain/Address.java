package com.example.jpaapp.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

// Value Object : 불변 객체
@Getter
@RequiredArgsConstructor
@Embeddable
public class Address {

    private final String city;

    private final String street;

    private final String zipcode;

}
