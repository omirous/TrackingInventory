package inventory;

public class ItemParser {

	private static final char DELIMETER = ';';

	public void parseLine(String line) {
		if (line == null)
			throw new InvalidInput("Expected a line with item data but got null instead.");

		if (line.isEmpty())
			throw new InvalidInput("Expected a line with item data but got an empty line instead.");

		long numOfDelimeters = line.codePoints().filter(c -> c == DELIMETER).count();

		if (numOfDelimeters != 2)
			throw new InvalidInput(message(numOfDelimeters));
	}

	private String message(long numOfDelimeters) {
		return String.format("Expected a line with 2 delimeters but got %d instead.", numOfDelimeters);
	}


}
