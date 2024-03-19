package com.example.Springdatajdbc.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Table("book")
@Data
public class Book {
    @Id
    @Column("id")
    private UUID id;

    @Column("title")
    private String title;

    @Column("author")
    private String author;

    @Column("publication_year")
    private Integer publicationYear;
}
