package cp213;

/**
 * Sample string methods.
 *
 * @author Himanya Verma
 * @studentID 169051154
 * @email Verm1154@mylaurier.ca
 * @version 2024-09-19
 */
public class printer {
    public class Printer<T> {
	T thingToPrint;

	public Printer(T thingToPrint) {
	    this.thingToPrint = thingToPrint;
	}

	public void print() {
	    System.out.println(thingToPrint);
	}
    }

}
