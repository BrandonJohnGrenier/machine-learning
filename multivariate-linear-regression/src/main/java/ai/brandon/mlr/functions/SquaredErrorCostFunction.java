package ai.brandon.mlr.functions;

import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.HALF_UP;

import java.math.BigDecimal;

import ai.brandon.mlr.model.SupervisedTrainingSet;

@SuppressWarnings("unchecked")
public class SquaredErrorCostFunction<T> {

    private final SupervisedTrainingSet<T> set;

    public SquaredErrorCostFunction(SupervisedTrainingSet<T> set) {
        this.set = set;
    }

    public BigDecimal at(T... inputs) {
        BigDecimal[] array = new BigDecimal[inputs.length];
        for (int i = 0; i < inputs.length; i++) {
            array[i] = new BigDecimal(inputs[i].toString());
        }
        return at(array);
    }

    public BigDecimal at(BigDecimal... inputs) {
        return sumOfSquaredErrors(new LinearFunction<T>(inputs)).divide(new BigDecimal(set.size() * 2), 10, HALF_UP);
    }

    private BigDecimal sumOfSquaredErrors(LinearFunction<T> function) {
        return set.stream().map(ith -> function.at(ith.getFeatures()).subtract(new BigDecimal(ith.getTarget().toString())).pow(2)).reduce(ZERO, BigDecimal::add);
    }

}
