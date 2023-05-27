package exceptions;

public class ClientException extends RuntimeException {
    public ClientException(String message) {
        super(message);
        System.err.println(message);
    }

}
