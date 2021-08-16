package storage;

import java.util.List;

public interface FileStorage {

	void appendLine(String line);

	List<String> readLines();

}