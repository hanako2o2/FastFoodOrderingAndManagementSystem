package Exception;

public class WrongCredentials extends Exception {
	
	private static final long serialVersionUID = 1L;

    public WrongCredentials() {
        super("Wrong UserID or Password. Please try again.");
    }

    public WrongCredentials(String message) {
        super(message);
    }

    public WrongCredentials(String message, Throwable cause) {
        super(message, cause);
    }
}
