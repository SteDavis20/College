package complex;

public class ComplexDemo {

	public static void main(String[] args) {
		
		// Test default constructor
		Complex object = new Complex();		
		System.out.println("Default Constructor Test: " + object.toString());
		
		// Test parameterized contructor
		Complex object2 = new Complex(4,5);
		System.out.println("Parameterized Constructor Test: " + object2.toString());
	
		// Test add method
		Complex object3 = new Complex(5,3);
		Complex sum = object2.add(object2, object3);
		System.out.println("" + object2.toString() + " + " + object3.toString());
		System.out.println("Addition test: " + sum.toString());
		
		// Test multiply method
		Complex product = object2.multiply(object2, object3);
		System.out.println("Multiplication Test: "+product.toString());
		
		// Test divide method
		Complex divisionAnswer = object2.divide(object2, object3);
		System.out.println("Division Test: "+divisionAnswer.toString());
	}

}
