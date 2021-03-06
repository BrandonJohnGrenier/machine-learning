package ai.brandon.mlr.functions;

import static java.math.RoundingMode.HALF_UP;

import java.math.BigDecimal;

import ai.brandon.commons.MinimisableFunction;
import ai.brandon.commons.model.SupervisedTrainingInstance;
import ai.brandon.commons.model.SupervisedTrainingSet;

public final class SquaredErrorPartialDerivative {

    private SquaredErrorPartialDerivative() {

    }

    public static <T> BigDecimal calculate(MinimisableFunction<T> function, SupervisedTrainingSet<T> set, Integer featureIndex) {
        return derivativeSumOfErrors(function, set, featureIndex).divide(new BigDecimal(set.size().toString()), 10, HALF_UP);
    }

    private static <T> BigDecimal derivativeSumOfErrors(MinimisableFunction<T> function, SupervisedTrainingSet<T> set, Integer featureIndex) {
        BigDecimal total = new BigDecimal(0);

        for (SupervisedTrainingInstance<T> instance : set.list()) {
            BigDecimal error = function.at(instance.getFeatures()).subtract(new BigDecimal(instance.getTarget().toString()));
            total = total.add(featureIndex == 0 ? error.multiply(new BigDecimal(1)) : error.multiply(new BigDecimal(instance.featureAt(featureIndex - 1).toString())));
        }

        return total;
    }

}
