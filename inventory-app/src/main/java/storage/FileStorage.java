package storage;

import java.util.List;

/**
 * A very simple abstraction for appending data at the end of a file (line by
 * line) and reading back all the lines of the file.
 */
public interface FileStorage {

	/**
	 * Append a line at the end of a file.
	 *
	 * @param line The line to add.
	 */
	void appendLine(String line);

	/**
	 * Get all the file content, line by line.
	 *
	 * @return All the lines of a file.
	 */
	List<String> readLines();

}