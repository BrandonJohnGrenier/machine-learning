package ai.brandon.ulr;

import java.math.BigDecimal;

import ai.brandon.common.functions.LinearFunction;
import ai.brandon.common.model.TrainingSet;
import ai.brandon.common.util.ErrorFunction;

public class Algorithm<T> {

	private TrainingSet<T> trainingSet;

	private static final double TOLERANCE = 1E-11;

	public Algorithm(TrainingSet<T> trainingSet) {
		this.trainingSet = trainingSet;
	}

	public void run() {
		BigDecimal theta_zero = new BigDecimal(0.5);
		BigDecimal theta_one = new BigDecimal(0.5);
		BigDecimal delta_one = new BigDecimal(0);
		BigDecimal delta_zero = new BigDecimal(0);

		Double alpha = 0.905;
		Double tolerance = 1000.0;

		BigDecimal temp0 = new BigDecimal(0);
		BigDecimal temp1 = new BigDecimal(0);

		int i = 0;

		do {
			i++;
			if (i % 10 == 0) {
				System.out.println("theta_0 = " + theta_zero.doubleValue());
				System.out.println("theta_1 = " + theta_one.doubleValue());
				// System.out.println("");
			}

			temp0 = calculateThetaZero(theta_zero, theta_one, alpha);
			temp1 = calculateThetaOne(theta_zero, theta_one, alpha);

			// System.out.println("temp_0 = " + temp0 + ", temp_1 = " + temp1);

			delta_zero = theta_zero.subtract(temp0);
			delta_one = theta_one.subtract(temp1);
			// System.out.println("delta_0 = " + delta_zero);
			// System.out.println("delta_1 = " + delta_one);

			theta_zero = temp0;
			theta_one = temp1;

			tolerance = Math.abs(delta_zero.doubleValue() + delta_one.doubleValue());

			if (i % 10 == 0) {
				System.out.println("tolerance: " + tolerance);
				System.out.println("--------------------------------------------");
			}
			// System.out.println("");
		}
		while (tolerance > TOLERANCE);

	}

	public BigDecimal calculateThetaZero(BigDecimal theta_zero, BigDecimal theta_one, Double alpha) {
		LinearFunction<T> function = new LinearFunction<T>(theta_zero, theta_one);
		BigDecimal vector = ErrorFunction.averageOfErrors(function, trainingSet).multiply(new BigDecimal(alpha.toString()));
		return new BigDecimal(theta_zero.toString()).subtract(vector);
	}

	public BigDecimal calculateThetaOne(BigDecimal theta_zero, BigDecimal theta_one, Double alpha) {
		LinearFunction<T> function = new LinearFunction<T>(theta_zero, theta_one);
		BigDecimal vector = ErrorFunction.averageOfErrors2(function, trainingSet).multiply(new BigDecimal(alpha.toString()));
		return new BigDecimal(theta_one.toString()).subtract(vector);
	}

}
