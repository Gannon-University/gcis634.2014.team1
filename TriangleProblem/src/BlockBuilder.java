import java.util.ArrayList;

public class BlockBuilder {

	private static String dashes = "================="; 
	private static int blockNumberCounter = 0;
	private static ArrayList<String> blocksList = new ArrayList<String>();
	
	public static ArrayList<String> blockBuilder (String[] fileContentArray) {
		addBlockText();
		for (int i = 0; i < fileContentArray.length; i++) {
			if (fileContentArray[i] == null) { // if the index is null continue, i
											// used this to avoid empty indexes
											// in the array
				continue;
			}

			String command = CommandsAndValuesConverter.getCommand(fileContentArray[i]);
//			System.out.println(i + " " + command + " " + value);

			if (command.equalsIgnoreCase("if_icmplt")
					|| command.equalsIgnoreCase("if_icmpgt")
					|| command.equalsIgnoreCase("if_icmple")
					|| command.equalsIgnoreCase("if_icmpne")
					|| command.equalsIgnoreCase("if_icmpeq")
					|| command.equalsIgnoreCase("if_icmpge")) {
				addBlockText();
				blocksList.add(fileContentArray[i]);
				addBlockText();
			} else if (command.equalsIgnoreCase("ireturn")) {
				addBlockText();
				blocksList.add(fileContentArray[i]);
			} else if (command.equalsIgnoreCase("goto")) {
				addBlockText();
				blocksList.add(fileContentArray[i]);
				addBlockText();
			} else {
				blocksList.add(fileContentArray[i]);
			}
		}

		return blocksList;
	}

	
	private static void addBlockText() {
		blocksList.add(dashes);
		blocksList.add("Block: " + String.valueOf(blockNumberCounter));
		blocksList.add(dashes);
		blockNumberCounter++;
	}
}
