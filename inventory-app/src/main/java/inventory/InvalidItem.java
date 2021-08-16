package inventory;

/**
 * This Runtime exception is used for indicating invalid Items.
 *
 * The exceptions contains a message explaining why the item is invalid.
 */
public class InvalidItem extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidItem(String message) {
		super(message);
	}

}
