package cp213;

/**
 * Inherited class in simple example of inheritance / polymorphism.
 *
 * @author
 * @version 2022-02-25
 */
public class CAS extends Professor {

    String term;

    public CAS(String firstName, String lastName, String department, String term) {
	super(firstName, lastName, department);
	this.term = term;
    }

    public String getTerm() {
	return this.term;
    }

    @Override
    public String toString() {
	return super.toString() + '\n' + "Term: " + this.term;
    }

}
