package inventory

import spock.lang.Specification

class ItemSpec extends Specification {

	def "can create item"() {
		expect:
		Item item = new Item()
	}

	def "item has a name and a serial number" () {
		given:
		String name = "name"
		String sn = "sn"

		when:
		Item item = new Item(name, sn)

		then:
		item.name == name
		item.serialNumber == sn
	}
}
