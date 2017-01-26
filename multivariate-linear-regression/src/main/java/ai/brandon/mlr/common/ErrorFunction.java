package ai.brandon.mlr.common;

import static java.math.RoundingMode.HALF_UP;

import java.math.BigDecimal;

import ai.brandon.mlr.functions.LinearFunction;
import ai.brandon.mlr.model.TrainingInstance;
import ai.brandon.mlr.model.TrainingSet;

public final class ErrorFunction {

    private ErrorFunction() {

    }

    public static <T> BigDecimal sumOfErrors2(LinearFunction<T> function, TrainingSet<T> set, Integer featureIndex) {
        BigDecimal total = new BigDecimal(0);
        if (set == null || set.isEmpty()) {
            return total;
        }

        for (TrainingInstance<T> instance : set.list()) {
            BigDecimal error = function.at(instance.getFeatures()).subtract(new BigDecimal(instance.getTarget().toString()));
            if (featureIndex == 0) {
                total = total.add(error.multiply(new BigDecimal(1)));
            }
            else {
                total = total.add(error.multiply(new BigDecimal(instance.getFeatureAt(featureIndex - 1).toString())));
            }
        }

        return total;
    }

    public static <T> BigDecimal averageOfErrors2(LinearFunction<T> expression, TrainingSet<T> set, Integer index) {
        return sumOfErrors2(expression, set, index).divide(new BigDecimal(set.size().toString()), 10, HALF_UP);
    }

}
