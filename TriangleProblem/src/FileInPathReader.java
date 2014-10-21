import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.util.ArrayList;


public class FileInPathReader implements FileInPathReaderInterface {

	private static BufferedReader reader;

	public String[] convertFileToText(String URL) throws IOException {
		reader = new BufferedReader(new FileReader(URL));

		String line = null;
		ArrayList<String> listArray = new ArrayList<String>();
		while ((line = reader.readLine()) != null) {
			listArray.add(line);
		}

		// Checking the file content
		String lastLine = listArray.get(listArray.size() - 1);
		String[] lastIndex = lastLine.split("\\s");

		// Adding the content of the file to an array
		String[] fileContentArray = new String[Integer.parseInt(lastIndex[0]) + 1];

		// Splitting the line code to indexes in the array, and the rest of the
		// line as a string
		for (String lines : listArray) {
			String[] parts = lines.split("\\s");

			if (parts.length == 2) {
				fileContentArray[Integer.parseInt(parts[0])] = parts[1];
			} else if (parts.length == 3) {
				fileContentArray[Integer.parseInt(parts[0])] = parts[1] + " "
						+ parts[2];
			} else if (parts.length == 4) {
				fileContentArray[Integer.parseInt(parts[0])] = parts[1] + " "
						+ parts[2] + " " + parts[3];
			}
		}
		
		return fileContentArray;
	}
		
}
