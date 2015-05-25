package home_library_hibernate_spring.dao;

import home_library_hibernate_spring.domain.Genre;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Администратор on 04.04.2015.
 */
@Repository
public class GenreDaoImpl implements GenreDao {

    @Qualifier("mySessionFactory")
    @Autowired(required = true)
    private SessionFactory mySessionFactory;

    @Transactional
    public Integer addGenre(Genre genre) throws SQLException {

        Session session = mySessionFactory.getCurrentSession();
        Integer id = null;
        id = (Integer) session.save(genre);

        return id;
    }

    @Transactional
    public Integer deleteGenre(Genre genre) throws SQLException {

        Session session = mySessionFactory.getCurrentSession();
        Integer id = genre.getId();
        session.delete(genre);

        return id;
    }

    @Transactional
    public Genre getGenre(int id) throws SQLException {

        Session session = mySessionFactory.getCurrentSession();
        Genre foundGenre = null;
        foundGenre = (Genre)session.get(Genre.class, id);

        return foundGenre;
    }

    @Transactional
    public List<Genre> getGenres() throws SQLException {

        Session session = mySessionFactory.getCurrentSession();
        List<Genre> genresList = null;
        genresList = session.createCriteria(Genre.class).list();

        return genresList;
    }
}
