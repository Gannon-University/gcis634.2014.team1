
public class Triangle {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String temp=triangleBuilder(50,50,50);
		System.out.print(temp);

	}
//git bash test
	public static String triangleBuilder(int a, int b, int c) {
		String triangleName="Not a Triangle";//Default to not a triangle
		if(a < b + c&&b < a + c&&c < a + b&&a!=0&&b!=0&&c!=0){
			if(a==b&&b==c){//can't avoid if statement,a=b=c
				triangleName="Equilateral";//Equilateral
			}else if(a==b||a==c||b==c&&!(a==b&&b==c))
			{//can't avoid if statement,a=b or a=c or b=c and no a=b=c
				triangleName="Isosceles";//Isosceles			
			}else{
				triangleName="Scalene";//Scalene
			}
		}else{
			triangleName="Not a Triangle";
		}
		return triangleName;
	}

}
