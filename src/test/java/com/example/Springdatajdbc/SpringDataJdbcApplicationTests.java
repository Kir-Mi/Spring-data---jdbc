package com.example.Springdatajdbc;

import com.example.Springdatajdbc.controller.BookController;
import com.example.Springdatajdbc.model.Book;
import com.example.Springdatajdbc.service.BookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class SpringDataJdbcApplicationTests {

	@Test
	void contextLoads() {
	}

	@InjectMocks
	private BookController bookController;

	@Mock
	private BookService bookService;

	@Test
	public void testGetAllBooks() {
		List<Book> books = new ArrayList<>();
		books.add(new Book(1L, "Book1", "Author1", 2000));
		books.add(new Book(2L, "Book2", "Author2", 2001));
		Mockito.when(bookService.getAll()).thenReturn(books);
		Iterable<Book> result = bookController.getAll();
		Assertions.assertEquals(2, ((List<Book>) result).size());
	}

	@Test
	public void testGetBookById() {
		Long id = 1L;
		Book book = new Book(id, "Book1", "Author1", 2000);
		Mockito.when(bookService.getById(id)).thenReturn(book);
		Book result = bookController.getById(id);
		Assertions.assertEquals(id, result.getId());
	}

	@Test
	public void testSaveBook() {
		Book book = new Book(1L, "Book1", "Author1", 2000);
		Mockito.when(bookService.create(Mockito.any(Book.class))).thenReturn(book);
		Book result = bookController.create(book);
		Assertions.assertEquals(book, result);
	}

	@Test
	public void testDeleteBook() {
		Long id = 1L;
		bookController.deleteById(id);
		Mockito.verify(bookService, Mockito.times(1)).deleteById(id);
	}

}
