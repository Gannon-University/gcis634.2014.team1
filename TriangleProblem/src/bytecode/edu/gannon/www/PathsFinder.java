package bytecode.edu.gannon.www;
import java.util.ArrayList;

/**
 * @author hassanalrawi The main purpose of this class is to path all the
 *         possible paths of a Java Bytecode application The purpose of this
 *         class is to make it easier for the developer to display the possible
 *         paths of a JVM application.
 */
public class PathsFinder {

	private static ArrayList<ArrayList<String>> allPathsArray = new ArrayList<ArrayList<String>>();
	private static String[] fileContentsArrayGlobal;
	private static int mainArrayIndex = 0;

	/*
	 * This method input is a String array, The index of each element in the
	 * array is the line number in byte code, and the content of the lines are
	 * the instructions (commands) inside the Java Bytecode File.
	 * 
	 * The return of this method is two dimensional array, each array inside the
	 * array contains the line indexes of the paths.
	 */
	public static ArrayList<ArrayList<String>> allPathsFinder(
			String[] fileContentArray) {
		fileContentsArrayGlobal = fileContentArray;
		ArrayList<String> mainPathLine = new ArrayList<String>();

		for (int i = 0; i < fileContentArray.length; i++) {
			if (fileContentArray[i] == null) {
				continue;
			}

			String command = CommandsAndValuesConverter
					.getCommand(fileContentArray[i]);
			String value = CommandsAndValuesConverter
					.getValue(fileContentArray[i]);

			mainPathLine.add(String.valueOf(i));

			if (command.equalsIgnoreCase("if_icmplt")
					|| command.equalsIgnoreCase("if_icmpgt")
					|| command.equalsIgnoreCase("if_icmple")
					|| command.equalsIgnoreCase("if_icmpne")
					|| command.equalsIgnoreCase("if_icmpeq")
					|| command.equalsIgnoreCase("if_icmpge")) {
				singlePathFinder(mainPathLine, Integer.parseInt(value));

			} else if (command.equalsIgnoreCase("goto")) {
				i = Integer.parseInt(value) - 1;
			}

			else if (command.equalsIgnoreCase("ireturn")) {

				allPathsArray.add(mainArrayIndex,
						(ArrayList<String>) mainPathLine.clone());
				mainArrayIndex++;
				break;
			}

		}

		// System.out.println("The Number of paths is: " +
		// allPathsArray.size());
		return allPathsArray;
	}

	/*
	 * The purpose of this method is to find a single path in the JVM Bytecode
	 * file.
	 */
	private static void singlePathFinder(ArrayList<String> path, int index) {
		ArrayList<String> singlePath = new ArrayList<String>();
		singlePath = (ArrayList<String>) path.clone();
		for (int i = index; i < fileContentsArrayGlobal.length; i++) {
			if (fileContentsArrayGlobal[i] == null) {
				continue;
			}

			String command = CommandsAndValuesConverter
					.getCommand(fileContentsArrayGlobal[i]);
			String value = CommandsAndValuesConverter
					.getValue(fileContentsArrayGlobal[i]);
			singlePath.add(String.valueOf(i));

			if (command.equalsIgnoreCase("if_icmplt")
					|| command.equalsIgnoreCase("if_icmpgt")
					|| command.equalsIgnoreCase("if_icmple")
					|| command.equalsIgnoreCase("if_icmpne")
					|| command.equalsIgnoreCase("if_icmpeq")
					|| command.equalsIgnoreCase("if_icmpge")) {
				singlePathFinder(singlePath, Integer.parseInt(value));
			}

			else if (command.equalsIgnoreCase("goto")) {
				i = Integer.parseInt(value) - 1;
			}

			else if (command.equalsIgnoreCase("ireturn")) {
				allPathsArray.add(mainArrayIndex,
						(ArrayList<String>) singlePath.clone());
				mainArrayIndex++;
				singlePath.clear();
				break;
			}
		}
	}
}
