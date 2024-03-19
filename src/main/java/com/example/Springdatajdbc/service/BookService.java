package com.example.Springdatajdbc.service;

import com.example.Springdatajdbc.model.Book;

import java.util.List;

public interface BookService {
    Book getById(Long id);
    List<Book> getAll();
    Book create(Book book);
    Book update(Book book, Long id);
    void deleteById(Long id);
}
