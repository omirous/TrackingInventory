package inventory;

import java.math.BigDecimal;
import java.util.StringTokenizer;

public class ItemParser {

	private static final char DELIMETER = ';';
	private StringTokenizer tokenizer;
	private String errorMessage;

	public Item parseLine(String line) {
		try {
			errorMessage = "";
			return doParseLine(line);
		} catch (Exception e) {
			errorMessage = e.getMessage();
			return null;
		}
	}

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
		tokenizer = new StringTokenizer(line, String.valueOf(DELIMETER));
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
