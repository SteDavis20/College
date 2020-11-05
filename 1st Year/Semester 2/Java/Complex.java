package complex;

public class Complex {

	private double x;	// real part
	private double y;	// imaginary part
	
	public Complex() {
		x=0;
		y=0;
	}
	
	// it creates the complex object by setting the two fields to the passed values.
	public Complex(double realPart, double imaginaryPart) {
		x = realPart;
		y = imaginaryPart;
	}
	
	// This method will find the sum of the current complex number and the passed
	// complex number.The methods returns a new Complex number which is the sum of the two.
	// (x1+y1 i)+(x2+y2 i)=(x1+x2)+i (y1+y2)
	public static Complex add(Complex a, Complex b) {
		Complex product = new Complex();
		product.x = a.x + b.x;
		product.y = a.y + b.y;
		return product;
	}
	
	// This method will find the product of the current complex number and the passed
	// complex number. The methods returns a new Complex number which is the product of the two
	// (x1+y1 i) (x2+y2 i) = (x1x2 - y1y2) +i(x1y2 + y1x2)
	public static Complex multiply(Complex a, Complex b) {
		Complex product = new Complex();
		product.x = (a.x * b.x) - (a.y * b.y);
		product.y = (a.x * b.y) + (a.y * b.x);
		return product;
	}
	
	// (x1+y1 i)/(x2+y2 i) = ((x1x2 + y1y2) + i(y1x2 - x1y2))/([x2]^2+[y2]^2)
	public static Complex divide(Complex a, Complex b) {
		Complex product = new Complex();
		product.x = ((a.x * b.x) + (a.y * b.y)) / ((b.x)*(b.x) + (b.y)*(b.y));
		product.y = ((a.y * b.x) - (a.x * b.y)) / ((b.x)*(b.x) + (b.y)*(b.y));		
		return product;
	}
	
	public String toString() {
		String number = "(" + x + ", " + y + "i)";
		return number;
	}
	
}
