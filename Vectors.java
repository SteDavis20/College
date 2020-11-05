package vectors;

public class Vectors {
	public static double magnitudeOfVector(double[] vector) { // take in one array
		double magnitude = 0;
		double sum = 0;
		for (int index = 0; index<vector.length; index++) {
			sum += Math.pow(vector[index], 2); 	// vector[index] accesses the index in the array "vector"
			// Math.pow(index, 2); would be incorrect as this would simply just square the values 1, 2, 3
			// not the actual indexes of the vector
		}
		magnitude = Math.sqrt(sum); 
		return magnitude;
	}
	// Don't want to return a value
	public static void normaliseVector(double[] vector) { // take in one array
		double element = 0; // element or dimension
		double magnitude = magnitudeOfVector(vector); // vector is a reference to the array so don't use "[]"
		for (int index = 0; index<vector.length; index++) {
			element = vector[index] / magnitude;
			vector[index] = element;
		} 
	}
	public static double dotProductOfTwoVectors(double[] vectorOne, double[] vectorTwo) { 
		double dotProduct = 0;
		double product = 0;
		for (int index = 0; index<vectorOne.length; index++) {
			product = vectorOne[index] * vectorTwo[index];
			dotProduct += product;
		}
		return dotProduct;
	}
	public static double[] addTwoVectorsTogether(double[] vectorOne, double[] vectorTwo) {
		double[] sumVector = {};
		double dimension = 0;
		for (int index = 0; index<vectorOne.length; index++) {
			dimension = vectorOne[index] + vectorTwo[index];
			sumVector[index] = dimension;
		}
		return sumVector;
	}
}
