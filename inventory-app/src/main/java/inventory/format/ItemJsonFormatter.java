package inventory.format;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import inventory.Item;

/**
 * Naive json implementation of {@link ItemFormatter}.
 */
public class ItemJsonFormatter implements ItemFormatter {

	private static final String NAME_PROPERTY = "name";
	private static final String SERIAL_NUMBER_PROPERTY = "sn";
	private static final String VALUE_PROPERTY = "value";

	public String toText(Item item) {
		return String.format(template(), item.getName(), item.getSerialNumber(), item.getValue());
	}

	public Item toItem(String json) {
		Map<String, String> map = jsonMap(json);
		return new Item(
				map.get(NAME_PROPERTY),
				map.get(SERIAL_NUMBER_PROPERTY),
				new BigDecimal(map.get(VALUE_PROPERTY))
			);
	}

	private Map<String, String> jsonMap(String json) {
		Map<String, String> map = new HashMap<String, String>();
		for (String property : properties(json))
			propertyToMap(property, map);

		return map;
	}

	private void propertyToMap(String property, Map<String, String> map) {
		StringTokenizer colonTokenizer = new StringTokenizer(property, ":");
		String keyWithQuotes = colonTokenizer.nextToken().trim();
		String valueWithQuotes = colonTokenizer.nextToken().trim();
		map.put(removeQuotes(keyWithQuotes), removeQuotes(valueWithQuotes));
	}

	private List<String> properties(String json) {
		StringTokenizer commaTokenizer = new StringTokenizer(removeBrackets(json), ",");
		List<String> properties = new ArrayList<String>();
		while (commaTokenizer.hasMoreTokens())
			properties.add(commaTokenizer.nextToken());

		return properties;
	}

	private String removeBrackets(String json) {
		String jsonWithoutBrackets = json.trim().substring(1);
		jsonWithoutBrackets = jsonWithoutBrackets.trim().substring(0, jsonWithoutBrackets.length() - 2).trim();
		return jsonWithoutBrackets;
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
		return String.format(
					"{ \"%s\": \"%%s\", \"%s\": \"%%s\", \"%s\": %%s }",
					NAME_PROPERTY,
					SERIAL_NUMBER_PROPERTY,
					VALUE_PROPERTY
				);
	}
}
