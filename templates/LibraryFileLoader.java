import java.awt.print.Book;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class responsible for loading
 * book data from file.
 */
public class LibraryFileLoader {
    /**
     * Contains all lines read from a book data file using
     * the loadFileContent method.
     *
     * This field can be null if loadFileContent was not called
     * for a valid Path yet.
     *
     * NOTE: Individual line entries do not include line breaks at the
     * end of each line.
     */
    private List<String> fileContent;
    /** Create a new loader. No file content has been loaded yet. */
    public LibraryFileLoader() {
        fileContent = null;
    }
    /**
     * Load all lines from the specified book data file and
     * save them for later parsing with the parseFileContent method.
     *
     * This method has to be called before the parseFileContent method
     * can be executed successfully.
     *
     * @param fileName file path with book data
     * @param true if book data could be loaded successfully, false otherwise
     * @throws NullPointerException if the given file name is null
     */
    public boolean loadFileContent(Path fileName) {
        Objects.requireNonNull(fileName, "Given filename must not be null.");
        boolean success = false;
        try {
            fileContent = Files.readAllLines(fileName);
            success = true;
        } catch (IOException | SecurityException e) {
            System.err.println("ERROR: Reading file content failed: " + e);
        }
        return success;
    }
    /**
     * Has file content been loaded already?
     * @return true if file content has been loaded already.
     */
    public boolean contentLoaded() {
        return fileContent != null;
    }
    /**
     * Parse file content loaded previously with the loadFileContent method.
     * @return newEntries parsed from the previously loaded book data or an empty list
     * if no book data has been loaded yet.
     * @throws NullPointerException if fileContent is null. If null, the error
     * is caught and the appropriate message is printed
     */
    public List<BookEntry> parseFileContent() {
        List<BookEntry> newEntries = new ArrayList<>();
        /** Takes row from csv file, then splits terms separated by commas into separate strings,
         * storing this in a string array.
         */
        try {
            Objects.requireNonNull(this.fileContent);
            for (int i = 1; i < this.fileContent.size(); i++) {
                String[] entryArray = fileContent.get(i).split(",");
                String[] authors;
                /** Splits authors into an array if there are multiple authors.
                 *authors is assigned to String array authors */
                if (entryArray[1].contains("-")) {
                    authors = entryArray[1].split("-");
                } else {
                    authors = new String[]{entryArray[1]};
                }
                /** Creates BookEntry using generated string array and author variable
                 * Each element of the array is cast to the appropriate type while the object is
                 * instantiated*/
                BookEntry currentEntry = new BookEntry(entryArray[0],
                    authors,
                    Float.parseFloat(entryArray[2]),
                    entryArray[3],
                    Integer.parseInt(entryArray[4]));
                newEntries.add(currentEntry);
            }
        } catch (Exception error) {
            System.err.println("ERROR: No content loaded before parsing.");
        }
        return newEntries;
    }
}