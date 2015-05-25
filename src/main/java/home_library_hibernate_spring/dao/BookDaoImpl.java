package home_library_hibernate_spring.dao;

import home_library_hibernate_spring.domain.Book;
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
public class BookDaoImpl implements BookDao {

    @Qualifier("mySessionFactory")
    @Autowired(required = true)
    private SessionFactory mySessionFactory;
    
    @Transactional
    public Integer addBook(Book book) throws SQLException {

        Session session = mySessionFactory.getCurrentSession();
        Integer id = (Integer) session.save(book);

        return id;
    }

    @Transactional
    public Integer deleteBook(Book book) throws SQLException {

        Session session = mySessionFactory.getCurrentSession();
        Integer id = book.getId();
        session.delete(book);

        return id;
    }

    @Transactional
    public Book getBook(int id) throws SQLException {

        Session session = mySessionFactory.getCurrentSession();
        Book foundBook = null;
        foundBook = (Book)session.get(Book.class, id);

        return foundBook;
    }

    @Transactional
    public List<Book> getBooks() throws SQLException {

        List<Book> booksList = null;
        Session session = mySessionFactory.getCurrentSession();
        booksList = session.createCriteria(Book.class).list();

        return booksList;
    }
}
