package exception;

public class InvalidInputException extends IllegalArgumentException {
    public InvalidInputException() {
        super("Invalid input!");
    }

    public InvalidInputException(String message) {
        super(message);
    }
}