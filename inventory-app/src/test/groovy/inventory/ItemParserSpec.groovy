package inventory

import spock.lang.Specification

class ItemParserSpec extends Specification {

	ItemParser parser

	def setup() {
		parser = new ItemParser()
	}

	def "parse a null line" () {
		given: 'null input'
		String input = null

		when: 'parsing that input to an Item'
		parser.parseLine(input)

		then: 'an InvalidInput expection is thrown'
		def e = thrown(InvalidInput)
		e.message == "Expected a line with item data but got null instead."
	}



}
