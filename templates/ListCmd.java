import java.util.List;
import java.util.Objects;

/**
 * Subclass of superclass Library Command
 * Designed for listing books in library
 */
public class ListCmd extends LibraryCommand {
    /**
     * Initialises String listType
     * This is used to store what type of list the user wants
     * ("Long" or "Short")
     */
    private String listType;
    /** Method ListCmd
     * Instantiates ListCmd object with LIST
     from Command type enum and argumentInput
     * Input Parameters
     * @param argumentInput stores string from user input following "LIST"
     */
    public ListCmd(String argumentInput) {
        super(CommandType.LIST, argumentInput);
    }
    @Override//parseArguments
    /** Method parseArguments
     * Verifies argumentInput is valid before executing command
     * 1. Checks if argument is Null and reports error if it is.
     * 2. Assigns the boolean inputValid false for the input validity
     * 3. Checks if the user input is empty.
     * 4. If 3. is false, then listType is assigned to search term converted
     to lowercase, and listType (initialised above) is set to true if the
     listType is "long" or "short"
     * 5. If the user input was blank, listType is set to "list" and validInput
     is set to true.
     * 6. If 4 or 5 are true, then the input is valid.
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
        // 3.
        if (!argumentInput.isBlank()) {
            // 4.
            listType = argumentInput.toLowerCase();
            validInput = listType.equals("short") || listType.equals("long");
            // 5.
        } else if (argumentInput.replace(" ", "").toLowerCase().isBlank()) {
            listType = "list";
            validInput = true;
        }
        // 6.
        return validInput;
    }
    @Override//execute
    /** Method execute
     *  Executes ListCmd Command on provided listType
     * 1. Checks if data is null
     * 2. Checks if there are any books in the library. If is empty,
     the appropriate message will be printed, if not then the else code
     run
     *3. Runs printBooks function, passing in the books from the library data
     * @param data contains all the library data
     * @throws NullPointerException if argumentInput is empty
     * */
    public void execute(LibraryData data) {
        // 1.
        Objects.requireNonNull(data, "Given input argument must not be null.");
        // 2.
        if (data.getBookData().isEmpty()) {
            System.out.println("The library has no books");
        } else {
            // 3.
            printBooks(data.getBookData());
        }
    }
    /** Method printBooks
     * Prints books in a List of bookEntries
     * 1. Prints number of books in the library
     * 2. Starts loop through books
     * 3. If the listType variable is equal to "short" or "list", then the
     method will print out all the titles of the books in the library
     * 4. If the listType variable is equal to "long", then the
     method will use the toString method of the BookEntry class
     to print all the books
     * Input parameters:
     * @param books contains a list of book entries
     * Input Exceptions
     * @throws NullPointerException if argumentInput is empty
     */
    public void printBooks (List<BookEntry> books){
        // 1.
        System.out.println(books.size() + " books in library:");
        // 2.
        for (BookEntry book : books) {
            //3.
            if (listType.equals("short") || listType.equals("list")) {
                System.out.println(book.getTitle());
                //4.
            } else if (listType.equals("long")) {
                System.out.print(book);
                System.out.println("\n");
            }
        }
    }
}