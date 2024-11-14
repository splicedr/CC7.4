import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author splicedr
 * Class UserInteractionLogger
 * This class is responsible for logging user interactions with the library management system.
 * It uses a FileWriter to write log messages to a file named "user_interactions.log".
 * The log messages include the timestamp and the user interaction details.
 */
public class UserInteractionLogger {

    private static final String LOG_FILE = "src/resources/data/user_interactions.log";

    /**
     * Constructor for UserInteractionLogger
     * Initializes the logger by writing a log message indicating the start of the logger.
     * The log message includes the timestamp and a message indicating that the logger has started.
     */
    public UserInteractionLogger() {
        try (FileWriter writer = new FileWriter(LOG_FILE, true)) {
            // Write the log message with a timestamp to the file
            writer.write("\n" + LocalDateTime.now() + " - Logger Started.\n");
            // Flush the writer to ensure the message is written immediately
            writer.flush();
            // Close the writer to release resources
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing to log file: " + e.getMessage());
        }
    }

    /**
     * Logs a user interaction message to the log file.
     * The log message includes the timestamp and the provided message.
     * @param message The message to be logged.
     */
    public void log(String message) {
        // Open the user_interactions.log file in append mode using a FileWriter to
        //  add new log entries without overwriting existing content
        try (FileWriter writer = new FileWriter(LOG_FILE, true)) {
            // Write the log message with a timestamp to the file
            writer.write(LocalDateTime.now() + " - " + message + "\n");
            // Flush the writer to ensure the message is written immediately
            writer.flush();
            // Close the writer to release resources
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing to log file: " + e.getMessage());
        }
    }
}