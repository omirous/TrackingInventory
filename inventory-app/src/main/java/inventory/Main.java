package inventory;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import inventory.parse.ItemParser;
import storage.FileStorageNIO;

/**
 * Read eval loop in command line for creating {@link Item} objects.
 *
 * Each input line represents the data for the item to be created. The data
 * should be separated by a delimiter(;). The pattern is
 * "name;serialNumber;value.
 *
 * For example: Playstation 4;PS4;100
 */
public class Main {

	static final String FILE_PATH = "src/main/resources/storage/items.txt";

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ItemParser parser = new ItemParser();
		FileStorageNIO storage = createFileStorage();
		prompt();
		while (scanner.hasNext()) {
			Item item = parser.parseLine(scanner.nextLine());
			checkErrors(parser, item);
			item.setStorage(storage);
			item.save();
			prompt();
		}
	}

	private static FileStorageNIO createFileStorage() {
		return new FileStorageNIO(createFilePath().toAbsolutePath().toString());
	}

	private static Path createFilePath() {
		Path projectPath = Paths.get(".");
		String projectPathString = projectPath.toAbsolutePath().toString();
		return Paths.get(projectPathString, FILE_PATH);
	}

	private static void checkErrors(ItemParser parser, Item item) {
		if (item != null)
			System.out.println("Item saved.");
		else
			System.err.println(parser.getErrorMessage());
	}

	private static void prompt() {
		System.out.println(message());
	}

	private static String message() {
		return String.format("Enter the data for an item, separated by delimiters (%s).", ItemParser.DELIMITER);
	}

}
