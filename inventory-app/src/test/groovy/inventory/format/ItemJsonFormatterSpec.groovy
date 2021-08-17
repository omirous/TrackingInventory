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
		String jsonString = formatter.toText(item)

		then:
		jsonString == result

		where:
		name 	| serialNumber 	| value || result
		"name"	| "sn"			| 0		|| '{ "name": "name", "sn": "sn", "value": 0 }'
		"name"	| "sn"			| 1.2	|| '{ "name": "name", "sn": "sn", "value": 1.2 }'
	}

	def 'convert json to Item' () {
		given: 'a json string representing an item'
		String json = '{ "name": "name", "sn": "sn", "value": 1.2 }'
		ItemJsonFormatter formatter = new ItemJsonFormatter()

		when: 'converting to item'
		Item item = formatter.toItem(json)

		then: 'item has the appropriate properties'
		item.name == "name"
		item.serialNumber == "sn"
		item.value == 1.2

	}
}
