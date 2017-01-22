package ai.brandon.common.model;

import java.math.BigDecimal;

import ai.brandon.common.functions.LinearFunction;

public class FunctionExpression<T> {

	private final LinearFunction<T> function;
	private BigDecimal result;

	public FunctionExpression(LinearFunction<T> function) {
		this.function = function;
	}

	public FunctionExpression<T> at(T x) {
		this.result = function.at(x);
		return this;
	}

	public BigDecimal times(T timesBy) {
		return result.multiply(new BigDecimal(timesBy.toString()));
	}

	@Override
	public String toString() {
		return "(" + function.toString() + ")" + "x"; 
	}
	
}
