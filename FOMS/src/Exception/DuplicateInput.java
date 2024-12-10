package Exception;

public class DuplicateInput extends Exception {
	
	private static final long serialVersionUID = 1L;

    public DuplicateInput() {
        super("Duplicate input.");
    }

    public DuplicateInput(String message) {
        super(message);
    }

    public DuplicateInput(String message, Throwable cause) {
        super(message, cause);
    }
}
