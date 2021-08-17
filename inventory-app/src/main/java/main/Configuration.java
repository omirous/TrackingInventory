package main;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Configuration {

	static final String FILE_PATH = "src/main/resources/storage/items.txt";

	public static Path createFilePath() {
		Path projectPath = Paths.get(".");
		String projectPathString = projectPath.toAbsolutePath().toString();
		return Paths.get(projectPathString, FILE_PATH);
	}

}
