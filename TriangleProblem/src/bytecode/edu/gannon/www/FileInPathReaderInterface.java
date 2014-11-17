package bytecode.edu.gannon.www;
import java.io.IOException;

/**
 * @author hassanalrawi An interface to Unify the URL input and the output of
 *         the classes that extends it, the purpose of this interface is to make
 *         sure every class that tries to input data to the application must
 *         follow the same rules as the other file input classes.
 * @version $Revision: 1.0 $
 */

public interface FileInPathReaderInterface {
	/**
	 * Method convertFileToText.
	 * @param fileURL String
	 * @return String[]
	 * @throws IOException
	 */
	public String[] convertFileToText(String fileURL) throws IOException;
}
