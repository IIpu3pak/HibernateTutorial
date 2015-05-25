package home_library_hibernate_spring.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Genres")
public class Genre {

    @Id
    @Column (name = "genre_id")
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "sequence", sequenceName = "genre_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    private Integer id;

    @Column (name = "genre_name")
    private String name;

    @Column (name = "genre_description")
    private String description;

    //@Column (name = "genre_parent")
    @OneToOne(cascade = CascadeType.ALL)
    private Genre parentGenre;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "genre")
    private Set<Book> books;

    public Genre() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Genre getParentGenre() {
        return parentGenre;
    }

    public void setParentGenre(Genre parentGenre) {
        this.parentGenre = parentGenre;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public void addBook(Book book) {
        if(books == null){
            books = new HashSet<>();
        }
        if(book.getGenre() == null) {
            book.setGenre(this);
        }
        books.add(book);
    }

    @Override
    public String toString() {
        return "Genre{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
               // ", parentGenre=" + parentGenre +
                '}';
    }
}
