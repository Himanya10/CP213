package cp213;

import java.util.Scanner;

/**
 * Class to demonstrate the use of Scanner with a keyboard and File objects.
 *
 * @author Himanya Verma
 * @version 2022-09-11
 */
public class ScannerTest {

    public static int countLines(final Scanner source) {
	int count = 0;

	while (source.hasNextLine()) {
	    source.nextLine();
	    count++;
	}
	return count;
    }

    public static int countTokens(final Scanner source) {
	int tokens = 0;
	while (source.hasNext()) {
	    source.next();
	    tokens++;
	}
	return tokens;
    }

    public static int readNumbers(final Scanner keyboard) {
	int total = 0;

	while (keyboard.hasNext()) {
	    System.out.println("Enter Number, press q to quit");
	    if (keyboard.hasNextInt()) {
		int number = keyboard.nextInt();
		total += number;
	    } else {
		String quit = keyboard.next();
		if (quit.equals("q")) {
		    break;
		}
		System.out.println("" + quit + "'is not an integer or 'q'");
	    }

	}

	return total;

    }
}