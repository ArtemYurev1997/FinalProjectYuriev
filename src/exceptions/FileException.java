package exceptions;

public class FileException extends RuntimeException {

    public FileException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "Файловое исключение";
    }

}
