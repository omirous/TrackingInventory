package inventory

import spock.lang.Specification
import spock.lang.Unroll

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

	@Unroll
	def "parse a line with #num delimeters" () {
		given:
		String line = input

		when: 'parsing that input to an Item'
		parser.parseLine(line)

		then: 'an InvalidInput expection is thrown'
		def e = thrown(InvalidInput)
		e.message == message

		where:
		input 				| num 	|| message
		"no delimeters"		| 0		|| "Expected a line with 2 delimeters but got none instead."
		"name;sn"			| 1		|| "Expected a line with 2 delimeters but got 1 instead."
	}


}
