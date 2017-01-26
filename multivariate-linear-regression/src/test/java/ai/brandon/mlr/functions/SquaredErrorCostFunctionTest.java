package ai.brandon.mlr.functions;

import static ai.brandon.mlr.model.FeatureSet.features;
import static org.assertj.core.api.StrictAssertions.assertThat;

import java.math.BigDecimal;

import org.junit.Test;

import ai.brandon.mlr.functions.SquaredErrorCostFunction;
import ai.brandon.mlr.model.TrainingSet;

public class SquaredErrorCostFunctionTest {

    @Test
    public void shouldBeAbleToCalculateTheSquaredErrorCostFunctionForTheGivenIntegerBasedTrainingSet() {
        TrainingSet<Integer> set = new TrainingSet<Integer>();
        set.add(features(0), 0).add(features(1), 1).add(features(2), 2).add(features(3), 3).add(features(4), 4).add(features(5), 5);

        SquaredErrorCostFunction<Integer> function = new SquaredErrorCostFunction<Integer>(set);

        assertThat(function.at(1, 1)).isEqualTo(new BigDecimal("0.5000000000"));
        assertThat(function.at(0, 1)).isEqualTo(new BigDecimal("0.0000000000"));
        assertThat(function.at(1, 0)).isEqualTo(new BigDecimal("2.5833333333"));
        assertThat(function.at(0, 0)).isEqualTo(new BigDecimal("4.5833333333"));
        assertThat(function.at(-1, 0)).isEqualTo(new BigDecimal("7.5833333333"));
        assertThat(function.at(-1, -1)).isEqualTo(new BigDecimal("23.8333333333"));
    }

    @Test
    public void shouldBeAbleToCalculateTheSquaredErrorCostFunctionForTheGivenDoubleBasedTrainingSet() {
        TrainingSet<Double> set = new TrainingSet<Double>();
        set.add(features(0.0), 0.0).add(features(1.0), 1.0).add(features(2.0), 2.0).add(features(3.0), 3.0).add(features(4.0), 4.0).add(features(5.0), 5.0);

        SquaredErrorCostFunction<Double> function = new SquaredErrorCostFunction<Double>(set);

        assertThat(function.at(1.0, 1.0)).isEqualTo(new BigDecimal("0.5000000000"));
        assertThat(function.at(0.0, 1.0)).isEqualTo(new BigDecimal("0.0000000000"));
        assertThat(function.at(1.0, 0.0)).isEqualTo(new BigDecimal("2.5833333333"));
        assertThat(function.at(0.0, 0.0)).isEqualTo(new BigDecimal("4.5833333333"));
        assertThat(function.at(-1.0, 0.0)).isEqualTo(new BigDecimal("7.5833333333"));
        assertThat(function.at(-1.0, -1.0)).isEqualTo(new BigDecimal("23.8333333333"));
    }

}
