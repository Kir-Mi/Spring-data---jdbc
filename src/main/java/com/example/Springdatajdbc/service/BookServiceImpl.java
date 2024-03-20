package com.example.Springdatajdbc.service;

import com.example.Springdatajdbc.exception.BasicException;
import com.example.Springdatajdbc.model.Book;
import com.example.Springdatajdbc.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository repository;

    public Book getById(Long id) {
        return repository.getById(id);
    }

    public List<Book> getAll() {
        return repository.getAll();
    }

    public Book create(Book book) {
        Long id = repository.create(book);
        book.setId(id);
        return book;
    }

    public Book update(Book book, Long id) {
        int result = repository.update(book, id);
        if (result > 0) {
            return book;
        } else {
            throw new BasicException("book hasn't been updated", HttpStatus.CONFLICT);
        }
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
