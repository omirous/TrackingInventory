package inventory.reports

import inventory.HtmlReport
import inventory.Item
import spock.lang.Specification

class HtmlReportSpec extends Specification {


	def "html report when there are no items" () {
		given: 'a list of empty items'
		HtmlReport report = new HtmlReport([])

		when: 'generating the report'
		String content = report.produce()

		then:
		content == """<html>
<p>There are no items to report.</p>
</html>"""
	}

	def "html report when there is one item" () {
		given: 'a list of empty items'
		HtmlReport report = new HtmlReport([new Item("name", "sn", 0)])

		when: 'generating the report'
		String content = report.produce()

		then:
		content == """<html>
<table>
<th>Name</th>
<th>Serial Number</th>
<th>Value</th>
<tr><td>name</td><td>sn</td><td>0</td></tr>
</table>
</html>"""
	}
}
