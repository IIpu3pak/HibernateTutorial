package home_library_hibernate.view;

import home_library_hibernate.dao.AuthorDao;
import home_library_hibernate.dao.AuthorDaoImpl;
import home_library_hibernate.dao.BookDao;
import home_library_hibernate.dao.GenreDao;
import home_library_hibernate.domain.Author;
import home_library_hibernate.domain.Book;
import home_library_hibernate.domain.Genre;
import home_library_hibernate.util.HibernateUtil;
import org.hibernate.SessionFactory;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Администратор on 04.04.2015.
 */
public class Main {

    private static BookDao bookDao;
    private static AuthorDao authorDao;
    private static GenreDao genreDao;
    private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public static void main(String[] args) {

        /*EntityManagerFactory emf = Persistence.createEntityManagerFactory("book");
        EntityManager em = emf.createEntityManager();
        em.find();*/



        try {
            writeInfoIntoDataBase(Book.getJackLondonBooks());
            writeInfoIntoDataBase(Book.getAyzekAzimovBooks());
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            if (sessionFactory != null & !sessionFactory.isClosed()) {
                sessionFactory.close();
            }
        }

    }

    public static void writeInfoIntoDataBase(List<HashMap<String, Object>> booksList) {

        Author newAuthor = null;
        Genre newGenre = null;
        Genre parentGenre = null;

        int i = 0;
        for (HashMap<String, Object> bookOption : booksList) {

            newAuthor = (Author) bookOption.get("Author");
            newGenre = (Genre) bookOption.get("Genre");
            parentGenre = newGenre.getParentGenre();

            Book newBook = new Book();
            newBook.setTitle((String) bookOption.get("Title"));
            newBook.setDescription((String) bookOption.get("Description"));
            newBook.setGenre(newGenre);
            newBook.setAuthor(newAuthor);
            newAuthor.addBook(newBook);
            newGenre.addBook(newBook);

           /* if(i < 1){
                authorDao = new AuthorDaoImpl(sessionFactory);
                try {
                    Integer author_id = authorDao.addAuthor(newAuthor);
                    System.out.println("Write author in database id - " + author_id);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if(i < 1) {
                genreDao = new GenreDaoImpl(sessionFactory);
                try {
                    Integer genre_id = genreDao.addGenre(newGenre);
                    System.out.println("Write genre in database id - " + genre_id);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            i = 1;*/

            /*bookDao = new BookDaoImpl(sessionFactory);
            try {
                Integer id = bookDao.addBook(newBook);
                System.out.println("Write book in database id - " + id);
            } catch (SQLException e) {
                e.printStackTrace();
            }*/

        }

       /* if(parentGenre != null) {
            genreDao = new GenreDaoImpl(sessionFactory);
            try {
                Integer genre_id = genreDao.addGenre(parentGenre);
                System.out.println("Write genre in database id - " + genre_id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }*/

        authorDao = new AuthorDaoImpl(sessionFactory);
        try {
            Integer author_id = authorDao.addAuthor(newAuthor);
            System.out.println("Write author in database id - " + author_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public BookDao getBookDAO() {
        return bookDao;
    }

    public void setBookDAO(BookDao bookDAO) {
        this.bookDao = bookDAO;
    }


}
