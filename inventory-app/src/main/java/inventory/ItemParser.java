package inventory;

import java.math.BigDecimal;
import java.util.StringTokenizer;

/**
 * The {@link ItemParser} is responsible for parsing a String to an {@link Item}.
 *
 * The String line contains the {@link Item} properties (name, serial number, value)
 * separated by a delimiter, in that order.
 *
 * The {@link ItemParser} will also perform validation on the String line, before
 * parsing it to an {@link Item}.
 *
 * Design decision: This class does not throw exception because it is going to
 * be used by a read eval loop on command line. It uses a very primitive version
 * of the Notification pattern. In case the Item cannot be parsed, the returned
 * {@link Item} is null and a method for getting to error message is provided
 * (getErrorMessage).
 */
public class ItemParser {

	public static final char DELIMITER = ';';
	private StringTokenizer tokenizer;
	private String errorMessage;

	/**
	 * Parse a String line containing the {@link Item} data to an {@link Item} object.
	 *
	 * @param line A String containing the item data separated by delimiters.
	 * @return Parsed {@link Item}
	 */
	public Item parseLine(String line) {
		try {
			errorMessage = "";
			return doParseLine(line);
		} catch (Exception e) {
			errorMessage = e.getMessage();
			return null;
		}
	}

	/**
	 * Get the error message in case there is a problem parsing the {@link Item}.
	 *
	 * @return String containing the error description.
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	private Item doParseLine(String line) {
		notNullLine(line);
		notEmptyLine(line);
		tokenize(line);
		exactly3Tokens();
		return parseLineToItem();
	}

	private void notNullLine(String line) {
		if (line == null)
			throw new InvalidInput("Expected a line with item data but got null instead.");
	}

	private void notEmptyLine(String line) {
		if (line.isEmpty())
			throw new InvalidInput("Expected a line with item data but got an empty line instead.");
	}

	private void tokenize(String line) {
		tokenizer = new StringTokenizer(line, String.valueOf(DELIMITER));
	}

	private void exactly3Tokens() {
		if (tokenizer.countTokens() != 3)
			throw new InvalidInput(message(tokenizer.countTokens()));
	}

	private Item parseLineToItem() {
		String name = tokenizer.nextToken();
		String sn = tokenizer.nextToken();
		String valueString = tokenizer.nextToken();
		return createItem(name, sn, valueString);
	}

	private Item createItem(String name, String sn, String valueString) {
		try {
			return new Item(name, sn, new BigDecimal(valueString));
		} catch (NumberFormatException e) {
			errorMessage = String.format("Expected value (3rd token) to be a number but got %s instead.", valueString);
			return null;
		}
	}

	private String message(long numOfDelimeters) {
		return String.format("Expected a line with 3 tokens but got %d instead.", numOfDelimeters);
	}

	private class InvalidInput extends RuntimeException {

		private static final long serialVersionUID = 1L;

		InvalidInput(String message) {
			super(message);
		}
	}

}
