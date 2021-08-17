package inventory.report

import inventory.Item
import spock.lang.Specification

class CsvReportSpec extends Specification {

	def 'csv report when there are no items' () {
		given: 'no items'
		CsvReport report = new CsvReport([]);

		when: 'generating the report'
		String content = report.produce()

		then: 'an empty String is returned'
		content == ""
	}

	def 'csv report when there is one item'() {
		given: 'one item'
		CsvReport report = new CsvReport([new Item("name", "sn", 0.5)]);

		when: 'generating the report'
		String content = report.produce()

		then: 'one line is returned'
		content == "name,sn,0.5"
	}

	def 'csv report when there are many items'() {
		given: 'many items'
		def items = [
			new Item("name", "sn", 100),
			new Item("name with spaces", "sn with spaces", 10.54),
			new Item("N", "S", 0.01),
		]
		CsvReport report = new CsvReport(items);

		when: 'generating the report'
		String content = report.produce()

		then: 'one line for each item is returns'
		content == "name,sn,100\nname with spaces,sn with spaces,10.54\nN,S,0.01"
	}

}
