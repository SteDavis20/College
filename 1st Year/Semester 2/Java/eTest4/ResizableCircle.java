package eTest4;

public class ResizableCircle extends Circle implements Resizable{

	public ResizableCircle(double radius) {
		super(radius);
	}
	
	@Override
	public void resize(int percent) {
		double decimalPercent = percent;	// necessary to turn percent into a fraction
		decimalPercent/=ONE_HUNDRED;				// get percent in decimal form
		radius *= (ONE+decimalPercent);
	}
}
