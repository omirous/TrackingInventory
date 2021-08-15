package inventory;

public class ItemParser {

	private static final String DELIMETER = ";";

	public void parseLine(String line) {
		if (line == null)
			throw new InvalidInput("Expected a line with item data but got null instead.");

		if (line.isEmpty())
			throw new InvalidInput("Expected a line with item data but got an empty line instead.");

		if (!line.contains(DELIMETER))
			throw new InvalidInput("Expected a line with with 2 delimeters but got none instead.");
	}


}
