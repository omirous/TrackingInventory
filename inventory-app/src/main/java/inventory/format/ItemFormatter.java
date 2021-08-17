package inventory.format;

import inventory.Item;

/**
 * Converts an Item to a text representation and a text representation to an {@link Item}.
 */
public interface ItemFormatter {

	/**
	 * Convert an item to text representation.
	 *
	 * @param item The item to convert.
	 * @return Text representation of the item.
	 */
	String toText(Item item);

	/**
	 * Convert a text representation of an item to an {@link Item} object.
	 * @param text The text representation of an item.
	 * @return The corresponding {@link Item}.
	 */
	Item toItem(String text);

}