import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * @author splicedr
 * Class LibraryMenu
 * This class is responsible for displaying the menu and handling user input
 * for the library management system.
 * It interacts with the Library class to perform various operations
 * such as viewing all books, sorting books, and searching for books.
 * It also handles user interaction logging and loading/saving the library data.
 */
public class LibraryMenu {
    private Library library;
    private UserInteractionLogger logger = new UserInteractionLogger();
    private LibrarySerializer serializer = new LibrarySerializer();  // Added serializer

    /**
     * Constructor for LibraryMenu
     * Initializes the LibraryMenu with the provided Library object
     * and loads the library data from the serializer or default file.
     * @param library The Library object to be managed by this menu
     */
    public LibraryMenu(Library library) {
        logger.log("Program start.");
        this.library = library;
        // Load the library data when the program starts
        List<Book> books = serializer.loadLibrary("src/resources/data/library.ser");
        if (books != null) {
            library.setBooks(books);
            System.out.println("Library loaded successfully from /resources/data/library.ser");
            logger.log("Library loaded successfully from /resources/data/library.ser");
        } else {
            library.loadBooks("src/resources/data/books.txt");
            System.out.println("No saved library found. Loaded default books from /resources/data/books.txt");
            logger.log("No saved library found. Loaded default books from /resources/data/books.txt");
        }
    }

    /**
     * Displays the menu and handles user input
     * Calls the corresponding method based on the user's choice
     * Saves the library data when the program exits
     */
    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        logger.log("Library menu displayed.");
        while (!exit) {
            System.out.println("\n1 - View all books | 2 - Sort books by title" +
                    " | 3 - Sort books by author | 4 - Sort books by publication year" +
                    " | 5 - Search for books by keyword | 6 - Exit the program");
            String option = scanner.next();
            switch (option) {
                case "1":
                    library.viewAllBooks();
                    logger.log("User viewed all books in library.");
                    break;
                case "2":
                    SortUtil.bubbleSort(library.getBooks(), Comparator.comparing(Book::getTitle));
                    System.out.println("Complete! Books have been sorted by title.");
                    logger.log("User sorted books by title.");
                    break;
                case "3":
                    SortUtil.insertionSort(library.getBooks(), Comparator.comparing(Book::getAuthor));
                    System.out.println("Complete! Books have been sorted by author.");
                    logger.log("User sorted books by author.");
                    break;
                case "4":
                    SortUtil.quickSort(library.getBooks(), Comparator.comparingInt(Book::getPublicationYear),
                            0, library.getBooks().size()-1);
                    System.out.println("Complete! Books have been sorted by publication year.");
                    logger.log("User sorted books by publication year.");
                    break;
                case "5":
                    System.out.println("Enter a keyword to search (title, author, or year): ");
                    String keyword = scanner.next();
                    List<Book> foundBooks = library.searchBooksByKeyword(keyword);
                    logger.log("User searched for books by keyword: " + keyword);
                    if (!foundBooks.isEmpty()) {
                        System.out.println("Books found: ");
                        int counter = 1;
                        for (Book book : foundBooks)
                            System.out.println(counter++ + " : " + book);
                        logger.log("Books found: " + foundBooks.toString());
                    }
                    else {
                        System.out.println("No books found with the given keyword.");
                        logger.log("No books found with the given keyword.");
                    }
                    break;
                case "6":
                    exit = true;
                    logger.log("User exited program.");
                    // Save the library data when the program exits
                    serializer.saveLibrary(library.getBooks(), "src/resources/data/library.ser");
                    System.out.println("Library saved successfully to src/resources/data/library.ser");
                    logger.log("Library saved successfully to src/resources/data/library.ser");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    logger.log("User entered invalid option: " + option);
                    break;
            }
        }
    }
}