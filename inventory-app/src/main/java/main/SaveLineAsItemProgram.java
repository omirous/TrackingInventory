package main;

import inventory.Item;
import inventory.parse.ItemParser;
import storage.FileStorage;
/**
 * Save a String coming possibly from the command line to an item.
 */
public class SaveLineAsItemProgram {

	private FileStorage fileStorage;
	private ItemParser parser;
	private boolean hasErrors;

	/**
	 * Create a program that saves a String with delimiters that represents an
	 * item to a file with json format.
	 *
	 * @param fileStorage {@link FileStorage} that represents the file containing
	 * the items.
	 */
	public SaveLineAsItemProgram(FileStorage fileStorage) {
		this.fileStorage = fileStorage;
		this.parser = new ItemParser();
	}

	/**
	 * Save the String to the items file.
	 *
	 * @param inputLine String corresponding to an item.
	 */
	public void saveLineToItem(String inputLine) {
		hasErrors = false;
		Item item = parser.parseLine(inputLine);
		if (item != null) {
			item.setStorage(fileStorage);
			item.save();
		}
		else {
			hasErrors = true;
		}
	}

	/**
	 * Check if errors occurred during the last save.
	 *
	 * @return true if there where errors in during the last save.
	 */
	public boolean hasErrors() {
		return hasErrors;
	}

	/**
	 * Get the error message if errors occurred during the last save.
	 *
	 * @return empty String if no error occurred
	 */
	public String getErrorMessage() {
		return parser.getErrorMessage();
	}

}
