import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author splicedr
 * Class SortUtil
 * This class contains utility methods for sorting a list of books.
 * It provides methods for bubble sort, insertion sort, and quick sort.
 * The sorting is done based on the provided comparator, typically the title, author or publication year of the book.
 */
public class SortUtil {

    /**
     *  Sorts the given list of books using the bubble sort algorithm.
     * The sorting is done in-place, modifying the original list.
     * @param books The list of books to be sorted
     * @param comparator The comparator to be used for sorting the books
     */
    public static void bubbleSort(List<Book> books, Comparator<Book> comparator) {
        int size = books.size();
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (comparator.compare(books.get(j), books.get(j + 1)) > 0) {
                    Collections.swap(books, j, j + 1);
                }
            }
        }
    }

    /**
     * Sorts the given list of books using the insertion sort algorithm.
     * The sorting is done in-place, modifying the original list.
     * @param books The list of books to be sorted
     * @param comparator The comparator to be used for sorting the books
     */
    public static void insertionSort(List<Book> books, Comparator<Book> comparator) {
        for (int i = 1; i < books.size(); i++) {
            Book key = books.get(i);
            int j = i - 1;
            while (j >= 0 && comparator.compare(books.get(j), key) > 0) {
                books.set(j + 1, books.get(j));
                j = j - 1;
            }
            books.set(j + 1, key);
        }
    }

    /**
     * Sorts the given list of books using the quick sort algorithm.
     * The sorting is done in-place, modifying the original list.
     * @param books The list of books to be sorted
     * @param comparator The comparator to be used for sorting the books
     * @param low The starting index of the subarray to be sorted
     * @param high The ending index of the subarray to be sorted
     */
    public static void quickSort(List<Book> books, Comparator<Book> comparator, int low, int high) {
        if (low < high) {
            int pi = partition(books, comparator, low, high);
            quickSort(books, comparator, low, pi - 1);
            quickSort(books, comparator, pi + 1, high);
        }
    }

    /**
     * Partition method used in quick sort algorithm
     * @param books The list of books to be partitioned
     * @param comparator The comparator to be used for partitioning the books
     * @param low The starting index of the subarray to be partitioned
     * @param high The ending index of the subarray to be partitioned
     * @return The index of the pivot element after partitioning
     */
    private static int partition(List<Book> books, Comparator<Book> comparator, int low, int high) {
        Book pivot = books.get(high);
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (comparator.compare(books.get(j), pivot) <= 0) {
                i++;
                Collections.swap(books, i, j);
            }
        }
        Collections.swap(books, i + 1, high);
        return i + 1;
    }
}