package ai.brandon.common.functions;

import java.math.BigDecimal;

public class LinearFunction<T> {

	private final BigDecimal a;
	private final BigDecimal b;

	public LinearFunction(BigDecimal a, BigDecimal b) {
		this.a = a;
		this.b = b;
	}
	
	public LinearFunction(T a, T b) {
		this.a = new BigDecimal(a.toString());
		this.b = new BigDecimal(b.toString());
	}

	public BigDecimal getA() {
		return a;
	}

	public BigDecimal getB() {
		return b;
	}

	public BigDecimal at(T x) {
		return a.add(b.multiply(new BigDecimal(x.toString())));
	}

	public String toString() {
		return "f(x) = " + a.doubleValue() + " + " + b.doubleValue() + "x";
	}
	
}
