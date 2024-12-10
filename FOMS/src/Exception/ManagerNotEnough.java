package Exception;

public class ManagerNotEnough extends Exception{
	
	private static final long serialVersionUID = 1L;

    public ManagerNotEnough() {
        super("Manager is not enough.");
    }

    public ManagerNotEnough(String message) {
        super(message);
    }

    public ManagerNotEnough(String message, Throwable cause) {
        super(message, cause);
    }
}
