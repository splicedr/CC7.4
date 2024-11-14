import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author splicedr
 * Class LibrarySerializer
 * This class is responsible for serializing and deserializing the library data.
 * It provides methods to save the library data to a file and load the library data from a file.
 * The library data is stored as a list of Book objects.
 */
public class LibrarySerializer {

    /**
     * Saves the library data to a file.
     * The library data is stored as a list of Book objects.
     * The method uses ObjectOutputStream to write the list of books to the specified file.
     * @param books The list of books to be saved
     * @param fileName The path of the file to save the library data to
     */
    public void saveLibrary(List<Book> books, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            // Use the writeObject method to serialize the list of books and write it to the specified file
            oos.writeObject(books);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the library data from a file.
     * The library data is stored as a list of Book objects.
     * The method uses ObjectInputStream to read the list of books from the specified file.
     * @param fileName The path of the file to load the library data from
     * @return The list of books loaded from the file, or null if the file is empty or corrupted
     */
    public List<Book> loadLibrary(String fileName) {
        File file = new File(fileName);
        // If file DNE or empty, return null
        if (!file.exists() || file.length() == 0)
            return null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            // Use ObjectInputStream to read the list of books from the specified file.
            // Ensure the deserialized object is cast to List<Book> before returning it.
            return (List<Book>) ois.readObject();
        }
        catch (EOFException e) {
            System.err.println("The file is empty or corrupted: " + fileName);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}