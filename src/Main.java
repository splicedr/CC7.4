/**
 * @author splicedr
 * Main Class
 * Driver to run the program.
 * It creates a Library object and a LibraryMenu object.
 * It then displays the menu to the user.
 */
public class Main {
    public static void main(String[] args) {
        // Create a Library object
        Library library = new Library();
        // Create a LibraryMenu object
        LibraryMenu libraryMenu = new LibraryMenu(library);
        // Display menu to user
        libraryMenu.displayMenu();
    }
}