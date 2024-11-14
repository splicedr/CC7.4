import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author splicedr
 * Class Library
 * This class represents a library with a list of books.
 * It provides methods to load books from a file, view all books, and search for books by keyword.
 */
public class Library {
    private List<Book> books = new ArrayList<>();

    /**
     * Loads books from a file into the library.
     * Each line in the file represents a book with the format: title,author,year
     * @param fileName The path to the file containing the books
     */
    public void loadBooks(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Line example: The Great Gatsby,F. Scott Fitzgerald,1925
                // Split by comma, and add new book with each parameter
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String title = parts[0].trim();
                    String author = parts[1].trim();
                    int year = Integer.parseInt(parts[2].trim());
                    books.add(new Book(title, author, year));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays all books in the library.
     * The books are numbered for easier selection.
     */
    public void viewAllBooks() {
        int counter = 1;
        for (Book book : books) {
            System.out.println(counter++ + " : " + book);
        }
    }

    // What if we have multiple books with the same keyword?
    /*
    public Book searchBookByKeyword(String keyword) {
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                    book.getAuthor().toLowerCase().contains(keyword.toLowerCase()) ||
                        Integer.toString(book.getPublicationYear()).contains(keyword)) {
                return book;
            }
        }
        return null;
    }
    */

    /**
     * Searches for books by keyword in the library.
     * The keyword can match the title, author, or publication year.
     * The search is case-insensitive.
     * @param keyword The keyword to search for
     * @return A list of books that match the keyword
     */
    public List<Book> searchBooksByKeyword(String keyword) {
        List<Book> foundBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                    book.getAuthor().toLowerCase().contains(keyword.toLowerCase()) ||
                    Integer.toString(book.getPublicationYear()).contains(keyword)) {
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }

    /**
     * Getters and Setters
     * @return The list of books in the library
     */
    public List<Book> getBooks() { return books; }
    public void setBooks(List<Book> books) { this.books = books; }
}