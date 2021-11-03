import java.util.Objects;

/**
 * Subclass of superclass Library Command
 * Designed for searching for books in Library
 */
public class SearchCmd extends LibraryCommand {
    /**
     * initialises String searchTerm
     * This is used to store the search term given by the user
     */
    private String searchTerm;
    /** Method SearchCmd
     * Instantiates SearchCmd object with SEARCH
     from Command type enum and argumentInput
     * Input Parameters
     * @param argumentInput stores string from user input following "SEARCH"
     */
    public SearchCmd(String argumentInput) {
        super(CommandType.SEARCH, argumentInput);
    }
    @Override//parseArguments
    /** Method parseArguments
     * Verifies argumentInput is valid before executing command
     * 1. Checks if argument is Null and reports error if it is.
     * 2. Assigns the boolean inputValid false for the input validity,
     and the initialised variable searchTerm to the user input string
     * 3. Checks if searchTerm contains spaces or is blank. If neither are true,
     the input is valid and so validInput is set to true. validInput is then
     returned
     * Input Parameters:
     * @param argumentInput contains input from user
     * Output Parameters:
     * @return validInput used for verification of inputArgument
     * Exceptions
     * @throws NullPointerException if argumentInput is empty*/
    protected boolean parseArguments(String argumentInput) {
        // 1.
        Objects.requireNonNull(argumentInput);
        // 2.
        boolean validInput = false;
        searchTerm = argumentInput;
        // 3.
        if (!searchTerm.isBlank() && !argumentInput.contains(" ")) {
            validInput = true;
        }
        return validInput;
    }
    @Override//execute
    /** Method execute
     *  Executes SearchCmd Command on provided search term
     * 1. Checks if data is null
     * 2. Initialises a counter variable, counter,  and begins loop
     through books in library
     * 3. If a book is found with the given search term, then the counter
     is increased by 1 and the book found is printed.
     4.  If the counter is still 0, the user is informed that no books were found
     with the provided search criteria
     * Input parameters:
     * @param data contains the library data
     * Exceptions:
     * @throws NullPointerException if argumentInput is empty
     * */
    public void execute(LibraryData data) {
        // 1.
        Objects.requireNonNull(data, "Given input argument must not be null.");
        // 2.
        int noOfBooksFound = 0;
        for (BookEntry book : data.getBookData()) {
            if (book.getTitle().toLowerCase().contains(searchTerm.toLowerCase())) {
                System.out.println(book.getTitle());
                noOfBooksFound++;
            }
        }
        // 4.
        if (noOfBooksFound == 0) {
            System.out.println("No hits found for search term: " + searchTerm);
        }
    }
}