package main;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Public configuration that might be used in more than one places.
 */
public class Configuration {

	static final String FILE_PATH = "src/main/resources/storage/items.txt";

	/**
	 * Get the file path as a String, to the file keeping the items.
	 */
	public static String createFilePathString() {
		return Configuration.createFilePath().toAbsolutePath().toString();
	}

	/**
	 *  Get the file path as NIO Path, to the file keeping the items.
	 */
	public static Path createFilePath() {
		Path projectPath = Paths.get(".");
		String projectPathString = projectPath.toAbsolutePath().toString();
		return Paths.get(projectPathString, FILE_PATH);
	}

}
