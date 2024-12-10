package Exception;

public class QuotaExceeded extends Exception{
	
	private static final long serialVersionUID = 1L;

    public QuotaExceeded() {
        super("Quota Exceeded.");
    }

    public QuotaExceeded(String message) {
        super(message);
    }

    public QuotaExceeded(String message, Throwable cause) {
        super(message, cause);
    }
}
