package zodicSign;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

public class Interpreter {
	private static int solution;
	private static int[] values;
	private static int index;

	private static Stack<Integer> stack;
	private static ArrayList<Integer> blockList;
	private static String[] contentArray2;
	private static BufferedReader reader;

	public static void interpeter() throws IOException {

		index = 0;
		values = new int[10];
		stack = new Stack<Integer>();
		reader = new BufferedReader(new FileReader(
				"/Users/hassanalrawi/Desktop/zodicSign.txt"));
		String line = null;
		ArrayList<String> listArray = new ArrayList<String>();
		while ((line = reader.readLine()) != null) {
			listArray.add(line);
		}

		// Defining Block Lines
		blockList = new ArrayList<Integer>();
		blockList.add(0);

		// Checking the file content
		String lastLine = listArray.get(listArray.size() - 1);
		String[] lastIndex = lastLine.split("\\s");

		// Adding the content of the file to an array
		contentArray2 = new String[Integer.parseInt(lastIndex[0]) + 1];

		// Splitting the line code to indexes in the array, and the rest of the
		// line as a string
		for (String lines : listArray) {
			String[] parts = lines.split("\\s");

			if (parts.length == 2) {
				contentArray2[Integer.parseInt(parts[0])] = parts[1];
			} else if (parts.length == 3) {
				contentArray2[Integer.parseInt(parts[0])] = parts[1] + " "
						+ parts[2];
			} else if (parts.length == 4) {
				contentArray2[Integer.parseInt(parts[0])] = parts[1] + " "
						+ parts[2] + " " + parts[3];
			}

		}

		splittingCommandsAndInput();

	} // End of Main

	// Splitting the commands and the input
	private static void splittingCommandsAndInput() {
		for (int i = 0; i < contentArray2.length; i++) {
			if (contentArray2[i] == null) { // if the index is null continue, i
											// used this to avoid empty indexes
											// in the array
				continue;
			}

			String[] commandsAndVlaues = contentArray2[i].split("\\s");
			String command = commandsAndVlaues[0];

			String value = " ";
			if (commandsAndVlaues.length > 1) {
				value = commandsAndVlaues[1];
			}

			// Printing the command and the index and whatever value after the
			// command, just to make sure my code is working correctly
			System.out.println(i + " " + command + " " + value);

			// Testing Block, Since i'm reading from a file, i'm changing the
			// values of a,b,c to int for testing
			if (value.equalsIgnoreCase("[result]")) {
				value = "0";
			} else if (value.equalsIgnoreCase("[month]")) {
				value = "6";
			} else if (value.equalsIgnoreCase("[day]")) {
				value = "25";
			}

			// If statements for every command in the homework, i wanted to use
			// Reflection to run the functions, but for simplicity i avoided
			// that
			if (command.equalsIgnoreCase("iload_1")) {
				values[1] = Integer.parseInt(value);
				iload_1();
			} else if (command.equalsIgnoreCase("iload_2")) {
				values[2] = Integer.parseInt(value);
				iload_2();
			} else if (command.equalsIgnoreCase("iload_3")) {
				values[3] = Integer.parseInt(value);
				iload_3();
			} else if (command.equalsIgnoreCase("iload")) {
				index = Integer.parseInt(value);
				iload();
			} else if (command.equalsIgnoreCase("iconst_0")) {
				iconst_0();
			} else if (command.equalsIgnoreCase("iconst_1")) {
				iconst_1();
			} else if (command.equalsIgnoreCase("iconst_2")) {
				iconst_2();
			} else if (command.equalsIgnoreCase("iconst_3")) {
				iconst_3();
			} else if (command.equalsIgnoreCase("iconst_4")) {
				iconst_4();
			} else if (command.equalsIgnoreCase("iconst_5")) {
				iconst_5();
			} else if (command.equalsIgnoreCase("if_icmplt")) {
				if (if_icmplt()) {
					i = Integer.parseInt(value) - 1;
				}
			} else if (command.equalsIgnoreCase("if_icmpgt")) {
				if (if_icmpgt()) {
					i = Integer.parseInt(value) - 1;
				}
			} else if (command.equalsIgnoreCase("if_icmple")) {
				if (if_icmple()) {
					i = Integer.parseInt(value) - 1;
				}
			} else if (command.equalsIgnoreCase("if_icmpne")) {
				if (if_icmpne()) {
					i = Integer.parseInt(value) - 1;
				}
			} else if (command.equalsIgnoreCase("if_icmpge")) {
				if (if_icmpge()) {
					i = Integer.parseInt(value) - 1;
				}
			} else if (command.equalsIgnoreCase("if_icmpeq")) {
				if (if_icmpeq()) {
					i = Integer.parseInt(value) - 1;
				}
			} else if (command.equalsIgnoreCase("goto")) {
				i = Integer.parseInt(value) - 1;
			} else if (command.equalsIgnoreCase("ireturn")) {
				solution = ireturn();
				break;
			} else if (command.equalsIgnoreCase("bipush")) {
				bipush(Integer.parseInt(value));
			} else if (command.equalsIgnoreCase("iadd")) {
				iadd();
			} else if (command.equalsIgnoreCase("istore_0")) {
				istore_0();
			} else if (command.equalsIgnoreCase("istore_1")) {
				istore_1();
			} else if (command.equalsIgnoreCase("istore_2")) {
				istore_2();
			} else if (command.equalsIgnoreCase("istore_3")) {
				istore_3();
			} else if (command.equalsIgnoreCase("istore")) {
				index = Integer.parseInt(value);
				istore();
			}

		}
	}

