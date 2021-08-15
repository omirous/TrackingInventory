package inventory;

public class Item {

	private String name;
	private String serialNumber;

	public Item() {

	}

	/**
	 * Create a new {@link Item} using a name and a serial number.
	 *
	 * @param name         The name of the item.
	 * @param serialNumber The serial number of the item
	 */
	public Item(String name, String serialNumber) {
		this.name = name;
		this.serialNumber = serialNumber;
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

}