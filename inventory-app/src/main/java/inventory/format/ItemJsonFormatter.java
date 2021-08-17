package inventory.format;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import inventory.Item;

public class ItemJsonFormatter {

	public String toJson(Item item) {
		return String.format(template(), item.getName(), item.getSerialNumber(), item.getValue());
	}

	public Item toItem(String json) {
		Map<String, String> map = new HashMap<String, String>();

		String jsonWithoutBrackets = json.trim().substring(1);
		jsonWithoutBrackets = jsonWithoutBrackets.trim().substring(0, jsonWithoutBrackets.length() - 2).trim();

		StringTokenizer commaTokenizer = new StringTokenizer(jsonWithoutBrackets, ",");

		List<String> properties = new ArrayList<String>();
		while (commaTokenizer.hasMoreTokens())
			properties.add(commaTokenizer.nextToken());

		for (String property : properties) {
			StringTokenizer colonTokenizer = new StringTokenizer(property, ":");
			String keyWithQuotes = colonTokenizer.nextToken().trim();
			String valueWithQuotes = colonTokenizer.nextToken().trim();
			String key = removeQuotes(keyWithQuotes);
			String value = removeQuotes(valueWithQuotes);
			map.put(key, value);
		}

		return new Item(map.get("name"), map.get("sn"), new BigDecimal(map.get("value")));
	}

	private String removeQuotes(String valueWithQuotes) {
		String value = valueWithQuotes;
		if (valueWithQuotes.startsWith("\""))
			value = valueWithQuotes.substring(1);
		if (valueWithQuotes.endsWith("\""))
			value = value.substring(0, value.length() - 1);
		return value;
	}

	private String template() {
		return "{ \"name\": \"%s\", \"sn\": \"%s\", \"value\": %s }";
	}
}
