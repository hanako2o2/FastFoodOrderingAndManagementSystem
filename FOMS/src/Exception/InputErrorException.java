package Exception;

public class InputErrorException extends Exception{

	private static final long serialVersionUID = 1L;

	public InputErrorException() {
        super("Input Error.");
    }

    public InputErrorException(String message) {
        super(message);
    }

    public InputErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}
