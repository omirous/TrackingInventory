package main;

import java.util.ArrayList;
import java.util.List;

import inventory.Item;
import inventory.format.ItemFormatter;
import inventory.format.ItemJsonFormatter;
import inventory.report.HtmlReport;
import storage.FileStorage;
import storage.FileStorageNIO;

public class HtmlReportProgram {
	public static void main(String[] args) {
		FileStorage storage = new FileStorageNIO(Configuration.createFilePath().toAbsolutePath().toString());
		ItemFormatter formatter = new ItemJsonFormatter();
		List<Item> items = new ArrayList<Item>();
		for (String itemString : storage.readLines())
			items.add(formatter.toItem(itemString));

		System.out.println(new HtmlReport(items).produce());
	}
}
