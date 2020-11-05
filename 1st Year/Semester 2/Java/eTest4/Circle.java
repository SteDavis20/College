package eTest4;


public class Circle implements GeometricObject {

	public static final double TWO = 2.0;
	public static final double ONE_HUNDRED = 100.0;
	public static final double ONE = 1.0;

	protected double radius = 1.0;

	public Circle(double radius) {
		this.radius=radius;
	}

	@Override
	public double getPerimeter() {
		double perimeter = TWO * Math.PI * radius;	// formula for radius of a circle
		perimeter = Math.round(perimeter*ONE_HUNDRED)/ONE_HUNDRED;	// round to 2 decimal places
		return perimeter;
	}

	@Override
	public double getArea() {
		double area = Math.PI * (radius*radius);	// formula for area of a circle
		area = Math.round(area*ONE_HUNDRED)/ONE_HUNDRED;	// round to 2 decimal places
		return area;
	}
}
