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
		"no delimeters"		| 0		|| "Expected a line with 2 delimeters but got 0 instead."
		"name;sn"			| 1		|| "Expected a line with 2 delimeters but got 1 instead."
		"name;sn;;"			| 3		|| "Expected a line with 2 delimeters but got 3 instead."
		"name;sn;1;2;3"		| 4		|| "Expected a line with 2 delimeters but got 4 instead."
	}

	def "parse an item" () {
		given: 'a csv input with 2 delimeters'
		String line = "name;serial;1.3"

		when: 'parsing that input to an Item'
		Item item = parser.parseLine(line)

		then: 'item properties are loaded properly'
		item.name == "name"
		item.serialNumber == "serial"
		item.value == 1.3
	}


}
