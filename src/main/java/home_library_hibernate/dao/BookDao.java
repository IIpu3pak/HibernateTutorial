package home_library_hibernate.dao;

import home_library_hibernate.domain.Book;

import java.sql.SQLException;
import java.util.List;


public interface BookDao {

    Integer addBook(Book book) throws SQLException;

    Integer deleteBook(Book book) throws SQLException;

    Book getBook(int id) throws SQLException;

    List<Book> getBooks() throws SQLException;

}
