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

	def 'parse an empty line' () {
		given: 'an empty String as input'
		String input = ""

		when: 'parsing that input to an Item'
		parser.parseLine(input)

		then: 'an InvalidInput expection is thrown'
		def e = thrown(InvalidInput)
		e.message == "Expected a line with item data but got an empty line instead."
	}

	def "parsing a line with no delimeters" () {
		given: 'a line with no delimeters'
		String input = "no delimeters"

		when: 'parsing that input to an Item'
		parser.parseLine(input)

		then: 'an InvalidInput expection is thrown'
		def e = thrown(InvalidInput)
		e.message == "Expected a line with 2 delimeters but got none instead."
	}

	def "parsing a line with one delimeter" () {
		given: 'a line with one delimeter'
		String input = "name;sn"

		when: 'parsing that input to an Item'
		parser.parseLine(input)

		then: 'an InvalidInput expection is thrown'
		def e = thrown(InvalidInput)
		e.message == "Expected a line with 2 delimeters but got 1 instead."
	}

}
