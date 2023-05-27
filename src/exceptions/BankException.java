package exceptions;

public class BankException extends RuntimeException {

    public BankException(String message) {
        super(message);
        System.err.println(message);

    }
}
