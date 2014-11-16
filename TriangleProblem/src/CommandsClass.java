

public class CommandsClass extends CommandsHandler {

	public static void iload() { // Loading Variables to the Array
		index = Integer.parseInt(value);
		stack.push(values[index]);
	}

	public static void iload_1() { // Loading Variables to the Array
		values[1] = Integer.parseInt(value);
		stack.push(values[1]);
	}

	public static void iload_2() {
		values[2] = Integer.parseInt(value);
		stack.push(values[2]);
	}

	public static void iload_3() {
		values[3] = Integer.parseInt(value);
		stack.push(values[3]);
	}

	public static void iconst_0() { // Pushing Values to the Stack
		stack.push(0);
	}

	public static void iconst_1() { // Pushing Values to the Stack
		stack.push(1);
	}

	public static void iconst_2() { // Pushing Values to the Stack
		stack.push(2);
	}

	public static void iconst_3() { // Pushing Values to the Stack
		stack.push(3);
	}

	public static void iconst_4() { // Pushing Values to the Stack
		stack.push(4);
	}

	public static void iconst_5() { // Pushing Values to the Stack
		stack.push(5);
	}

	public static void if_icmplt() {
		if (stack.pop() > stack.pop()) {
			i = Integer.parseInt(value) - 1;
		}
	}

	public static void if_icmpgt() {
		if (stack.pop() < stack.pop()) {
			i = Integer.parseInt(value) - 1;
		}
	}

	public static void if_icmple() {
		if (stack.pop() >= stack.pop()) {
			i = Integer.parseInt(value) - 1;
		}
	}

	public static void if_icmpne() {
		if (stack.pop() != stack.pop()) {
			i = Integer.parseInt(value) - 1;
		}

	}

	public static void if_icmpeq() {
		if (stack.pop() == stack.pop()) {
			i = Integer.parseInt(value) - 1;
		}
	}

	public static void if_icmpge() {
		if (stack.pop() <= stack.pop()) {
			i = Integer.parseInt(value) - 1;
		}
	}

	public static int ireturn() {
		if (stack.lastElement()== 0)
		{
			System.out.println("Not a triangle.");
		}
		else if (stack.lastElement()== 1)
		{
			System.out.println("Equilateral triangle.");
		}
		else if (stack.lastElement()== 2)
		{
			System.out.println("Isosceles triangle.");
		}
		else if (stack.lastElement()== 3)
		{
			System.out.println("Scalene triangle.");
		}
		
		System.exit(0);
		return stack.pop();	
	}

	public static void bipush() {
		stack.push(Integer.parseInt(value));
	}

	public static void iadd() {
		stack.push(stack.pop() + stack.pop());
	}

	public static void istore_0() {
		values[0] = stack.pop();
	}

	public static void istore_1() {
		values[1] = stack.pop();
	}

	public static void istore_2() {
		values[2] = stack.pop();
	}

	public static void istore_3() {
		values[3] = stack.pop();
	}

	public static void istore() {
		values[index] = stack.pop();
	}
	
	public static void goTo() {
		i = Integer.parseInt(value) -1;
	}
	
}
