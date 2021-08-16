package inventory;

import java.math.BigDecimal;
import java.util.StringTokenizer;

public class ItemParser {

	private static final char DELIMETER = ';';

	public Item parseLine(String line) {
		notNullLine(line);
		notEmptyLine(line);
		lineContainsExactly2Delimeters(line);
		return parseLineToItem(line);
	}

	private void notNullLine(String line) {
		if (line == null)
			throw new InvalidInput("Expected a line with item data but got null instead.");
	}

	private void notEmptyLine(String line) {
		if (line.isEmpty())
			throw new InvalidInput("Expected a line with item data but got an empty line instead.");
	}

	private void lineContainsExactly2Delimeters(String line) {
		if (countDelimeters(line) != 2)
			throw new InvalidInput(message(countDelimeters(line)));
	}

	private Item parseLineToItem(String line) {
		StringTokenizer tokenizer = new StringTokenizer(line, String.valueOf(DELIMETER));
		String name = tokenizer.nextToken();
		String sn = tokenizer.nextToken();
		String valueString = tokenizer.nextToken();
		return new Item(name, sn, new BigDecimal(valueString));
	}

	private long countDelimeters(String line) {
		return line.codePoints().filter(c -> c == DELIMETER).count();
	}

	private String message(long numOfDelimeters) {
		return String.format("Expected a line with 2 delimeters but got %d instead.", numOfDelimeters);
	}

}
