package inventory;

public class Item {

	private String name;
	private String serialNumber;

	public Item() {

	}

	public Item(String name, String sn) {
		this.name = name;
		this.serialNumber = sn;
	}

	public String getName() {
		return name;
	}

	public String getSerialNumber() {
		return serialNumber;
	}


}
