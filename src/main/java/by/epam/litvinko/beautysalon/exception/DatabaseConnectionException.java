package by.epam.litvinko.beautysalon.exception;

/**
 * The type Database connection exception.
 */
public class DatabaseConnectionException extends Exception{
    /**
     * Instantiates a new Database connection exception.
     */
    public DatabaseConnectionException() {
    }

    /**
     * Instantiates a new Database connection exception.
     *
     * @param message the message
     */
    public DatabaseConnectionException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Database connection exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public DatabaseConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Database connection exception.
     *
     * @param cause the cause
     */
    public DatabaseConnectionException(Throwable cause) {
        super(cause);
    }
}
