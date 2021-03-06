package home_library_hibernate.dao;

import home_library_hibernate.domain.Genre;

import java.sql.SQLException;
import java.util.List;


public interface GenreDao {

    Integer addGenre(Genre genre) throws SQLException;

    Integer deleteGenre(Genre genre) throws SQLException;

    Genre getGenre(int id) throws SQLException;

    List<Genre> getGenres() throws SQLException;

}
