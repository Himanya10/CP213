package cp213;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Utilities for working with Movie objects.
 *
 * @author your name, id, email here
 * @version 2024-09-01
 */
public class MovieUtilities {

    /**
     * Counts the number of movies in each genre given in Movie.GENRES. An empty
     * movies list should produce a count array of: [0,0,0,0,0,0,0,0,0,0,0]
     *
     * @param movies List of movies.
     * @return Number of genres across all Movies. One entry for each genre in the
     *         Movie.GENRES array.
     */
    public static int[] genreCounts(final ArrayList<Movie> movies) {

	int[] counts = new int[Movie.GENRES.length];

	for (Movie movie : movies) {
	    int genre = movie.getGenre();
	    if (genre >= 0 && genre < counts.length) {
		counts[genre]++;
	    }
	}

	return counts;
    }

    /**
     * Creates a Movie object by requesting data from a user. Uses the format:
     *
     * <pre>
    Title:
    Year:
    Director:
    Rating:
    Genres:
    0: science fiction
    1: fantasy
    ...
    10: mystery
    
    Enter a genre number:
     * </pre>
     *
     * @param keyboard A keyboard (System.in) Scanner.
     * @return A Movie object.
     */
    public static Movie getMovie(final Scanner keyboard) {

	System.out.print("Title: ");
	String title = keyboard.nextLine();

	System.out.print("Year: ");
	int year = keyboard.nextInt();
	keyboard.nextLine();

	System.out.print("Director: ");
	String director = keyboard.nextLine();

	System.out.print("Rating (0.0-10.0): ");
	Double rating = keyboard.nextDouble();

	System.out.print("Genre: ");
	System.out.println(Movie.genresMenu());

	System.out.print("Enter a Genre Number: ");
	int genre = keyboard.nextInt();

	return new Movie(title, year, director, rating, genre);
    }

    /**
     * Creates a list of Movies whose genre is equal to the genre parameter.
     *
     * @param movies List of movies.
     * @param genre  Genre to compare against.
     * @return List of movies of genre.
     */
    public static ArrayList<Movie> getByGenre(final ArrayList<Movie> movies, final int genre) {

	ArrayList<Movie> filteredMovies = new ArrayList<>();

	for (Movie movie : movies) {
	    if (movie.getGenre() == genre) {
		filteredMovies.add(movie);
	    }
	}

	return filteredMovies;
    }

    /**
     * Creates a list of Movies whose ratings are equal to or higher than rating.
     *
     * @param movies List of movies.
     * @param rating Rating to compare against.
     * @return List of movies of rating or higher.
     */
    public static ArrayList<Movie> getByRating(final ArrayList<Movie> movies, final double rating) {

	ArrayList<Movie> filteredMovies = new ArrayList<>();

	for (Movie movie : movies) {
	    if (movie.getRating() == rating) {
		filteredMovies.add(movie);
	    }
	}

	return filteredMovies;
    }

    /**
     * Creates a list of Movies from a particular year.
     *
     * @param movies List of movies.
     * @param year   Year to compare against.
     * @return List of movies of year.
     */
    public static ArrayList<Movie> getByYear(final ArrayList<Movie> movies, final int year) {

	ArrayList<Movie> filteredMovies = new ArrayList<>();

	for (Movie movie : movies) {
	    if (movie.getYear() == year) {
		filteredMovies.add(movie);
	    }

	}

	return filteredMovies;
    }

    /**
     * Asks a user to select a genre from a list of genres displayed by calling
     * Movie.genresMenu() and returns an integer genre code. The genre must be a
     * valid index to an item in Movie.GENRES.
     *
     * @param keyboard A keyboard (System.in) Scanner.
     * @return An integer genre code.
     */
    public static int readGenre(final Scanner keyboard) {

	System.out.println(Movie.genresMenu());
	System.out.print("Enter a Genre Number: ");
	int genre = keyboard.nextInt();

	while (genre < 0 || genre >= Movie.GENRES.length) {
	    System.out.print("Invalid Genre. Input a Valid Genre Number: ");
	    genre = keyboard.nextInt();
	}

	return genre;
    }

    /**
     * Creates and returns a Movie object from a line of formatted string data.
     *
     * @param line A vertical bar-delimited line of movie data in the format
     *             title|year|director|rating|genre
     * @return The data from line as a Movie object.
     */
    public static Movie readMovie(final String line) {
	String[] fields = line.split("\\|");

	String title = fields[0];
	int year = Integer.parseInt(fields[1]);
	String director = fields[2];
	double rating = Double.parseDouble(fields[3]);
	int genre = Integer.parseInt(fields[4]);

	return new Movie(title, year, director, rating, genre);
    }

    /**
     * Reads a list of Movies from a file.
     *
     * @param fileIn A Scanner of a Movie data file in the format
     *               title|year|director|rating|genre
     * @return A list of Movie objects.
     */
    public static ArrayList<Movie> readMovies(final Scanner fileIn) {

	ArrayList<Movie> movies = new ArrayList<>();

	while (fileIn.hasNextLine()) {
	    String line = fileIn.nextLine();
	    movies.add(readMovie(line));
	}

	return movies;
    }

    /**
     * Writes the contents of a list of Movie to a PrintStream.
     *
     * @param movies A list of Movie objects.
     * @param ps     Output PrintStream.
     */
    public static void writeMovies(final ArrayList<Movie> movies, PrintStream ps) {

	for (Movie movie : movies) {
	    movie.write(ps);
	}

	return;
    }

}
