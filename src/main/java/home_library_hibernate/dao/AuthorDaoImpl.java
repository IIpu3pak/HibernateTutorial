package home_library_hibernate.dao;

import home_library_hibernate.domain.Author;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;


@Repository
public class AuthorDaoImpl implements AuthorDao {

    @Autowired
    private SessionFactory sessionFactory;

    public AuthorDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Integer addAuthor(Author author) throws SQLException {

        Integer id = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            id = (Integer) session.save(author);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return id;

    }

    @Override
    public Integer deleteAuthor(Author author) throws SQLException {

        Integer id = author.getId();
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.delete(author);
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
    public Author getAuthor(int id) throws SQLException {

        Author foundAuthor = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            foundAuthor = (Author)session.get(Author.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return foundAuthor;
    }

    @Override
    public List<Author> getAuthors() throws SQLException {

        List<Author> authorsList = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            authorsList = session.createCriteria(Author.class).list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return authorsList;
    }
}
