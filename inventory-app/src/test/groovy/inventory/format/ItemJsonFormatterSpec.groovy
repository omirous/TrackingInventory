package inventory.format

import inventory.Item
import spock.lang.Specification
import spock.lang.Unroll

class ItemJsonFormatterSpec extends Specification {

	@Unroll
	def 'convert Item(#name, #serialNumber, #value) to json' () {
		given: 'an item'
		Item item = new Item(name, serialNumber, value)
		ItemJsonFormatter formatter = new ItemJsonFormatter()

		when: 'converting to json'
		String jsonString = formatter.toJson(item)

		then:
		jsonString == result

		where:
		name 	| serialNumber 	| value || result
		"name"	| "sn"			| 0		|| '{ "name": "name", "sn": "sn", "value": 0 }'
		"name"	| "sn"			| 1.2	|| '{ "name": "name", "sn": "sn", "value": 1.2 }'
	}
}
