package inventory;

import java.util.List;

public class HtmlReport {

	private List<Item> items;

	public HtmlReport(List<Item> items) {
		this.items = items;
	}

	public String produce() {
		return "<html>" + "\n"
				+ body()
				+ "</html>";
	}

	private String body() {
		if (items.isEmpty())
			return "<p>There are no items to report.</p>" + "\n";

		return "<table>" + "\n"
			+ headers()
			+ row(items.get(0)) + "\n"
			+ "</table>" + "\n";
	}

	private String row(Item item) {
		return String.format(
					"<tr><td>%s</td><td>%s</td><td>%s</td></tr>",
					item.getName(),
					item.getSerialNumber(),
					item.getValue()
				);
	}

	private String headers() {
		return "<th>Name</th>" + "\n"
				+ "<th>Serial Number</th>" + "\n"
				+ "<th>Value</th>" + "\n";
	}

}
