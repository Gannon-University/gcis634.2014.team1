package bytecode.edu.gannon.www;
import java.awt.List;

/**
 * @author hassanalrawi This class is responsible of building split blocks of
 *         the JVM bytecode. The input of this class method is a single array
 *         containing the JVM Instructions, The array indexes must be the line
 *         indexes in the JVM instructions, and the content of the is the JVM
 *         bytecode instructions.
 * 
 *         The purpose of this class is to help the developer visualize the code
 *         blocks in an easy manner.
 */
public class BlockBuilder {

	private final static String dashes = "=================";
	private static int blockNumberCounter = 0;
	private static List blocksList = new List();

	public static List blockBuilder(String[] fileContentArray) {
		addBlockText();
		for (int i = 0; i < fileContentArray.length; i++) {
			if (fileContentArray[i] == null) { 
				continue;
			}

			String command = CommandsAndValuesConverter
					.getCommand(fileContentArray[i]);
			// System.out.println(i + " " + command + " " + value);

			if (command.equals("if_icmplt")
					|| command.equals("if_icmpgt")
					|| command.equals("if_icmple")
					|| command.equals("if_icmpne")
					|| command.equals("if_icmpeq")
					|| command.equals("if_icmpge")) {
				addBlockText();
				blocksList.add(fileContentArray[i]);
				addBlockText();
			} else if (command.equals("ireturn")) {
				addBlockText();
				blocksList.add(fileContentArray[i]);
			} else if (command.equals("goto")) {
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
