package zodicSign;

import java.util.ArrayList;

public class PathsFinder {

	private static ArrayList<ArrayList<String>> allPathsArray = new ArrayList<ArrayList<String>>();
	private static String[] fileContentsArrayGlobal;
	private static int mainArrayIndex = 0;
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
				SinglePathFinder(mainPathLine, Integer.parseInt(value));

			} else if (command.equalsIgnoreCase("goto")) {
				i = Integer.parseInt(value) - 1;
			}

			else if (command.equalsIgnoreCase("ireturn")) {
				
				allPathsArray.add(mainArrayIndex, (ArrayList<String>) mainPathLine.clone());
				mainArrayIndex++;
				break;
			}

		}

		System.out.println("The Number of paths is: " + allPathsArray.size());
		return allPathsArray;
	}

	private static void SinglePathFinder(ArrayList<String> path, int index) {
		ArrayList<String> singlePath = new ArrayList<String>();

		singlePath = (ArrayList<String>) path.clone();
		
		
		for (int i = index; i < fileContentsArrayGlobal.length; i++) {
			if (fileContentsArrayGlobal[i] == null) {
				continue;
			}

			
			String command = CommandsAndValuesConverter.getCommand(fileContentsArrayGlobal[i]);
			String value = CommandsAndValuesConverter.getValue(fileContentsArrayGlobal[i]);
			singlePath.add(String.valueOf(i));

			if (command.equalsIgnoreCase("if_icmplt")
					|| command.equalsIgnoreCase("if_icmpgt")
					|| command.equalsIgnoreCase("if_icmple")
					|| command.equalsIgnoreCase("if_icmpne")
					|| command.equalsIgnoreCase("if_icmpeq")
					|| command.equalsIgnoreCase("if_icmpge")) {

				SinglePathFinder(singlePath, Integer.parseInt(value));

			}

			else if (command.equalsIgnoreCase("goto")) {
				i = Integer.parseInt(value) - 1;
			}

			else if (command.equalsIgnoreCase("ireturn")) {
				allPathsArray.add(mainArrayIndex, (ArrayList<String>) singlePath.clone());;
				mainArrayIndex++;
				singlePath.clear();
				break;
			}

		}

	}

}
