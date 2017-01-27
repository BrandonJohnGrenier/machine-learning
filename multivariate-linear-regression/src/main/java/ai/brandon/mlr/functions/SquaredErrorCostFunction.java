package ai.brandon.mlr.functions;

import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.HALF_UP;

import java.math.BigDecimal;

import ai.brandon.commons.BigDecimals;
import ai.brandon.mlr.model.SupervisedTrainingSet;

@SuppressWarnings("unchecked")
public class SquaredErrorCostFunction<T> {

    private final SupervisedTrainingSet<T> set;

    public SquaredErrorCostFunction(SupervisedTrainingSet<T> set) {
        this.set = set;
    }

    public BigDecimal at(T... inputs) {
        return at(BigDecimals.toBigDecimalArray(inputs));
    }

    public BigDecimal at(BigDecimal... inputs) {
        return sumOfSquaredErrors(new LinearFunction<T>(inputs)).divide(new BigDecimal(2 * set.size()), 10, HALF_UP);
    }

    private BigDecimal sumOfSquaredErrors(LinearFunction<T> function) {
        return set.stream().map(ith -> function.at(ith.getFeatures()).subtract(new BigDecimal(ith.getTarget().toString())).pow(2)).reduce(ZERO, BigDecimal::add);
    }

}
