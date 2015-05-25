package home_library_hibernate_spring.dao;

import home_library_hibernate_spring.domain.Book;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Администратор on 04.04.2015.
 */
public interface BookDao {

    Integer addBook(Book book) throws SQLException;

    Integer deleteBook(Book book) throws SQLException;

    Book getBook(int id) throws SQLException;

    List<Book> getBooks() throws SQLException;

}
