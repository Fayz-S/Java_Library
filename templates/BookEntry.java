import java.util.Arrays;
import java.util.Objects;

public final class BookEntry {
    private final String title;
    private final String[] authors;
    private final float rating;
    private final String ISBN;
    private final int pages;
    /** Creates a book entry and initialises it with input parameters
     * 1. Makes sure none of the input parameters are null
     * 2. Checks if the rating is between 0 and 5.
     * 3. Checks if pages are not negative is between 0 and 5.
     * Input Parameters:
     * @param title contains String of title of book
     * @param authors contains String array of authors of book
     * @param rating contains float ratings of book
     * @param ISBN contains String ISBN of book
     * @param pages contains int number of pages in book
     * Exception:
     * @throws NullPointerException if argumentInput is empty
     * @throws IllegalArgumentException if rating is not between 0 and 5,
     * or if there is a negative number of pages
     * */
    public BookEntry(String title, String[] authors, float rating, String ISBN, int pages) {
        // 1.
        Objects.requireNonNull(title, "Given argument input must not be null.");
        Objects.requireNonNull(authors, "Given argument input must not be null.");
        Objects.requireNonNull(rating, "Given argument input must not be null.");
        Objects.requireNonNull(ISBN, "Given argument input must not be null.");
        Objects.requireNonNull(pages, "Given argument input must not be null.");
        // 2.
        if (rating < 0 || rating > 5) {
            throw new IllegalArgumentException("Must be between 0 and 5");
            // 3.
        } else if (pages < 0){
            throw new IllegalArgumentException("Pages cannot be negative");
        }
        this.title = title;
        this.authors = authors;
        this.rating = rating;
        this.ISBN = ISBN;
        this.pages = pages;
    }
    // -------------------- Getter Functions -------------------- //
    public String getTitle() {
        return title;
    }
    /** Get authors is sorted first before returned*/
    public String[] getAuthors() {
        Arrays.sort(authors);
        return authors;    }
    public float getRating() {
        return rating;
    }
    public String getISBN() {
        return ISBN;
    }
    public int getPages() {
        return pages;
    }
    // -------------------- Other Functions -------------------- //
    /** Method arrayToString
     * Used to remove square brackets from array
     * Input Parameters
     * @param array contains String array to be altered
     * Output Parameters
     * @return converts array to string with square brackets removed
     * */
    private String arrayToString (String [] array) {
        return Arrays.toString(array)
                   .replace("[", "")
                   .replace("]", "");
    }
    @Override//toString
    /** Method toString
     * Overwrites default toString() object method for printing book entries
     * 1. The arrayToString function is applied to the authors attribute,
     with the result stored in authorsOutput
     * 2. Creates the ratingFormatted, which stores the ratings attribute
     of the book to 2 decimal places as a string
     * 3. Outputs the attributes in appropriate format, which is then printed
     * Output Parameters
     * @ return attributes in appropriate format for printing
     * */
    public String toString() {
        // 1.
        String authorsOutput = arrayToString (getAuthors());
        // 2.
        String ratingFormatted = String.format("%.2f", getRating());
        // 3.
        return (getTitle() + "\n" + "by " + authorsOutput + "\n" + "Rating: " + ratingFormatted + "\n" + "ISBN: " + getISBN() + "\n" + + getPages() + " pages");
    }
    @Override//equals
    /** Method equals
     * Overwrites default equals object method so book entries can be compared
     * 1. Checks if Object obj is being compared to itself
     * 2. Checks if obj is instance of BookEntry Class so it can be compared
     * 3. Creates object with type BookEntry by casting obj type BookEntry.
     This allows the object to use BookEntry methods
     * 4. Compares values and returns true if they are all equal, else returns false
     * Input Parameters:
     * @param obj contains object passed in for comparison
     * Output Parameters
     * @return boolean
     * */
    public boolean equals (Object obj) {
        //1.
        if (obj == this) {
            return true;
        }
        // 2.
        if (!(obj instanceof BookEntry)){
            return false;
        }
        // 3.
        BookEntry object = (BookEntry) obj;
        // 4.
        return  (getTitle().equals(object.getTitle())) &&
                    (Arrays.toString(getAuthors()).equals(Arrays.toString(object.getAuthors()))) &&
                    (getRating() == object.getRating()) &&
                    (getISBN().equals(object.getISBN())) &&
                    (getPages() == object.getPages());
    }
    @Override//haschode
    /** Method hashcode
     * Overwrites default hashcode object method to generate
     hashcode for book entries.
     * If BookEntry attribute is not a number, the hashcode object for the given
     attribute type is called to generate its own hashcode. The hash starts on
     7 and 31 is multiplied by the hash before adding the hash number given
     from the attribute. This ensures a unique result is
     obtained for every function
     * Output Parameters
     * @return generated hashcode
     * */
    public int hashCode() {
        int hash = 7;
        hash = (31 * hash) + (getTitle() == null ? 0 : getTitle().hashCode());
        hash = (31 * hash) + arrayToString (getAuthors()).hashCode();
        hash = (31 * hash) + (int) getRating();
        hash = (31 * hash) + getISBN().hashCode();
        hash = (31 * hash) + getPages();
        return hash;
    }
    // --------------------------------------------------------- //
}