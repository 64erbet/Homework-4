package ua.goit.jdbc.exceprtions;

public class ExitException extends RuntimeException {
    public ExitException(String message) {
        super(message);
    }
}
