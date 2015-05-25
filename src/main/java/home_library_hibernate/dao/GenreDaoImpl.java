package home_library_hibernate.dao;

import home_library_hibernate.domain.Genre;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Администратор on 04.04.2015.
 */
public class GenreDaoImpl implements GenreDao {

    private SessionFactory sessionFactory;

    public GenreDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Integer addGenre(Genre genre) throws SQLException {

        Integer id = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            id = (Integer) session.save(genre);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return id;

    }

    @Override
    public Integer deleteGenre(Genre genre) throws SQLException {

        Integer id = genre.getId();
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.delete(genre);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return id;
    }

    @Override
    public Genre getGenre(int id) throws SQLException {

        Genre foundGenre = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            foundGenre = (Genre)session.get(Genre.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return foundGenre;
    }

    @Override
    public List<Genre> getGenres() throws SQLException {

        List<Genre> genresList = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            genresList = session.createCriteria(Genre.class).list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return genresList;
    }
}
