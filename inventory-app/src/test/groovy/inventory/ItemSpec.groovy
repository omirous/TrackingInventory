package inventory

import spock.lang.Specification

class ItemSpec extends Specification {

	def "can create item"() {
		expect:
		Item item = new Item()
	}
}
