package inventory;

import java.math.BigDecimal;

public class Item {

	private String name;
	private String serialNumber;
	private BigDecimal value;

	/**
	 * Create a new {@link Item} using a name, a serial number and a value.
	 *
	 * @param name         The name of the item.
	 * @param serialNumber The serial number of the item
	 * @param value        The value of the item.
	 */
	public Item(String name, String serialNumber, BigDecimal value) {
		this.name = name;
		this.serialNumber = serialNumber;
		this.value = value;
		validate();
	}

	/**
	 * Retrieve the name of the {@link Item}.
	 *
	 * @return item's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Retrieve the serial number of the {@link Item}.
	 *
	 * @return item's serial number
	 */
	public String getSerialNumber() {
		return serialNumber;
	}

	/**
	 * Retrieve the value of the {@link Item}.
	 *
	 * @return item's value
	 */
	public BigDecimal getValue() {
		return value;
	}

	private void validate() {
		if (name == null)
			throw new InvalidItem(nullNameMessage());

		if (name.isEmpty())
			throw new InvalidItem(emptyNameMessage());

		if (serialNumber == null)
			throw new InvalidItem(serialNumberNullMessage());

		if (serialNumber.isEmpty())
			throw new InvalidItem(emptySerialNumberMessage());

		if (value == null)
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
