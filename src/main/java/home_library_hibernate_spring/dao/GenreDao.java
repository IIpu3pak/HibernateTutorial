package home_library_hibernate_spring.dao;

import home_library_hibernate_spring.domain.Genre;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Администратор on 04.04.2015.
 */
public interface GenreDao {

    Integer addGenre(Genre genre) throws SQLException;

    Integer deleteGenre(Genre genre) throws SQLException;

    Genre getGenre(int id) throws SQLException;

    List<Genre> getGenres() throws SQLException;

}
