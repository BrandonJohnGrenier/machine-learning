package ai.brandon.ulr.functions;

import java.math.BigDecimal;
import java.math.RoundingMode;

import ai.brandon.ulr.model.TrainingInstance;
import ai.brandon.ulr.model.TrainingSet;

public final class ErrorFunction {

	private ErrorFunction() {

	}

	public static <T> BigDecimal sumOfErrors(LinearFunction<T> function, TrainingSet<T> set) {
		BigDecimal total = new BigDecimal(0);
		if (set == null || set.isEmpty()) {
			return total;
		}

		for (TrainingInstance<T> instance : set.list()) {
			total = total.add(function.at(instance.getX()).subtract(new BigDecimal(instance.getY().toString())));
		}

		return total;
	}

	public static <T> BigDecimal sumOfErrors2(LinearFunction<T> function, TrainingSet<T> set) {
		BigDecimal total = new BigDecimal(0);
		if (set == null || set.isEmpty()) {
			return total;
		}

		for (TrainingInstance<T> instance : set.list()) {
			BigDecimal temp = function.at(instance.getX()).subtract(new BigDecimal(instance.getY().toString()));
			total = total.add(temp.multiply(new BigDecimal(instance.getX().toString())));
		}

		return total;
	}

	public static <T> BigDecimal averageOfErrors2(LinearFunction<T> expression, TrainingSet<T> set) {
		return sumOfErrors2(expression, set).divide(new BigDecimal(set.size().toString()), RoundingMode.UP);
	}

	public static <T> BigDecimal averageOfErrors(LinearFunction<T> function, TrainingSet<T> set) {
		return sumOfErrors(function, set).divide(new BigDecimal(set.size().toString()), RoundingMode.UP);
	}

}
