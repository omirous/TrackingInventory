package storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * {@link FileStorage} implementation, using Java NIO.
 */
public class FileStorageNIO implements FileStorage {

	private String filepath;

	/**
	 * Create a new {@link FileStorage} that points to a specific file.
	 *
	 * You need to provide a filepath that points to the specific file.
	 * This is a simple string that represents the complete path to the file.
	 *
	 * For example, c:/my_folder/myfile.txt
	 *
	 * @param filepath the file path to the file.
	 */
	public FileStorageNIO(String filepath) {
		this.filepath = filepath;
	}

	public void appendLine(String line) {
		try {
			String newLine = String.format("%s\n", line);
			Files.write(Paths.get(filepath), newLine.getBytes(), StandardOpenOption.APPEND);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public List<String> readLines() {
		try {
			return Files.readAllLines(Paths.get(filepath));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
