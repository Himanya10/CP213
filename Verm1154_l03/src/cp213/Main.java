package cp213;

import java.time.LocalDate;

/**
 * Tests the Student class.
 *
 * @author Himanya Verma
 * @version 2022-01-17
 */
public class Main {

    public static void main(String[] args) {
	final String line = "-".repeat(40);

	int id = 123456;
	String surname = "Brown";
	String forename = "David";
	LocalDate birthDate = LocalDate.parse("1962-10-25");

	Student student = new Student(id, surname, forename, birthDate);

	System.out.println("New Student:");
	System.out.println(student);
	System.out.println(line);

	System.out.println("Test Getters");
	// call getters here
	System.out.println("ID: " + student.getId());
	System.out.println("Forename: " + student.getForename());
	System.out.println("Suranme: " + student.getSurname());
	System.out.println("Birth Date: " + student.getBirthDate());
	System.out.println(line);

	System.out.println("Test Setters");
	// call setters here
	student.setId(654321);
	student.setForename("Wilfred ");
	student.setSurname("Laurier");
	student.setBirthDate(LocalDate.parse("1841-11-20"));

	System.out.println("Updated Student:");
	System.out.println(student);
	System.out.println(line);

	System.out.println("Test compareTo");
	// create new Students - call comparisons here

	Student student1 = new Student(100001, " Washington", "George", LocalDate.parse("1732-02-22"));
	Student student2 = new Student(100002, " Churchhill", "Winston", LocalDate.parse("1874-11-30"));

	System.out.println("Comparing " + student + " and " + student1 + ": " + student.compareTo(student1));
	System.out.println("Comparing " + student + " and " + student2 + ": " + student.compareTo(student2));
	System.out.println("Comparing " + student1 + " and" + student2 + ": " + student1.compareTo(student2));

    }

}
