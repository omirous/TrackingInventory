package inventory.report

import inventory.Item
import inventory.report.HtmlReport
import spock.lang.Specification

class HtmlReportSpec extends Specification {

	def "html report when there are no items" () {
		given: 'a list of empty items'
		HtmlReport report = new HtmlReport([])

		when: 'generating the report'
		String content = report.produce()

		then: 'expected content is generated'
		content == """<html>
<p>There are no items to report.</p>
</html>"""
	}

	def "html report when there is one item" () {
		given: 'a list of empty items'
		HtmlReport report = new HtmlReport([new Item("name", "sn", 0)])

		when: 'generating the report'
		String content = report.produce()

		then: 'expected content is generated'
		content == expectedContentForOneItem()
	}

	def "html report when there are many items" () {
		given: 'many items'
		def items = [
			new Item("name", "sn", 100),
			new Item("name with spaces", "sn with spaces", 10.54),
			new Item("N", "S", 0.01),
		]
		HtmlReport report = new HtmlReport(items)

		when: 'generating the report'
		String content = report.produce()

		then: 'expected content is generated'
		content == expectedContentForManyItems()
	}

	private String expectedContentForOneItem() {
		"""<html>
<table>
<th>Name</th>
<th>Serial Number</th>
<th>Value</th>
<tr><td>name</td><td>sn</td><td>0</td></tr>
</table>
</html>"""
	}

	private String expectedContentForManyItems() {
		"""<html>
<table>
<th>Name</th>
<th>Serial Number</th>
<th>Value</th>
<tr><td>name</td><td>sn</td><td>100</td></tr>
<tr><td>name with spaces</td><td>sn with spaces</td><td>10.54</td></tr>
<tr><td>N</td><td>S</td><td>0.01</td></tr>
</table>
</html>"""
	}
}
