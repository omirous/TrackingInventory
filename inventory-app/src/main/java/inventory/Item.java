package inventory;

import java.math.BigDecimal;

import inventory.validate.ItemValidator;
import storage.FileStorage;

/**
 * An {@link Item} is a simple object that has a name, a serial number and a
 * numeric value.
 *
 * The design choice was not to provide setters for this object.
 */
public class Item {

	private String name;
	private String serialNumber;
	private BigDecimal value;
	private ItemValidator validator;
	private FileStorage storage;

	/**
	 * Create a new {@link Item} using a name, a serial number and a value.
	 *
	 * @param name         The name of the item.
	 * @param serialNumber The serial number of the item.
	 * @param value        The value of the item.
	 */
	public Item(String name, String serialNumber, BigDecimal value) {
		this.name = name;
		this.serialNumber = serialNumber;
		this.value = value;
		validator = new ItemValidator();
		validate();
	}

	/**
	 * In case you need to save an Item, you need to provide a {@link FileStorage}
	 * implementation.
	 *
	 * No default {@link FileStorage} implementation is provided by this class. It
	 * is necessary to set one before calling the save method.
	 *
	 * @param storage The storage that will be used during save method.
	 */
	public void setStorage(FileStorage storage) {
		this.storage = storage;
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

	public void save() {
		storage.appendLine(String.format("%s;%s;%s", getName(), getSerialNumber(), getValue()));
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
		validator.validate(this);
	}

}
