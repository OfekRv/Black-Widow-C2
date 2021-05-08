package blackwidow.c2.exceptions;

public class TwitterServiceException extends ServiceException {
    public TwitterServiceException(String message) {
        super(message);
    }

    public TwitterServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
