package Exception;

public class NonexistentOrderID extends Exception {
	
	private static final long serialVersionUID = 1L;

	public NonexistentOrderID() {
        super("Input Error.");
    }

    public NonexistentOrderID(String message) {
        super(message);
    }

    public NonexistentOrderID(String message, Throwable cause) {
        super(message, cause);
    }

}
