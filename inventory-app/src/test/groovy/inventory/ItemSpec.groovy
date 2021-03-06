package inventory

import java.nio.file.FileStore

import inventory.format.ItemFormatter
import inventory.validate.InvalidItem
import spock.lang.Specification
import storage.FileStorage

class ItemSpec extends Specification {

	def "item has a name, a serial number and a value" () {
		given: 'a name, a serial number and a value'
		String name = "name"
		String sn = "sn"
		BigDecimal value = BigDecimal.ZERO

		when: 'creating an item'
		Item item = new Item(name, sn, value)

		then: 'these properties can be retrieved'
		item.name == name
		item.serialNumber == sn
		item.value == value
	}

	def "item's name cannot be null" () {
		when: 'creating an item with a null name'
		new Item(null, "sn", BigDecimal.ZERO)

		then: 'an InvalidItem exception is thrown'
		def e = thrown(InvalidItem)
		e.message == "Expected an item with a name but got a null name instead."
	}

	def "item's name cannot be empty"() {
		when: 'creating an item with an empty name'
		new Item("", "sn", BigDecimal.ZERO)

		then: 'an InvalidItem exception is thrown'
		def e = thrown(InvalidItem)
		e.message == "Expected an item with a name but got an empty name instead."
	}

	def "item's serial number cannot be null" () {
		when: 'creating an item with a null name'
		new Item("name", null, BigDecimal.ZERO)

		then: 'an InvalidItem exception is thrown'
		def e = thrown(InvalidItem)
		e.message == "Expected an item with a serial number but got a null serial number instead."
	}

	def "item's serial number cannot be empty"() {
		when: 'creating an item with an empty name'
		new Item("name", "", BigDecimal.ZERO)

		then: 'an InvalidItem exception is thrown'
		def e = thrown(InvalidItem)
		e.message == "Expected an item with an empty serial number but got an empty serial number instead."
	}

	def "item's value cannot be null"() {
		when: 'creating an item with a null value'
		new Item("name", "sn", null)

		then: 'an InvalidItem exception is thrown'
		def e = thrown(InvalidItem)
		e.message == 'Expected an item with a value but got null value instead.'
	}

	def "save an Item" () {
		given: 'an item'
		String name = "name"
		String sn = "sn"
		BigDecimal value = BigDecimal.ZERO
		Item item = new Item(name, sn, value)

		and: 'a file storage for the item'
		FileStorage myStorage = Mock()
		item.storage = myStorage

		and: 'an item formatter'
		ItemFormatter myItemFormatter = Mock()
		item.formatter = myItemFormatter

		when: 'saving an item'
		item.save()

		then: "FileStorage appendLine is called"
		1 * myItemFormatter.toText(item) >> {"name#sn#30"}
		1 * myStorage.appendLine("name#sn#30")

	}

}
