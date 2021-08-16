package inventory;

import java.util.Scanner;

import inventory.parse.ItemParser;

/**
 * Read eval loop in command line for creating {@link Item} objects.
 *
 * Each input line represents the data for the item to be created.
 * The data should be separated by a delimiter(;).
 * The pattern is "name;serialNumber;value.
 *
 * For example:
 * Playstation 4;PS4;100
 */
public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ItemParser parser = new ItemParser();
		prompt();
		while(scanner.hasNext()) {
			Item item = parser.parseLine(scanner.nextLine());
			checkErrors(parser, item);
			prompt();
		}
	}

	private static void checkErrors(ItemParser parser, Item item) {
		if (item != null)
			System.out.println("Item parsed");
		else
			System.err.println(parser.getErrorMessage());
	}

	private static void prompt() {
		System.out.println(message());
	}

	private static String message() {
		return String.format("Enter the data for an item, separated by delimiters (%s)", ItemParser.DELIMITER);
	}

}
