import java.awt.print.Book;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Subclass of superclass Library Command
 * Designed for removing books from Library
 */
public class RemoveCmd extends LibraryCommand {
    /**
     * Initialises String removeType and String removeTerm
     * remoteType is used to store whether the user wants to remove books
     * via an author or a title
     * removeTerm contains the title or author itself
     */
    private String removeType;
    private String removeTerm;
    /** Method RemoveCmd
     * Instantiates RemoveCmd object  with REMOVE
     from Command type enum and argumentInput
     * Input Parameters
     * @param argumentInput stores string from user input following "REMOVE"
     */
    public RemoveCmd(String argumentInput) {
        super(CommandType.REMOVE, argumentInput);
    }
    @Override//parseArguments
    /** Method parseArguments
     * Verifies argumentInput is valid before executing command
     * 1. Checks if argument is Null and reports error if it is. If not,
     it assigns the boolean inputValid false for the input validity,
     and the input string is trimmed and stored in formattedInput
     * 2. Runs if formattedInput is larger than 6, so that substrings for
     "author" and "title" can be obtained from formattedInput
     * 3. Checks if the first part of formattedString is "title" or "author". If one
     of them is true removeType variable declared above is assigned to the first
     part, and the rest of formattedString (that should contain the title/author)
     is assigned to removeTerm. validInput is also changed to true as the
     input is now valid
     * 4. Returns validity of argument via inputValid
     * Input Parameters:
     * @param argumentInput contains input from user
     * Output Parameters:
     * @return boolean validInput used for verification of inputArgument
     * Exceptions
     * @throws NullPointerException if argumentInput is empty*/
    protected boolean parseArguments(String argumentInput) {
        // 1.
        Objects.requireNonNull(argumentInput);
        boolean validInput = false;
        String formattedInput = argumentInput.trim();
        if (!(formattedInput.length() < 7)) {
            // 3.
            if (formattedInput.substring(0, 5).toLowerCase().equals("title")) {
                validInput = true;
                removeType = formattedInput.substring(0, 5);
                removeTerm = formattedInput.substring(6).trim();
            } else if (formattedInput.substring(0, 6).toLowerCase().equals("author")) {
                validInput = true;
                removeType = formattedInput.substring(0, 6);
                removeTerm = formattedInput.substring(7).trim();
            }
        }
        // 4.
        return validInput;
    }
    @Override//execute
    /** Method execute
     *  Executes RemoveCmd Command on provided title/author
     * 1. Checks if data is null
     * 2. Checks if there are any books in the library. If is empty,
     the appropriate message will be printed, if not then the else code
     run
     * 3. Checks if the user is removing a title or author, and runs the
     appropriate removal function
     * Input Parameters:
     * @param data contains the library data
     * Exceptions:
     * @throws NullPointerException if argumentInput is empty*/
    public void execute(LibraryData data) {
        // 1.
        Objects.requireNonNull(data);
        // 2.
        if (data.getBookData().isEmpty()) {
            System.out.println("The library has no books");
        } else {
            // 3.
            if (removeType.toLowerCase().equals("title")) {
                removeDataTitle(data.getBookData());
            } else if (removeType.toLowerCase().equals("author")) {
                removeDataAuthor(data.getBookData());
            }
        }
    }
    // -------------------- Removal Functions -------------------- //
    /** Method removeDataAuthor
     * Removes books written by author in removeTerm.
     * 1. creates a copy of the books list and a counter
     * 2. loops through copy, casting the current object to BookEntry so that
     the authors can be obtained from the BookEntry getAuthors method
     method
     * 3. Within the loop, if the books copy contains a book by the target
     author, the book is removed from the actual books list, and the
     counter is increased
     * 4. Prints the number of books removed for an author
     * Input Parameters:
     * @param books contains books in the library
     * Exceptions
     * @throws NullPointerException if argumentInput is empty*/
    public void removeDataAuthor(List<BookEntry> books) {
        // 1.
        int counter = 0;
        List<BookEntry> booksCopy = new ArrayList<>(books);
        // 2.
        for (BookEntry entry : booksCopy) {
            BookEntry book = (BookEntry) entry;
            //3.
            if (Arrays.toString(book.getAuthors()).contains((String)removeTerm)) {
                books.remove(book);
                counter++;
            }
        }
        // 4.
        System.out.println(counter + " books removed for author: " + removeTerm);
    }
    /** Method removeDataTitle
     * Removes book with the title stored in removeTerm
     * 1. Assigns the string removedSuccesfully to output for instance that
     the title is not found
     * 2. Loops through each book in the library. If the title is found, it
     removes the title, reassigns the removedSuccessfully and ends the loop
     * 3. Prints that either the book has been removed or that
     the book was not found
     * Input Parameters:
     * @param books contains books in the library
     * Exceptions:
     * @throws NullPointerException if argumentInput is empty */
    public void removeDataTitle(List<BookEntry> books) {
        // 1.
        String removedSuccessfully = removeTerm + ": not found.";
        // 2.
        for (BookEntry book : books) {
            if (book.getTitle().equals(removeTerm)) {
                books.remove(book);
                removedSuccessfully = removeTerm + ": removed successfully.";
                break;
            }
        }
        // 3.
        System.out.println(removedSuccessfully);
    }
    // ----------------------------------------------------------- //
}