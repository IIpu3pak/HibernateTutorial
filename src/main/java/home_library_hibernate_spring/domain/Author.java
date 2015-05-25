package home_library_hibernate_spring.domain;

import javax.persistence.*;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table (name = "Authors")
public class Author {

    @Id
    @Column (name = "author_id")
    //@GeneratedValue (strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "sequence", sequenceName = "author_id", allocationSize = 1)//, initialValue = 2)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    private Integer id;

    @Column (name = "author_firstName")
    private String firstName;

    @Column (name = "author_middleName")
    private String middleName;

    @Column (name = "author_lastName")
    private String lastName;

    @Column (name = "author_knownAs")
    private String knownAs;

    @Column (name = "author_dateOfBirth")
    //@Temporal()
    private GregorianCalendar dateOfBirth;


//    @ManyToMany
//    @JoinTable(name="author_book",
//            joinColumns = @JoinColumn(name="author_id", referencedColumnName="id"),
//            inverseJoinColumns = @JoinColumn(name="book_id", referencedColumnName="id")
//    )

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author")
    private Set<Book> books;

    public Author() {
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getKnownAs() {
        return knownAs;
    }

    public void setKnownAs(String knownAs) {
        this.knownAs = knownAs;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public GregorianCalendar getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(GregorianCalendar dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void addBook(Book book) {
        if(books == null){
            books = new HashSet<>();
        }
        if(book.getAuthor() == null) {
            book.setAuthor(this);
        }
        books.add(book);
    }

    @Override
    public String toString() {
        return getKnownAs() + "( " + getFirstName() + " " + getMiddleName() + " " + getLastName() + " ) date of birth - " + getDateOfBirth();
    }
}
