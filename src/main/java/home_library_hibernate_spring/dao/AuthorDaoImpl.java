package home_library_hibernate_spring.dao;

import home_library_hibernate_spring.domain.Author;
import org.hibernate.SQLQuery;
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
@Transactional
public class AuthorDaoImpl implements AuthorDao {

    @Qualifier("mySessionFactory")
    @Autowired(required = true)
    private SessionFactory mySessionFactory;

    @Transactional
    public Integer addAuthor(Author author) throws SQLException {

        Session session = mySessionFactory.getCurrentSession();
        return (Integer) session.save(author);
    }

    @Transactional
    public Integer deleteAuthor(Author author) throws SQLException {

        Integer id = author.getId();
        Session session = mySessionFactory.getCurrentSession();
        session.delete(author);
        return id;
    }

    @Transactional
    public Author getAuthor(int id) throws SQLException {

        Session session = mySessionFactory.getCurrentSession();
        return (Author)session.get(Author.class, id);

    }

    @Transactional
    public List<Author> getAuthors() throws SQLException {

        SQLQuery query = mySessionFactory.getCurrentSession().createSQLQuery("SELECT * FROM Authors");
        return query.list();

    }
}
