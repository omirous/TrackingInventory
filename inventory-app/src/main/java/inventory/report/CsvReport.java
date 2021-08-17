package inventory.report;

import java.util.List;
import java.util.stream.Collectors;

import inventory.Item;

public class CsvReport {

	private static final String DELIMITER = ",";

	private List<Item> items;

	public CsvReport(List<Item> items) {
		this.items = items;
	}

	public String produce() {
		if (items.isEmpty())
			return "";

		return itemsToCsv();
	}

	private String itemsToCsv() {
		return items
				.stream()
				.map(item -> itemToCsv(item))
				.collect(Collectors.joining("\n"));
	}

	private String itemToCsv(Item item) {
		return item.getName() +
				DELIMITER +
				item.getSerialNumber() +
				DELIMITER +
				item.getValue();
	}

}
