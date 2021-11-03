import java.util.*;
import java.util.stream.Collectors;

/**
 * Subclass of superclass Library Command
 * Designed for outputting books that have been group in ascending order
 * by title or author
 */
public class GroupCmd extends LibraryCommand {
    /** Initialises groupType.
     * This is used to store whether the user is grouping by
     * "AUTHOR" or "TITLE */
    private String groupType;
    /** Method GroupCmd
     * Instantiates RemoveCmd object  with GROUP
     from Command type enum and argumentInput
     * Input Parameters
     * @param argumentInput stores string from user input following "GROUP"
     */
    public GroupCmd(String argumentInput) {
        super(CommandType.GROUP, argumentInput);
    }
    @Override//parseArguments
    /** Checks if argument given is valid
     * 1. Checks if argument is Null and reports error if it is.
     * 2. Assigns the boolean inputValid false for the input validity, and the
     input string is converted to lowercase and stored in formattedInput
     * 3. If formattedInput is equal to "author" or "title", formattedInput
     is stored in the variable groupType initialised above, and validInput
     is set to true, indicating that the input is valid
     * 4. Returns validity of argument via inputValid
     * Input Parameters
     * @param argumentInput stores string from user input following "GROUP"
     * Output parameters:
     * @return boolean validInput used for verification of inputArgument
     * Exceptions
     * @throws NullPointerException if argumentInput is empty*/
    protected boolean parseArguments(String argumentInput) {
        // 1.
        Objects.requireNonNull(argumentInput);
        // 2.
        boolean validInput = false;
        String formattedArgument = argumentInput.toLowerCase();
        // 3.
        if (formattedArgument.equals("author")|| formattedArgument.equals("title")) {
            groupType = argumentInput;
            validInput = true;
        }
        return validInput;
    }
    @Override//execute
    /** Method execute
     *  Executes GroupCmd Command on provided title/author
     * 1. Checks if data is null
     * 2. Checks if there are any books in the library. If is empty,
     the appropriate message will be printed, if not then the else code
     run.
     * 3. Checks if the user is removing a title or author, and runs the
     appropriate grouping function
     * Input Parameters:
     * @param data contains the library data
     * Exceptions:
     * @throws NullPointerException if argumentInput is empty*/
    public void execute(LibraryData data) {
        // 1.
        Objects.requireNonNull(data);
        // 2.
        if (data.getBookData().isEmpty()) {
            System.out.println("The library has no book entries.");
        } else {
            if (groupType.toLowerCase().equals("title")) {
                groupByTitle(data.getBookData());
            } else if (groupType.toLowerCase().equals("author")) {
                groupByAuthor(data.getBookData());
            }
        }
    }
    // -------------------- Grouping Functions -------------------- //
    /** Method groupByAuthor
     * Groups the books in the library in ascending order by author
     * 1. Prints "Grouped data by AUTHOR"
     * 2. Gets list of authors in library in ascending order
     * 3. Loops through authorsList
     * 4. Using the stream function of ArrayLists, the list of type BookEntry,
     booksFound, is assigned to all the books found that were written by
     the given author
     * 5. Prints out the Author and then using a loop, prints the contents of
     booksFound hence printing the books written by the given author.
     * Input Parameters:
     * @param books
     * Exceptions:
     * @throws NullPointerException if argumentInput is empty
     * */
    public void groupByAuthor(List<BookEntry> books) {
        // 1.
        System.out.println("Grouped data by AUTHOR");
        // 2.
        ArrayList<String> authorsList = getAuthors(books);
        // 3.
        for (String author : authorsList) {
            // 4.
            List<BookEntry> booksFound = books.stream()
                                             .filter(book -> (Arrays.toString(book.getAuthors())).contains(author))
                                             .collect(Collectors.toList());
            // 5.
            System.out.println ("## " + author);
            for (BookEntry book : booksFound) {
                System.out.println("\t" + book.getTitle());
            }
        }
    }
    /** Method getAuthors
     * Gets a list of all the authors in the library
     * 1. Creates a new list of Strings called authorsList
     * 2. Loops through each book in the library, and adds their authors
     to authorsList
     * 3. Sorts, removes duplicates, casts it to type ArrayList and returns
     authorsList
     * Input Parameters:
     * @param books list of book entries obtained from library
     * Output Parameters:
     * @return String Arraylist authorlist which contains all authors
    with books in the library in ascending order
     */
    public ArrayList<String> getAuthors (List<BookEntry> books) {
        // 1.
        ArrayList<String> authorsList = new ArrayList<>();
        // 2.
        for (BookEntry book : books) {
            authorsList.addAll(Arrays.asList(book.getAuthors()));
        }
        // 3.
        authorsList.sort(Comparator.comparing(String::toString));
        return (ArrayList<String>) authorsList.stream().distinct().collect(Collectors.toList());
    }
    public void groupByTitle (List<BookEntry> books) {
        System.out.println("Grouped data by TITLE");
        /**
         * Loops through each letter of the alphabet
         * Creates a list of all the books found beginning with the current
         letter
         * If the following list of books found is not empty, the program will
         print the letter with the books beginning with the letter from
         the list*/
        for (int i = 65; i < 90; i++) {
            String strI = String.valueOf((char) i);
            List<BookEntry> booksFound = (books.stream()
                                              .filter(book -> book.getTitle().toUpperCase().startsWith(strI))
                                              .collect(Collectors.toList()));
            if (!booksFound.isEmpty()) {
                System.out.println("## " + strI);
                for (BookEntry book : booksFound) {
                    System.out.println("\t" + book.getTitle());
                }
            }
        }
        /**
         * Uses checkDigit function to check if a book begins with a digit.
         If true, the function will print the heading "[0-9] and execute the
         following code:
         * Loops through each number from 0-9
         * Creates a list of all the books found beginning with the current
         number
         * If the following list of books found is not empty, the program will
         print the letter with the books beginning with the letter from
         the list*/
        if (checkDigit(books)) {
            System.out.println("## [0-9]");
            for (int i = 0; i < 10; i++) {
                String strInt = String.valueOf(i);
                List<BookEntry> booksFound = (books.stream()
                                                  .filter(book -> book.getTitle().toUpperCase().startsWith(strInt))
                                                  .collect(Collectors.toList()));
                if (!booksFound.isEmpty()) {
                    for (BookEntry book : booksFound) {
                        System.out.println("\t" + book.getTitle());
                    }
                }
            }
        }
    }
    /**
     *
     * @param books contains books from library
     * @return boolean for if a book begins with a number
     */
    public boolean checkDigit (List<BookEntry> books) {
        boolean digitFound = false;
        for (int i = 0; i < 10; i++) {
            if (digitFound(books, i)) {
                digitFound = true;
                break;
            }
        }
        return digitFound;
    }
    public boolean digitFound (List<BookEntry> books, int i) {
        boolean found = false;
        String strI = String.valueOf(i);
        for (BookEntry book : books) {
            if (book.getTitle().toUpperCase().startsWith(strI)) {
                found = true;
                break;
            }
        }
        return found;
    }
}