package ma.ensa.ordcusthreads;

public class FileProcessingException extends Exception {
    String message;

    public FileProcessingException(String message) {
        super(message);
        this.message = message;
    }
}
