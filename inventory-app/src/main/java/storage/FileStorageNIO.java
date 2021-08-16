package storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class FileStorageNIO {

	private String filepath;

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
