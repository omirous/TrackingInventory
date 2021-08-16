package storage

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardOpenOption

import spock.lang.Specification
import spock.lang.Stepwise

@Stepwise
class FileStorageSpec extends Specification {

	static String PROJECT_PATH = "."
	static String TEST_RESOURCES = "src/test/resources"
	static String STORAGE_FOLDER = "storage"
	static String FILE = "temp-file.txt"

	static FileStorage storage

	def setupSpec() {
		Files.createFile(filePath())
		storage = new FileStorage(filePathString())
	}

	def "append to file" () {
		when:
		storage.appendLine("hello")
		storage.appendLine("world")

		then:
		List lines = new File(filePathString()).readLines()
		lines == ["hello", "world"]
	}

	def "read file content" () {
		expect:
		storage.readLines() == ["hello", "world"]
	}

	def cleanupSpec() {
		Files.deleteIfExists(filePath())
	}

	private static String filePathString() {
		filePath().toAbsolutePath().toString()
	}

	private static Path filePath() {
		Paths.get(projectPathString(), TEST_RESOURCES, STORAGE_FOLDER, FILE)
	}

	private static String projectPathString() {
		Path current = Paths.get(PROJECT_PATH)
		current.toAbsolutePath().toString()
	}

}
