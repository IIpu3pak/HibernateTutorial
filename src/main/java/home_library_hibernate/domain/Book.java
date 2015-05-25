package home_library_hibernate.domain;

import javax.persistence.*;
import java.util.*;


@Entity
@Table(name = "Books")
public class Book {

    @Id
    @Column(name = "book_id")
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "sequence", sequenceName = "book_id", allocationSize = 1) //, initialValue = 3)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    private int id;

    @Column(name = "book_title")
    private String title;

    @Column(name = "book_description")
    private String description;

    @ManyToOne
    //@Column (name = "book_author")
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "genre_id")
    //@Column (name = "book_genre")
    private Genre genre;


    public Book() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public static List<HashMap<String, Object>> getJackLondonBooks() {

        Author newAuthor = new Author();
        newAuthor.setDateOfBirth(new GregorianCalendar(1876, 01, 12));
        newAuthor.setFirstName("John");
        newAuthor.setMiddleName("Griffith");
        newAuthor.setLastName("Chaney");
        newAuthor.setKnownAs("Jack London");

        Genre newGenre = new Genre();
        newGenre.setName("Classic");
        newGenre.setDescription("Classic");

        List<HashMap<String, Object>> newBooksList = new ArrayList<>();

        HashMap<String, Object> bookOptions = new HashMap<>();

        bookOptions.clear();
        bookOptions.put("Title", "The Sea-Wolf");
        bookOptions.put("Description", "Book about sea");
        bookOptions.put("Author", newAuthor);
        bookOptions.put("Genre", newGenre);
        newBooksList.add((HashMap) bookOptions.clone());

        bookOptions.clear();
        bookOptions.put("Title", "The Call of the Wild");
        bookOptions.put("Description", "Book about dogs");
        bookOptions.put("Author", newAuthor);
        bookOptions.put("Genre", newGenre);
        newBooksList.add((HashMap) bookOptions.clone());

        bookOptions.clear();
        bookOptions.put("Title", "Martin Eden");
        bookOptions.put("Description", "Book about man");
        bookOptions.put("Author", newAuthor);
        bookOptions.put("Genre", newGenre);
        newBooksList.add((HashMap) bookOptions.clone());

        return newBooksList;
    }

    public static List<HashMap<String, Object>> getAyzekAzimovBooks() {

        List<HashMap<String, Object>> newBooksList = new ArrayList<>();

        HashMap<String, Object> bookOptions = new HashMap<>();

        Author newAuthor = new Author();
        newAuthor.setDateOfBirth(new GregorianCalendar(1919, 10, 04));
        newAuthor.setFirstName("Isaak");
        newAuthor.setMiddleName("Azimov");
        newAuthor.setLastName("Yudovich");
        newAuthor.setKnownAs("Ayzek Azimov");

        Genre genreFiction = new Genre();
        genreFiction.setName("Fiction");
        genreFiction.setDescription("Fiction");

        Genre newGenreSF = new Genre();
        newGenreSF.setName("Science fiction");
        newGenreSF.setDescription("Science fiction");
        newGenreSF.setParentGenre(genreFiction);

        Genre newGenreSpF = new Genre();
        newGenreSpF.setName("Space fiction");
        newGenreSpF.setDescription("Space fiction");
        newGenreSpF.setParentGenre(genreFiction);

        bookOptions.clear();
        bookOptions.put("Title", "I, robot");
        bookOptions.put("Description", "Book about robots");
        bookOptions.put("Author", newAuthor);
        bookOptions.put("Genre", newGenreSF);
        //newBooksList.add(bookOptions);
        newBooksList.add((HashMap) bookOptions.clone());

        bookOptions.clear();
        bookOptions.put("Title", "Foundation");
        bookOptions.put("Description", "Book about fall of the space empire");
        bookOptions.put("Author", newAuthor);
        bookOptions.put("Genre", newGenreSpF);
        //newBooksList.add(bookOptions);
        newBooksList.add((HashMap) bookOptions.clone());

        bookOptions.clear();
        bookOptions.put("Title", "The Gods Themselves");
        bookOptions.put("Description", "Book about rationalism without morality leads to evil");
        bookOptions.put("Author", newAuthor);
        bookOptions.put("Genre", newGenreSF);
        //newBooksList.add(bookOptions);
        newBooksList.add((HashMap) bookOptions.clone());

        return newBooksList;
    }

    @Override
    public String toString() {
        return getTitle() + " author - " + getAuthor() + " genre - " + getGenre();
    }

    public static void main(String[] args) {
        List<HashMap<String, Object>> hm = getAyzekAzimovBooks();

        for (HashMap<String, Object> currentHM : hm) {
            System.out.println(currentHM.toString());
        }
    }
}
