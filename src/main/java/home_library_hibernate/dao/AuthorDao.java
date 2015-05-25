package home_library_hibernate.dao;

import home_library_hibernate.domain.Author;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Администратор on 04.04.2015.
 */
public interface AuthorDao {

    Integer addAuthor(Author author) throws SQLException;

    Integer deleteAuthor(Author author) throws SQLException;

    Author getAuthor(int id) throws SQLException;

    List<Author> getAuthors() throws SQLException;

}
