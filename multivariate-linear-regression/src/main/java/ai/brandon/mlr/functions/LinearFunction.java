package ai.brandon.mlr.functions;

import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import ai.brandon.commons.Subscript;

@SuppressWarnings("unchecked")
public class LinearFunction<T> {

    private final List<BigDecimal> parameters = new ArrayList<BigDecimal>();

    public LinearFunction(T... parameters) {
        for (T parameter : parameters) {
            this.parameters.add(new BigDecimal(parameter.toString()));
        }
    }

    public LinearFunction(BigDecimal... parameters) {
        this.parameters.addAll(Arrays.asList(parameters));
    }

    public List<BigDecimal> getParameters() {
        return Collections.unmodifiableList(parameters);
    }

    public BigDecimal at(List<T> inputs) {
        if (inputs.size() != parameters.size() - 1) {
            throw new IllegalArgumentException("Function " + this + " has " + parameters.size() + " parameters, and requires " + (parameters.size() - 1) + " inputs - " + inputs.size() + " were provided.");
        }

        List<BigDecimal> list = toBigDecimalList(inputs);
        return IntStream.range(0, list.size()).mapToObj(i -> list.get(i).multiply(parameters.get(i))).reduce(ZERO, BigDecimal::add);
    }

    public BigDecimal at(T... inputs) {
        return at(Arrays.asList(inputs));
    }

    public String toString() {
        return "f(x) = " + IntStream.range(0, parameters.size()).mapToObj(i -> parameters.get(i).doubleValue() + "x" + Subscript.valueOf(i)).collect(Collectors.joining(" + ")) + ", where x" + Subscript.valueOf(0) + " = 1";
    }

    private List<BigDecimal> toBigDecimalList(List<T> inputs) {
        List<BigDecimal> list = new ArrayList<BigDecimal>(Arrays.asList(new BigDecimal(1)));
        list.addAll(inputs.stream().map(input -> new BigDecimal(input.toString())).collect(Collectors.toList()));
        return list;
    }

}
