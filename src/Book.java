import java.io.Serializable;

/**
 * @author splicedr
 * Class Book
 * Represents a book with title, author, and publication year
 * Implements Serializable to allow object serialization
 */
public class Book implements Serializable {
    private String title;
    private String author;
    private int publicationYear;

    /**
     * Constructor
     * @param title The title of the book
     * @param author The author
     * @param publicationYear The publication year
     */
    public Book(String title, String author, int publicationYear) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
    }

    /**
     * Getters and Setters
     * @return The title, author, and publication year
     */
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public int getPublicationYear() { return publicationYear; }
    public void setPublicationYear(int publicationYear) { this.publicationYear = publicationYear; }

    /**
     * toString
     * @return A string representation of the book
     */
    @Override
    public String toString() {
        return title + " by " + author + " (" + publicationYear + ")";
    }
}