package Exception;

public class NonExistent extends Exception{
	
	private static final long serialVersionUID = 1L;

    public NonExistent() {
        System.out.println("The selection does not exist.");
    }

    public NonExistent(String message) {
        super(message);
    }

    public NonExistent(String message, Throwable cause) {
        super(message, cause);
    }

}
