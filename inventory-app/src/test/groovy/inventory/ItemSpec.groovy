package inventory

import spock.lang.Specification

class ItemSpec extends Specification {

	def "item has a name, a serial number and a value" () {
		given:
		String name = "name"
		String sn = "sn"
		BigDecimal value = BigDecimal.ZERO

		when:
		Item item = new Item(name, sn, value)

		then:
		item.name == name
		item.serialNumber == sn
		item.value == value
	}

	//validations
	//name and serial number not null, not empty
	//value cannot be null

	def "item's name cannot be null" () {
		when: 'creating an item with a null name'
		new Item(null, "sn", BigDecimal.ZERO)

		then: 'an InvalidItem exception is thrown'
		def e = thrown(InvalidItem)
		e.message == "Expected an item with a name but got a null name instead."
	}



}
