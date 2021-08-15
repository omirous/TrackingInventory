package inventory

import spock.lang.Specification

class ItemParserSpec extends Specification {

	def "parse a null line" () {
		given:
		ItemParser parser = new ItemParser()
		String input = null

		when:
		parser.parseLine(input)

		then:
		def e = thrown(InvalidInput)
		e.message == "Expected a line with item data but got null instead."
	}

}
