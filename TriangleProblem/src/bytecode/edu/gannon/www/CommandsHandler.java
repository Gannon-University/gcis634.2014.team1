package bytecode.edu.gannon.www;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Stack;

/**
 * @author hassanalrawi This class is responsible to run the JVM instructions,
 *         and to present the user with the output of the JVM instructions
 */
public class CommandsHandler {

	protected static int[] values = new int[10];
	protected static Stack<Integer> stack = new Stack<Integer>();
	protected static int index = 0;
	protected static int i;
	protected static String command;
	protected static String value;

	public static void doCommand(String commandLines[])
			throws ClassNotFoundException, SecurityException,
			NoSuchMethodException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException {
		for (i = 0; i < commandLines.length; i++) {
			// if the index is null continue, i used this to avoid empty indexes
			// in the array
			if (commandLines[i] == null) {
				continue;
			}

			command = CommandsAndValuesConverter.getCommand(commandLines[i]);
			// System.out.println(command);
			if (command.equals("goto")) {
				command = "goTo";
			}

			value = CommandsAndValuesConverter.getValue(commandLines[i]);

			// For Testing Purposes

			if (value.equalsIgnoreCase("[a]")) {
				value = "7";
			} else if (value.equalsIgnoreCase("[b]")) {
				value = "6";
			} else if (value.equalsIgnoreCase("[c]")) {
				value = "6";
			}
			
			Method commandMethod = CommandsClass.class.getMethod(command);
			commandMethod.invoke(new CommandsClass());
		}
	}
}
