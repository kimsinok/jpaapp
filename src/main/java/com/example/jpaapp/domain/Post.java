package com.example.jpaapp.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    private String title;

    private String content;

    @ElementCollection
    @CollectionTable(name = "attachment", joinColumns = @JoinColumn(name = "post_id"))
    private List<Attachment> attachments = new ArrayList<>();

    public void addAttachment(Attachment attachment) {
        this.attachments.add(attachment);
    }

}
