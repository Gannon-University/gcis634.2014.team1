package bytecode.edu.gannon.www;
// $codepro.audit.disable com.instantiations.assist.eclipse.analysis.audit.rule.effectivejava.alwaysOverridetoString.alwaysOverrideToString
/**
 * @author hassanalrawi This class provides a way to extract the JVM command
 *         from a string, and the following values of the instructions (line
 *         numbers, input values, etc.)
 * @version $Revision: 1.0 $
 */
public class CommandsAndValuesConverter {

	/**
	 * Field INSTRUCTIONS_LENGTH.
	 * (value is 2)
	 */
	private static final int INSTRUCTIONS_LENGTH = 2;

	/**
	 * Method getCommand.
	 * @param inputLine String
	
	 * @return String */
	public static String getCommand(String inputLine) {
		final String[] commandsAndValues = inputLine.split("\\s");
		final String command = commandsAndValues[0];
		return command;
	}

	/**
	 * Method getValue.
	 * @param inputLine String
	
	 * @return String */
	public static String getValue(String inputLine) {
		final String[] commandsAndValues = inputLine.split("\\s");
		if (commandsAndValues.length >= INSTRUCTIONS_LENGTH) {
			final String value = commandsAndValues[1];
			return value;
		} else {
			return " ";
		}
	}
}
