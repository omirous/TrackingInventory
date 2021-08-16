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
		Item item = parser.parseLine(input)

		then: 'an InvalidInput expection is thrown'
		parser.errorMessage == "Expected a line with item data but got null instead."
		item == null
	}

	def 'parse an empty line' () {
		given: 'an empty String as input'
		String input = ""

		when: 'parsing that input to an Item'
		Item item = parser.parseLine(input)

		then: 'an InvalidInput expection is thrown'
		parser.errorMessage == "Expected a line with item data but got an empty line instead."
		item == null
	}

	@Unroll
	def "a line without 3 tokens is invalid - #input" () {
		given:
		String line = input

		when: 'parsing that input to an Item'
		Item item = parser.parseLine(line)

		then: 'an InvalidInput expection is thrown'
		parser.errorMessage == message
		item == null

		where:
		input 				|| message
		"no delimeters"		|| "Expected a line with 3 tokens but got 1 instead."
		"name;sn"			|| "Expected a line with 3 tokens but got 2 instead."
		"name;sn;;"			|| "Expected a line with 3 tokens but got 2 instead."
		"name;sn;1;2;3"		|| "Expected a line with 3 tokens but got 5 instead."
		";serial;1.3"		|| "Expected a line with 3 tokens but got 2 instead."
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

	@Unroll
	def "value is not a number - #message" () {
		given:
		String line = input

		when: 'parsing that input to an Item'
		Item item = parser.parseLine(line)

		then:
		item == null
		parser.errorMessage == message

		where:
		input						|| message
		"name;serial;notANumber"    || "Expected value (3rd token) to be a number but got notANumber instead."
		"name;serial;123d"			|| "Expected value (3rd token) to be a number but got 123d instead."
	}

}
