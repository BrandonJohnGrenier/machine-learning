package ai.brandon.mlr.functions;

import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import ai.brandon.commons.BigDecimals;
import ai.brandon.commons.Subscript;

@SuppressWarnings("unchecked")
public class LinearFunction<T> {

    private final List<BigDecimal> parameters = new ArrayList<BigDecimal>();

    public LinearFunction(T... parameters) {
        _linear_function(BigDecimals.toBigDecimalList(parameters));
    }

    public LinearFunction(List<T> parameters) {
        _linear_function(BigDecimals.toBigDecimalList(parameters));
    }

    public LinearFunction(BigDecimal... parameters) {
        _linear_function(BigDecimals.arrayToList(parameters));
    }

    private void _linear_function(List<BigDecimal> parameters) {
        this.parameters.addAll(parameters);
    }

    public List<BigDecimal> getParameters() {
        return Collections.unmodifiableList(parameters);
    }

    public BigDecimal getParameterAt(Integer index) {
        return parameters.get(index);
    }
    
    public BigDecimal at(T... inputs) {
        return _at(BigDecimals.toBigDecimalList(inputs));
    }

    public BigDecimal at(List<T> inputs) {
        return _at(BigDecimals.toBigDecimalList(inputs));
    }

    private BigDecimal _at(List<BigDecimal> inputs) {
        if (inputs.size() != parameters.size() - 1) {
            throw new IllegalArgumentException("Function " + this + " has " + parameters.size() + " parameters, and requires " + (parameters.size() - 1) + " inputs - " + inputs.size() + " were provided.");
        }

        List<BigDecimal> list = new ArrayList<BigDecimal>(inputs);
        list.add(0, new BigDecimal("1"));

        return IntStream.range(0, list.size()).mapToObj(i -> list.get(i).multiply(parameters.get(i))).reduce(ZERO, BigDecimal::add);
    }

    public String toString() {
        return "f(x) = " + IntStream.range(0, parameters.size()).mapToObj(i -> parameters.get(i).doubleValue() + "x" + Subscript.valueOf(i)).collect(Collectors.joining(" + ")) + ", where x" + Subscript.valueOf(0) + " = 1";
    }

}
