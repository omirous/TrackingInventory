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
}
