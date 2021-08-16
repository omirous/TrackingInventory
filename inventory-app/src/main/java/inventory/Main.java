package inventory;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		prompt();
		while(scanner.hasNext()) {
			String input = scanner.nextLine();
			System.out.println(input);
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
