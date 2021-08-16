package inventory.validate;

import inventory.Item;

/**
 * The item validate contains the rules for creating a valid {@link Item}.
 *
 *  Those are:
 *
 *  <ul>
 *  <li> The name cannot be null.</li>
 *  <li> The name cannot be an empty string. </li>
 *  <li> The serial number cannot be null.</li>
 *  <li> The serial number cannot be an empty string. </li>
 *  <li> The value cannot be null. </li>
 *  </ul>
 */
public class ItemValidator {

	private Item item;

	/**
	 * Check if an {@link Item} is valid.
	 * A runtime exception with an explanation message will be thrown in case
	 * the item is not valid.
	 *
	 * @param item The item to be checked.
	 * @throws InvalidItem if the item is not valid
	 */
	public void validate(Item item) {
		this.item = item;
		nameNotNull();
		nameNotEmpty();
		serialNumberNotNull();
		serialNumberNotEmpty();
		valueNotNull();
	}

	private void nameNotNull() {
		if (item.getName() == null)
			throw new InvalidItem(nullNameMessage());
	}

	private void nameNotEmpty() {
		if (item.getName().isEmpty())
			throw new InvalidItem(emptyNameMessage());
	}

	private void serialNumberNotNull() {
		if (item.getSerialNumber() == null)
			throw new InvalidItem(serialNumberNullMessage());
	}

	private void serialNumberNotEmpty() {
		if (item.getSerialNumber().isEmpty())
			throw new InvalidItem(emptySerialNumberMessage());
	}

	private void valueNotNull() {
		if (item.getValue() == null)
			throw new InvalidItem(nullValueMessage());
	}

	private String nullNameMessage() {
		return "Expected an item with a name but got a null name instead.";
	}

	private String emptyNameMessage() {
		return "Expected an item with a name but got an empty name instead.";
	}

	private String serialNumberNullMessage() {
		return "Expected an item with a serial number but got a null serial number instead.";
	}

	private String emptySerialNumberMessage() {
		return "Expected an item with an empty serial number but got an empty serial number instead.";
	}

	private String nullValueMessage() {
		return "Expected an item with a value but got null value instead.";
	}
}
