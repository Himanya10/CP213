package cp213;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * Stores a List of MenuItems and provides a method return these items in a
 * formatted String. May be constructed from an existing List or from a file
 * with lines in the format:
 *
 * <pre>
1.25 hot dog
10.00 pizza
...
 * </pre>
 *
 * @author Himanya Verma
 * @author Abdul-Rahman Mawlood-Yunis
 * @author David Brown
 * @version 2024-10-15
 */
public class Menu {

    // Attributes.

    // define a List of MenuItem objects
    // Note that this must be a *List* of some flavour
    // @See
    // https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/List.html

    ArrayList<MenuItem> Menu = new ArrayList<MenuItem>();

    /**
     * Creates a new Menu from an existing List of MenuItems. MenuItems are copied
     * into the Menu List.
     *
     * @param items an existing List of MenuItems.
     */
    public Menu(List<MenuItem> items) {

	Iterator<MenuItem> iterator = items.iterator();

	while (iterator.hasNext()) {
	    MenuItem item = iterator.next();
	    Menu.add(item);
	}
    }

    /**
     * Constructor from a Scanner of MenuItem strings. Each line in the Scanner
     * corresponds to a MenuItem. You have to read the Scanner line by line and add
     * each MenuItem to the List of items.
     *
     * @param fileScanner A Scanner accessing MenuItem String data.
     */
    public Menu(Scanner fileScanner) {

	while (fileScanner.hasNextLine()) {
	    String line = fileScanner.nextLine();
	    String[] parts = line.split(" ");

	    if (parts.length >= 2) {
		double price = Double.parseDouble(parts[0]);
		String name = line.substring(parts[0].length()).trim();
		MenuItem item = new MenuItem(name, price);
		Menu.add(item);
	    }
	}

    }

    /**
     * Returns the List's i-th MenuItem.
     *
     * @param i Index of a MenuItem.
     * @return the MenuItem at index i
     */
    public MenuItem getItem(int i) {

	return Menu.get(i);
    }

    /**
     * Returns the number of MenuItems in the items List.
     *
     * @return Size of the items List.
     */
    public int size() {

	return Menu.size();
    }

    /**
     * Returns the Menu items as a String in the format:
     *
     * <pre>
    5) poutine      $ 3.75
    6) pizza        $10.00
     * </pre>
     *
     * where n) is the index + 1 of the MenuItems in the List.
     */
    @Override
    public String toString() {

	String menu = "";
	String format = "%-12s $%5.2f";
	int i = 0;

	while (i < size()) {
	    String output = "";

	    output = String.format(format, Menu.get(i).getListing(), Menu.get(i).getPrice());

	    i += 1;
	    menu += " " + i + ") " + output + '\n';
	}

	return menu;
    }
}