package inventory.programs

import spock.lang.Specification
import storage.FileStorage

class SaveLineAsItemProgramSpec extends Specification {

	def 'save item generates error'() {
		given:
		String line = 'Sony Bravia;SB,700'
		FileStorage storage = Mock()
		SaveLineAsItemProgram pgm = new SaveLineAsItemProgram(storage)

		when:
		pgm.saveLineToItem(line)

		then:
		pgm.hasErrors()
		0 * storage.appendLine(_)
		pgm.getErrorMessage() == "Expected a line with 3 tokens but got 2 instead."
	}

	def 'save item'() {
		given:
		String line = 'Sony Bravia;SB;700'
		FileStorage storage = Mock()
		SaveLineAsItemProgram pgm = new SaveLineAsItemProgram(storage)

		when:
		pgm.saveLineToItem(line)

		then:
		!pgm.hasErrors()
		1 * storage.appendLine(_)
		pgm.getErrorMessage() == ""
	}
}
