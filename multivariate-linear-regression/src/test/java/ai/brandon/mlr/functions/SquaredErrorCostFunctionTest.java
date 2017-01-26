package ai.brandon.mlr.functions;

import static java.util.Arrays.asList;
import static org.assertj.core.api.StrictAssertions.assertThat;

import java.math.BigDecimal;

import org.junit.Test;

import ai.brandon.mlr.model.SupervisedTrainingSet;

public class SquaredErrorCostFunctionTest {

    @Test
    public void shouldBeAbleToCalculateTheSquaredErrorCostFunctionForTheGivenSingleVariableIntegerBasedTrainingSet() {
        SupervisedTrainingSet<Integer> set = new SupervisedTrainingSet<Integer>(1);
        set.add(asList(0), 0).add(asList(1), 1).add(asList(2), 2).add(asList(3), 3).add(asList(4), 4).add(asList(5), 5);

        SquaredErrorCostFunction<Integer> function = new SquaredErrorCostFunction<Integer>(set);

        assertThat(function.at(1, 1)).isEqualTo(new BigDecimal("0.5000000000"));
        assertThat(function.at(0, 1)).isEqualTo(new BigDecimal("0.0000000000"));
        assertThat(function.at(1, 0)).isEqualTo(new BigDecimal("2.5833333333"));
        assertThat(function.at(0, 0)).isEqualTo(new BigDecimal("4.5833333333"));
        assertThat(function.at(-1, 0)).isEqualTo(new BigDecimal("7.5833333333"));
        assertThat(function.at(-1, -1)).isEqualTo(new BigDecimal("23.8333333333"));
    }

    @Test
    public void shouldBeAbleToCalculateTheSquaredErrorCostFunctionForTheGivenSingleVariableDoubleBasedTrainingSet() {
        SupervisedTrainingSet<Double> set = new SupervisedTrainingSet<Double>(1);
        set.add(asList(0.0), 0.0).add(asList(1.0), 1.0).add(asList(2.0), 2.0).add(asList(3.0), 3.0).add(asList(4.0), 4.0).add(asList(5.0), 5.0);

        SquaredErrorCostFunction<Double> function = new SquaredErrorCostFunction<Double>(set);

        assertThat(function.at(1.0, 1.0)).isEqualTo(new BigDecimal("0.5000000000"));
        assertThat(function.at(0.0, 1.0)).isEqualTo(new BigDecimal("0.0000000000"));
        assertThat(function.at(1.0, 0.0)).isEqualTo(new BigDecimal("2.5833333333"));
        assertThat(function.at(0.0, 0.0)).isEqualTo(new BigDecimal("4.5833333333"));
        assertThat(function.at(-1.0, 0.0)).isEqualTo(new BigDecimal("7.5833333333"));
        assertThat(function.at(-1.0, -1.0)).isEqualTo(new BigDecimal("23.8333333333"));
    }

}
