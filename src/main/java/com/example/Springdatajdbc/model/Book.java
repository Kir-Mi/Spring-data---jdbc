package com.example.Springdatajdbc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "books")
public class Book {
    @Id
    private Long id;
    @Column("title")
    private String title;
    @Column("author")
    private String author;
    @Column("publication_year")
    private Integer publicationYear;
}
