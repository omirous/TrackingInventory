package main;

import java.util.Scanner;

import inventory.Item;
import inventory.parse.ItemParser;
import inventory.programs.SaveLineAsItemProgram;
import storage.FileStorage;
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
public class SaveItemsMain {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		SaveLineAsItemProgram program = new SaveLineAsItemProgram(createFileStorage());
		prompt();
		while (scanner.hasNext()) {
			program.saveLineToItem(scanner.nextLine());
			if (program.hasErrors())
				System.err.println(program.getErrorMessage());
			else
				System.out.println("Item saved.");
			prompt();
		}
	}

	private static FileStorage createFileStorage() {
		return new FileStorageNIO(Configuration.createFilePathString());
	}

	private static void prompt() {
		System.out.println(message());
	}

	private static String message() {
		return String.format("Enter the data for an item, separated by delimiters (%s).", ItemParser.DELIMITER);
	}

}
