package com.example.Springdatajdbc.service;

import com.example.Springdatajdbc.exception.NotFoundException;
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
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Book not found", HttpStatus.NOT_FOUND));
    }

    public List<Book> getAll(){
        return (List<Book>) repository.findAll();
    }

    public Book create(Book book){
        return repository.save(book);
    }

    public Book update(Book book, Long id) {
        Book existingBook = repository.findById(id).orElseThrow(() -> new NotFoundException("Book not found", HttpStatus.NOT_FOUND));
        existingBook.setAuthor(book.getAuthor());
        existingBook.setTitle(existingBook.getTitle());
        existingBook.setPublicationYear(book.getPublicationYear());
        return repository.save(existingBook);
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }
}
