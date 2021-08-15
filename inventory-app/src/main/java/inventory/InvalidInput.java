package inventory;

public class InvalidInput extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidInput(String message) {
		super(message);
	}
}
