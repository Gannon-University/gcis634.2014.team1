

public class CommandsAndValuesConverter {

	public static String getCommand(String inputLine) {
		String[] commandsAndValues = inputLine.split("\\s");
		String command = commandsAndValues[0];
		return command;
	}
	
	public static String getValue(String inputLine) {
		String[] commandsAndValues = inputLine.split("\\s");
		if (commandsAndValues.length >= 2) {
			String value = commandsAndValues[1];
			return value;
		} else {
			return " ";
		}
	}
}
