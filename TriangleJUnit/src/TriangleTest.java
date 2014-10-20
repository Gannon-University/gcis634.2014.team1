import static org.junit.Assert.*;

import org.junit.Test;


public class TriangleTest {

	@Test
	public void test1() {
		String expected="Equilateral";
		assertEquals(expected, Triangle.triangleBuilder(1,1,1));
	}

}
