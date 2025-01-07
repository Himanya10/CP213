package cp213;

import java.util.Scanner;

/**
 * Wraps around an Order object to ask for MenuItems and quantities.
 *
 * @author Himanya Verma
 * @author Abdul-Rahman Mawlood-Yunis
 * @author David Brown
 * @version 2024-10-15
 */
public class Cashier {

    private Menu menu = null;

    /**
     * Constructor.
     *
     * @param menu A Menu.
     */
    public Cashier(Menu menu) {
	this.menu = menu;
    }

    /**
     * Reads keyboard input. Returns a positive quantity, or 0 if the input is not a
     * valid positive integer.
     *
     * @param scan A keyboard Scanner.
     * @return
     */
    private int askForQuantity(Scanner scan) {
	int quantity = 0;
	System.out.print("How many do you want? ");

	try {
	    String line = scan.nextLine();
	    quantity = Integer.parseInt(line);
	} catch (NumberFormatException nfex) {
	    System.out.println("Not a valid number");
	}
	return quantity;
    }

    /**
     * Prints the menu.
     */
    private void printCommands() {
	System.out.println("\nMenu:");
	System.out.println(menu.toString());
	System.out.println("Press 0 when done.");
	System.out.println("Press any other key to see the menu again.\n");
    }

    /**
     * Asks for commands and quantities. Prints a receipt when all orders have been
     * placed.
     *
     * @return the completed Order.
     */
    public Order takeOrder() {
	System.out.println("Welcome to WLU Foodorama!");
	printCommands();

	Scanner customer = new Scanner(System.in);
	Order order = new Order();
	boolean proceed = true;

	while (proceed) {
	    System.out.print("Command: ");

	    try {
		int selection = customer.nextInt();
		customer.nextLine(); // Clear newline from the buffer

		if (selection == 0) {
		    proceed = false;
		} else if (selection > 0 && selection <= menu.size()) {
		    MenuItem item = menu.getItem(selection - 1);
		    int quantity = askForQuantity(customer);

		    if (quantity > 0) {
			order.add(item, quantity);
			System.out.println("Added " + quantity + " x " + item.getListing());
		    }
		} else {
		    System.out.println("Invalid selection. Please try again.");
		}
	    } catch (Exception e) {
		System.out.println("Invalid input, please enter a number.");
		customer.nextLine(); // Clear invalid input
	    }
	}

	customer.close();
	System.out.println("\nOrder complete. Here's your receipt:\n");
	System.out.println(order.toString());

	return order;
    }
}