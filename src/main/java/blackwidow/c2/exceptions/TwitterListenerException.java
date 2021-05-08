package blackwidow.c2.exceptions;

public class TwitterListenerException extends BlackWidowException {
    public TwitterListenerException(String message) {
        super(message);
    }

    public TwitterListenerException(String message, Throwable cause) {
        super(message, cause);
    }
}
