package home_library_hibernate_spring.view;

import home_library_hibernate_spring.dao.AuthorDao;
import home_library_hibernate_spring.dao.BookDao;
import home_library_hibernate_spring.domain.Author;
import home_library_hibernate_spring.domain.Book;
import home_library_hibernate_spring.domain.Genre;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;


public class Main {

//    @Autowired
//    private static AuthorDao authorDao;
//
//    @Autowired
//    private static BookDao bookDao;
//
//    @Autowired
//    private static GenreDao genreDao;

    public static void main(String[] args) {

        Locale.setDefault(Locale.ENGLISH);

        try {
            writeInfoIntoDataBase(Book.getJackLondonBooks());
            writeInfoIntoDataBase(Book.getAyzekAzimovBooks());
        } catch (Throwable e) {
            e.printStackTrace();
//        } finally {
//            if (sessionFactory != null & !sessionFactory.isClosed()) {
//                sessionFactory.close();
//            }
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
        }


        ApplicationContext context = null;
        try {
            context = new ClassPathXmlApplicationContext("home_library_hibernate_spring/Context.xml");
        } catch (BeansException e) {
            e.printStackTrace();
        }
        AuthorDao authorDao = context.getBean("authorDaoImpl", AuthorDao.class);
        BookDao bookDao = context.getBean("bookDaoImpl", BookDao.class);

        try {
            Integer author_id = authorDao.addAuthor(newAuthor);
            System.out.println("Write author in database id - " + author_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
