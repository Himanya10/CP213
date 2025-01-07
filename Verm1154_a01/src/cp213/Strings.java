package cp213;

/**
 * @author Your name and id here
 * @version 2024-09-01
 */
public class Strings {
    // Constants
    public static final String VOWELS = "aeiouAEIOU";

    /**
     * Determines if string is a "palindrome": a word, verse, or sentence (such as
     * "Able was I ere I saw Elba") that reads the same backward or forward. Ignores
     * case, spaces, digits, and punctuation in the string parameter s.
     *
     * @param string a string
     * @return true if string is a palindrome, false otherwise
     */
    public static boolean isPalindrome(final String string) {
	boolean result = true;

	StringBuilder cleanedString = new StringBuilder();

	for (int i = 0; i < string.length(); i++) {
	    char ch = string.charAt(i);

	    if (Character.isLetter(ch)) {
		cleanedString.append(Character.toLowerCase(ch));
	    }
	}

	int left = 0;
	int right = cleanedString.length() - 1;

	while (left < right) {
	    if (cleanedString.charAt(left) != cleanedString.charAt(right)) {
		result = false;
	    }
	    left++;
	    right--;
	}

	return result;
    }

    /**
     * Determines if name is a valid Java variable name. Variables names must start
     * with a letter or an underscore, but cannot be an underscore alone. The rest
     * of the variable name may consist of letters, numbers and underscores.
     *
     * @param name a string to test as a Java variable name
     * @return true if name is a valid Java variable name, false otherwise
     */
    public static boolean isValid(final String name) {
	boolean result = true;

	if (name == null || name.isEmpty() || name.equals("_")) {
	    result = false;
	}

	char firstChar = name.charAt(0);
	if (!Character.isLetter(firstChar) && firstChar != '_') {
	    result = false;
	}

	for (int i = 1; i < name.length(); i++) {
	    char ch = name.charAt(i);
	    if (!Character.isLetterOrDigit(ch) && ch != '_') {
		result = false;
	    }
	}

	return result;
    }

    /**
     * Converts a word to Pig Latin. The conversion is:
     * <ul>
     * <li>if a word begins with a vowel, add "way" to the end of the word.</li>
     * <li>if the word begins with consonants, move the leading consonants to the
     * end of the word and add "ay" to the end of that. "y" is treated as a
     * consonant if it is the first character in the word, and as a vowel for
     * anywhere else in the word.</li>
     * </ul>
     * Preserve the case of the word - i.e. if the first character of word is
     * upper-case, then the new first character should also be upper case.
     *
     * @param word The string to convert to Pig Latin
     * @return the Pig Latin version of word
     */
    public static String pigLatin(String word) {

	char firstchar = word.charAt(0);
	int vowelIndex = -1;

	if (VOWELS.indexOf(firstchar) != -1) {
	    return word + "way";
	}

	for (int i = 1; i < word.length(); i++) {
	    char ch = word.charAt(i);
	    if (VOWELS.indexOf(ch) != -1 || ch == 'y' || ch == 'Y') {
		vowelIndex = i;
		break;
	    }

	}

	if (vowelIndex == -1) {
	    return word + "ay";
	}

	String consontants = word.substring(0, vowelIndex);
	String restOfWord = word.substring(vowelIndex);

	String pigLatinWord = restOfWord + consontants.toLowerCase() + "ay";

	if (Character.isUpperCase(firstchar)) {
	    pigLatinWord = Character.toUpperCase(pigLatinWord.charAt(0)) + pigLatinWord.substring(1);
	}

	return pigLatinWord;
    }

}
