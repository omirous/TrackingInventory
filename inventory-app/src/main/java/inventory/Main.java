package inventory;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ItemParser parser = new ItemParser();
		prompt();
		while(scanner.hasNext()) {
			String line = scanner.nextLine();
			Item item = parser.parseLine(line);
			if (item != null)
				System.out.println("Item parsed");
			else
				System.err.println(parser.getErrorMessage());
			prompt();
		}
	}

	private static void prompt() {
		System.out.println(message());
	}

	private static String message() {
		return "Enter the data for an item, separated by delimeters (;)";
	}

}