	// Below are the methods of every command in the file
	private static void iload() { // Loading Variables to the Array
		stack.push(values[index]);
	}

	public static void iload_1() { // Loading Variables to the Array
		stack.push(values[1]);
	}

	public static void iload_2() {
		stack.push(values[2]);
	}

	public static void iload_3() {
		stack.push(values[3]);
	}

	private static void iconst_0() { // Pushing Values to the Stack
		stack.push(0);
	}

	private static void iconst_1() { // Pushing Values to the Stack
		stack.push(1);
	}

	private static void iconst_2() { // Pushing Values to the Stack
		stack.push(2);
	}

	private static void iconst_3() { // Pushing Values to the Stack
		stack.push(3);
	}

	private static void iconst_4() { // Pushing Values to the Stack
		stack.push(4);
	}

	private static void iconst_5() { // Pushing Values to the Stack
		stack.push(5);
	}

	private static boolean if_icmplt() {
		if (stack.pop() > stack.pop()) {
			return true;
		}
		return false;
	}

	private static boolean if_icmpgt() {
		if (stack.pop() < stack.pop()) {
			return true;
		}
		return false;
	}

	private static boolean if_icmple() {
		if (stack.pop() >= stack.pop()) {
			return true;
		}
		return false;
	}

	private static boolean if_icmpne() {
		if (stack.pop() != stack.pop()) {
			return true;
		}
		return false;
	}

	private static boolean if_icmpeq() {
		if (stack.pop() == stack.pop()) {
			return true;
		}
		return false;
	}

	private static boolean if_icmpge() {
		if (stack.pop() <= stack.pop()) {
			return true;
		}
		return false;
	}

	private static int ireturn() {
		return stack.pop();
	}

	private static void bipush(int a) {
		stack.push(a);
	}

	private static void iadd() {
		stack.push(stack.pop() + stack.pop());
	}

	private static void istore_0() {
		values[0] = stack.pop();
	}

	private static void istore_1() {
		values[1] = stack.pop();
	}

	private static void istore_2() {
		values[2] = stack.pop();
	}

	private static void istore_3() {
		values[3] = stack.pop();
	}

	private static void istore() {
		values[index] = stack.pop();
	}
}
