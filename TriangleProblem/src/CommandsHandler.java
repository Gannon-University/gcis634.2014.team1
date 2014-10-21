

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Stack;



public class CommandsHandler {

	protected static int[] values = new int[10];
	protected static Stack<Integer> stack = new Stack<Integer>();
	protected static int index = 0;
	protected static int i;
	protected static String command;
	protected static String value;
	public static void doCommand(String commandLines[]) throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		for (i = 0; i < commandLines.length; i++) {
			if (commandLines[i] == null) { // if the index is null continue, i
											// used this to avoid empty indexes
											// in the array
				
				continue;
			}
		
			command = CommandsAndValuesConverter.getCommand(commandLines[i]);
//			System.out.println(command);
			if (command.equals("goto")) {
				command = "goTo";
			}
			
			value = CommandsAndValuesConverter.getValue(commandLines[i]);
		
			//For Testing Purposes
			
			if (value.equalsIgnoreCase("[month]")) {
				value = "4";
			} else if (value.equalsIgnoreCase("[day]")) {
				value = "16";
			} else if (value.equalsIgnoreCase("[result]")) {
				value = "25";
			}
			
//			System.out.println(i + " " + command + " " + value);
//			System.out.println(i);
			
			Method commandMethod = CommandsClass.class.getMethod(command);
			commandMethod.invoke(new CommandsClass());
		
		}
	}
	
}
