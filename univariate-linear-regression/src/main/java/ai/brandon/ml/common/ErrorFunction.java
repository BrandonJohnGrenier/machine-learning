package ai.brandon.ml.common;

import java.math.BigDecimal;
import java.math.RoundingMode;

import ai.brandon.ml.functions.LinearFunction;
import ai.brandon.ml.model.TrainingInstance;
import ai.brandon.ml.model.TrainingSet;

public final class ErrorFunction {

	private ErrorFunction() {

	}

	public static <T> BigDecimal sumOfErrors(LinearFunction<T> function, TrainingSet<T> set) {
		BigDecimal total = new BigDecimal(0);
		if (set == null || set.isEmpty()) {
			return total;
		}

		//System.out.println("Evaluating Function: " + function.toString());
		for (TrainingInstance<T> instance : set.list()) {
			//System.out.println("f(" + instance.getX() + ") = " + function.at(instance.getX()).doubleValue() + ", actual = " + instance.getY() + ", delta = " + function.at(instance.getX()).subtract(new BigDecimal(instance.getY().toString())).doubleValue());
			total = total.add(function.at(instance.getX()).subtract(new BigDecimal(instance.getY().toString())));
		}

		//System.out.println("Absolute error value: " + total);
		return total;
	}

	public static <T> BigDecimal sumOfErrors2(LinearFunction<T> function, TrainingSet<T> set) {
		BigDecimal total = new BigDecimal(0);
		if (set == null || set.isEmpty()) {
			return total;
		}

		//System.out.println("Evaluating Function 2: " + function.toString());
		for (TrainingInstance<T> instance : set.list()) {
			//System.out.println("f(" + instance.getX() + ") = " + function.at(instance.getX()));
			BigDecimal temp = function.at(instance.getX()).subtract(new BigDecimal(instance.getY().toString()));
			total = total.add(temp.multiply(new BigDecimal(instance.getX().toString())));
		}

		//System.out.println("Absolute error value 2: " + total);
		return total;
	}

	public static <T> BigDecimal averageOfErrors2(LinearFunction<T> expression, TrainingSet<T> set) {
		return sumOfErrors2(expression, set).divide(new BigDecimal(set.size().toString()), RoundingMode.UP);
	}

	public static <T> BigDecimal averageOfErrors(LinearFunction<T> function, TrainingSet<T> set) {
		return sumOfErrors(function, set).divide(new BigDecimal(set.size().toString()), RoundingMode.UP);
	}

}
