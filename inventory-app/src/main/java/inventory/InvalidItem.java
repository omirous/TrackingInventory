package inventory;

public class InvalidItem extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidItem(String message) {
		super(message);
	}

}
