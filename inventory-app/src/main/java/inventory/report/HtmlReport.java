package inventory.report;

import java.util.List;
import java.util.stream.Collectors;

import inventory.Item;

/**
 * Generate an html report for a list of items.
 */
public class HtmlReport {

	private List<Item> items;

	/**
	 * Create a report for a list of items.
	 *
	 * @param items Items to be included in the report
	 */
	public HtmlReport(List<Item> items) {
		this.items = items;
	}

	public String produce() {
		return "<html>" + "\n"
				+ body()
				+ "</html>";
	}

	private String headers() {
		return "<th>Name</th>" + "\n"
				+ "<th>Serial Number</th>" + "\n"
				+ "<th>Value</th>";
	}

	private String body() {
		if (items.isEmpty())
			return "<p>There are no items to report.</p>" + "\n";

		return "<table>" + "\n"
			+ headers()  + "\n"
			+ rows() + "\n"
			+ "</table>" + "\n";
	}

	private String rows() {
		return items
				.stream()
				.map(i -> row(i))
				.collect(Collectors.toList())
				.stream()
				.collect(Collectors.joining("\n"));
	}

	private String row(Item item) {
		return String.format(
					"<tr><td>%s</td><td>%s</td><td>%s</td></tr>",
					item.getName(),
					item.getSerialNumber(),
					item.getValue()
				);
	}

}
