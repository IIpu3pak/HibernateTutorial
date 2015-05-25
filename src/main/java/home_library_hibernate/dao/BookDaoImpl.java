package home_library_hibernate.dao;

import home_library_hibernate.domain.Book;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Администратор on 04.04.2015.
 */
@Repository
public class BookDaoImpl implements BookDao {

    @Autowired
    private SessionFactory sessionFactory;

    public BookDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Integer addBook(Book book) throws SQLException {

        Integer id = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            id = (Integer) session.save(book);
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
    public Integer deleteBook(Book book) throws SQLException {

        Integer id = book.getId();
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.delete(book);
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
    public Book getBook(int id) throws SQLException {

        Book foundBook = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            foundBook = (Book)session.get(Book.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return foundBook;
    }

    @Override
    public List<Book> getBooks() throws SQLException {

        List<Book> booksList = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            booksList = session.createCriteria(Book.class).list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return booksList;
    }
}
