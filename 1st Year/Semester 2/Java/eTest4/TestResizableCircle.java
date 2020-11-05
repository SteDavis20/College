package eTest4;

public class TestResizableCircle {

	public static void main(String[] args) {

		ResizableCircle resizableCircle = new ResizableCircle(100.0);
		int percentage = -10;	// to reduce circle, use negative value, to increase, use positive
		resizableCircle.resize(percentage);
		System.out.println("Perimeter of the resized circle is: "+resizableCircle.getPerimeter());
		System.out.println("Area of the resized circle is: "+resizableCircle.getArea());
	}
}
