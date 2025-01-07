package cp213;

import java.time.LocalDate;

/**
 * Student class definition.
 *
 * @author Himanya Verma
 * @version 2024-10-01
 */
public class Student implements Comparable<Student> {

    // Attributes
    private LocalDate birthDate = null;
    private String forename = "";
    private int id = 0;
    private String surname = "";

    /**
     * Instantiates a Student object.
     *
     * @param id        student ID number
     * @param surname   student surname
     * @param forename  name of forename
     * @param birthDate birthDate in 'YYYY-MM-DD' format
     */
    public Student(int id, String surname, String forename, LocalDate birthDate) {

	this.id = id;
	this.surname = surname;
	this.forename = forename;
	this.birthDate = birthDate;

	return;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString() Creates a formatted string of student data.
     */
    @Override
    public String toString() {

	String string = String.format("Name:%6s%s, %s %nID: %13d%nBirthdate: %s", "", surname, forename, id, birthDate);
	return string;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(final Student target) {
	int result = this.surname.compareTo(target.surname);

	if (result == 0) {
	    result = this.forename.compareTo(target.forename);
	}
	if (result == 0) {
	    result = Integer.compare(this.id, target.id);
	}

	return result;
    }

    // getters and setters defined here

    // getters
    public LocalDate getBirthDate() {
	return birthDate;
    }

    public String getForename() {
	return forename;
    }

    public int getId() {
	return id;
    }

    public String getSurname() {
	return surname;
    }

    // setters
    public void setBirthDate(LocalDate birthDate) {
	this.birthDate = birthDate;
    }

    public void setForename(String forename) {
	this.forename = forename;
    }

    public void setId(int id) {
	this.id = id;
    }

    public void setSurname(String surname) {
	this.surname = surname;
    }
}
