package inventory.format;

import inventory.Item;

public class ItemJsonFormatter {

	public String toJson(Item item) {
		return String.format(template(), item.getName(), item.getSerialNumber(), item.getValue());
	}

	private String template() {
		return "{ \"name\": \"%s\", \"sn\": \"%s\", \"value\": %s }";
	}
}
