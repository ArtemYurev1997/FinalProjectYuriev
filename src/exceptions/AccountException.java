package exceptions;

public class AccountException extends RuntimeException {
    public AccountException(String message) {
        super(message);
        System.err.println(message);

    }
}
