package ai.brandon.common.functions;

import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.HALF_UP;

import java.math.BigDecimal;

import ai.brandon.common.model.TrainingSet;

public class SquaredErrorCostFunction<T> {

	private final TrainingSet<T> set;

	public SquaredErrorCostFunction(TrainingSet<T> set) {
		this.set = set;
	}

	public BigDecimal at(T theta0, T theta1) {
		return at(new BigDecimal(theta0.toString()), new BigDecimal(theta1.toString()));
	}

	public BigDecimal at(BigDecimal theta0, BigDecimal theta1) {
		BigDecimal total = sumOfSquaredErrors(new LinearFunction<T>(theta0, theta1));
		return total.divide(new BigDecimal(set.size() * 2), 3, HALF_UP);
	}

	private BigDecimal sumOfSquaredErrors(LinearFunction<T> function) {
		return set.stream().map(ith -> function.at(ith.getX()).subtract(new BigDecimal(ith.getY().toString())).pow(2)).reduce(ZERO, BigDecimal::add);
	}

}
