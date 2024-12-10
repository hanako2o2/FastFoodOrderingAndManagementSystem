package Exception;

public class EmptyCartException extends Exception{

    private static final long serialVersionUID = 1L;

    public EmptyCartException() {
        super("The cart is empty.");
    }

    public EmptyCartException(String message) {
        super(message);
    }

    public EmptyCartException(String message, Throwable cause) {
        super(message, cause);
    }

}
