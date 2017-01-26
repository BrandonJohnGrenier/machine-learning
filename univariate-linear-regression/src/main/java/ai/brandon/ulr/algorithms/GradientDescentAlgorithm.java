package ai.brandon.ulr.algorithms;

import java.math.BigDecimal;

import ai.brandon.ulr.common.ErrorFunction;
import ai.brandon.ulr.functions.LinearFunction;
import ai.brandon.ulr.functions.SquaredErrorCostFunction;
import ai.brandon.ulr.model.TrainingSet;

public class GradientDescentAlgorithm<T> {

	private final TrainingSet<T> set;
	private final SquaredErrorCostFunction<T> costFunction;

	private Double tolerance = 1E-9;
	private Double alpha = 0.100;

	public GradientDescentAlgorithm(TrainingSet<T> set) {
		this.set = set;
		this.costFunction = new SquaredErrorCostFunction<>(this.set);
	}

	public Double getTolerance() {
		return tolerance;
	}

	public void setTolerance(Double tolerance) {
		this.tolerance = tolerance;
	}

	public Double getAlpha() {
		return alpha;
	}

	public void setAlpha(Double alpha) {
		this.alpha = alpha;
	}

	public LinearFunction<T> run() {
		BigDecimal theta0 = new BigDecimal(0.0), tempTheta0 = new BigDecimal(0.0);
		BigDecimal theta1 = new BigDecimal(0.0), tempTheta1 = new BigDecimal(0.0);
		BigDecimal cost = new BigDecimal(100.0), tempCost = new BigDecimal(100.0);

		Double convergence = new Double(100.0);

		while (convergence > tolerance) {
			tempTheta0 = calculateThetaZero(theta0, theta1);
			tempTheta1 = calculateThetaOne(theta0, theta1);

			cost = costFunction.at(theta0, theta1);
			tempCost = costFunction.at(tempTheta0, tempTheta1);

			alpha = (tempCost.doubleValue() > cost.doubleValue()) ? alpha / 2 : alpha + 0.02;
			convergence = Math.abs(tempCost.doubleValue() - cost.doubleValue());

			theta0 = tempTheta0;
			theta1 = tempTheta1;
		}

		return new LinearFunction<T>(theta0, theta1);
	}

	public BigDecimal calculateThetaZero(BigDecimal theta0, BigDecimal theta1) {
		LinearFunction<T> function = new LinearFunction<T>(theta0, theta1);
		BigDecimal vector = ErrorFunction.averageOfErrors(function, set).multiply(new BigDecimal(alpha.toString()));
		return new BigDecimal(theta0.toString()).subtract(vector);
	}

	public BigDecimal calculateThetaOne(BigDecimal theta0, BigDecimal theta1) {
		LinearFunction<T> function = new LinearFunction<T>(theta0, theta1);
		BigDecimal vector = ErrorFunction.averageOfErrors2(function, set).multiply(new BigDecimal(alpha.toString()));
		return new BigDecimal(theta1.toString()).subtract(vector);
	}

}
