package inventory;

public class ItemParser {

	public void parseLine(String line) {
		if (line == null)
			throw new InvalidInput("Expected a line with item data but got null instead.");

		if (line.isEmpty())
			throw new InvalidInput("Expected a line with item data but got an empty line instead.");
	}
}
