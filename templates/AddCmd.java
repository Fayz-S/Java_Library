import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * Subclass of superclass Library Command
 * Designed for adding books to Library
 */
public class AddCmd extends LibraryCommand {
    /** Initialises Path entryPath.
     * This is used to store the path received from the user input */
    private Path entryPath;
    /** Method RemoveCmd
     * Instantiates AddCmd object  with ADD
     from Command type enum and argumentInput
     * Input Parameters
     * @param argumentInput stores string from user input following "ADD"
     */
    public AddCmd(String argumentInput) {
        super(CommandType.ADD, argumentInput);
    }
    @Override//parseArguments
    /** Method parseArguments
     * Verifies argumentInput is valid before executing command
     * 1. Checks if argument is Null and reports error if it is.
     * 2. Assigns the boolean inputValid false for the input validity,
     * 3. Checks if the user input is not blank. If it is, validEntry will
     remain false
     * 4. Assigns the Path entryPath declared earlier to the user input
     * 5. Checks if the ending of the path is equal to ".csv". If true,
     the function will reassign inputValid to true, confirming the validity
     the user input.
     * Input Parameters:
     * @param argumentInput contains input from user
     * Output Parameters:
     * @return boolean validInput used for verification of inputArgument
     * Exceptions:
     * @throws NullPointerException if argumentInput is empty*/
    protected boolean parseArguments(String argumentInput) {
        // 1.
        Objects.requireNonNull(argumentInput);
        // 2.
        boolean validEntry = false;
        // 3.
        if (!argumentInput.isBlank()) {
            // 4.
            entryPath = Paths.get(argumentInput.replaceAll(" ", ""));
            // 5.
            validEntry = argumentInput.substring(argumentInput.length() - 4).equals(".csv");
        }
        return validEntry;
    }
    @Override//execute
    /** Method execute
     * Executes AddCmd Command with provided path
     * 1. Checks if the library data provided is null
     * 2. Calls loadData function from the Library data class, which extracts data
     from the csv file obtained via the entryPath and adds it to the library
     * Input Parameters:
     * @param data contains all the data from the library
     * Exceptions:
     * @throws NullPointerException if data is null
     */
    public void execute(LibraryData data) {
        Objects.requireNonNull(data, "Given argument input must not be null.");
        data.loadData(entryPath);
    }
}