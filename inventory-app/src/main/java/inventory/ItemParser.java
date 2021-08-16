package inventory;

import java.math.BigDecimal;
import java.util.StringTokenizer;

public class ItemParser {

	private static final char DELIMETER = ';';
	private StringTokenizer tokenizer;
	private String errorMessage;

	public Item parseLine(String line) {
		errorMessage = "";
		notNullLine(line);
		notEmptyLine(line);
		exactly3Tokens(line);
		return parseLineToItem(line);
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	private void notNullLine(String line) {
		if (line == null)
			throw new InvalidInput("Expected a line with item data but got null instead.");
	}

	private void notEmptyLine(String line) {
		if (line.isEmpty())
			throw new InvalidInput("Expected a line with item data but got an empty line instead.");
	}

	private void exactly3Tokens(String line) {
		tokenizer = new StringTokenizer(line, String.valueOf(DELIMETER));
		if (tokenizer.countTokens() != 3)
			throw new InvalidInput(message(tokenizer.countTokens()));
	}

	private Item parseLineToItem(String line) {
		String name = tokenizer.nextToken();
		String sn = tokenizer.nextToken();
		String valueString = tokenizer.nextToken();
		try {
			return new Item(name, sn, new BigDecimal(valueString));
		} catch (NumberFormatException e) {
			errorMessage = "Expected value (3rd token) to be a number but got notANumber instead.";
			return null;
		}
	}

	private String message(long numOfDelimeters) {
		return String.format("Expected a line with 3 tokens but got %d instead.", numOfDelimeters);
	}

}
