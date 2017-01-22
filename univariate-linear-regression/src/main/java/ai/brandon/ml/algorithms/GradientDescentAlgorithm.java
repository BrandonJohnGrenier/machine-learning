package ai.brandon.ml.algorithms;

import java.math.BigDecimal;

import ai.brandon.ml.common.ErrorFunction;
import ai.brandon.ml.functions.LinearFunction;
import ai.brandon.ml.functions.SquaredErrorCostFunction;
import ai.brandon.ml.model.TrainingSet;

public class GradientDescentAlgorithm<T> {

	private final TrainingSet<T> set;
	private final SquaredErrorCostFunction<T> costFunction;

	private Double tolerance = 1E-9;
	private Double alpha = 0.100;

	public GradientDescentAlgorithm(TrainingSet<T> set) {
		this.set = set;
		this.costFunction = new SquaredErrorCostFunction<>(this.set);
	}

	public LinearFunction<T> run() {
		BigDecimal theta0 = new BigDecimal(0.0);
		BigDecimal tempTheta0 = new BigDecimal(0);

		BigDecimal theta1 = new BigDecimal(0.0);
		BigDecimal tempTheta1 = new BigDecimal(0);

		BigDecimal cost = new BigDecimal(100);

		while (cost.doubleValue() > tolerance) {
			cost = costFunction.at(theta0, theta1);
			tempTheta0 = calculateThetaZero(theta0, theta1, alpha);
			tempTheta1 = calculateThetaOne(theta0, theta1, alpha);

			BigDecimal newCost = costFunction.at(tempTheta0, tempTheta1);

			alpha = (newCost.doubleValue() > cost.doubleValue()) ? alpha / 2 : alpha + 0.01;

			theta0 = tempTheta0;
			theta1 = tempTheta1;
		}

		return new LinearFunction<T>(theta0, theta1);
	}

	public BigDecimal calculateThetaZero(BigDecimal theta0, BigDecimal theta1, Double alpha) {
		LinearFunction<T> function = new LinearFunction<T>(theta0, theta1);
		BigDecimal vector = ErrorFunction.averageOfErrors(function, set).multiply(new BigDecimal(alpha.toString()));
		return new BigDecimal(theta0.toString()).subtract(vector);
	}

	public BigDecimal calculateThetaOne(BigDecimal theta0, BigDecimal theta1, Double alpha) {
		LinearFunction<T> function = new LinearFunction<T>(theta0, theta1);
		BigDecimal vector = ErrorFunction.averageOfErrors2(function, set).multiply(new BigDecimal(alpha.toString()));
		return new BigDecimal(theta1.toString()).subtract(vector);
	}

}
