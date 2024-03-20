package com.example.Springdatajdbc.repository;

import com.example.Springdatajdbc.model.Book;
import com.example.Springdatajdbc.util.BookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookRepository {
    private final JdbcTemplate jdbcTemplate;

    private final String SQL_FIND_BOOK = "select * from books where book_id = ?";
    private final String SQL_DELETE_BOOK = "delete from books where book_id = ?";
    private final String SQL_UPDATE_BOOK = "update books set title = ?, author = ?, publication_year  = ? where book_id = ?";
    private final String SQL_GET_ALL = "select * from books";
    private final String SQL_INSERT_BOOK = "insert into books(title, author, publication_year) values(?,?,?)";

    public Book getById(Long id) {
        return jdbcTemplate.queryForObject(SQL_FIND_BOOK, new Object[]{id}, new BookMapper());
    }

    public List<Book> getAll() {
        return jdbcTemplate.query(SQL_GET_ALL, new BookMapper());
    }

    public Long create(Book book) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL_INSERT_BOOK, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setInt(3, book.getPublicationYear());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    public int update(Book book, Long id) {
        return jdbcTemplate.update(SQL_UPDATE_BOOK, book.getTitle(), book.getAuthor(), book.getPublicationYear());
    }

    public int deleteById(Long id) {
        return jdbcTemplate.update(SQL_DELETE_BOOK, id);
    }
}
