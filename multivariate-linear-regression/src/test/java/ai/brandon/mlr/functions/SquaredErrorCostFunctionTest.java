package ai.brandon.mlr.functions;

import static org.assertj.core.api.StrictAssertions.assertThat;

import java.math.BigDecimal;

import org.junit.Test;

import ai.brandon.mlr.model.SupervisedTrainingSet;

public class SquaredErrorCostFunctionTest {

    @Test
    public void shouldBeAbleToCalculateTheSquaredErrorCostFunctionForTheGivenSingleVariableIntegerBasedTrainingSet() {
        SupervisedTrainingSet<Integer> set = new SupervisedTrainingSet<Integer>(1);
        set.addInstance(0, 0).addInstance(1, 1).addInstance(2, 2).addInstance(3, 3).addInstance(4, 4).addInstance(5, 5);

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
        set.addInstance(0.0, 0.0).addInstance(1.0, 1.0).addInstance(2.0, 2.0).addInstance(3.0, 3.0).addInstance(4.0, 4.0).addInstance(5.0, 5.0);

        SquaredErrorCostFunction<Double> function = new SquaredErrorCostFunction<Double>(set);

        assertThat(function.at(1.0, 1.0)).isEqualTo(new BigDecimal("0.5000000000"));
        assertThat(function.at(0.0, 1.0)).isEqualTo(new BigDecimal("0.0000000000"));
        assertThat(function.at(1.0, 0.0)).isEqualTo(new BigDecimal("2.5833333333"));
        assertThat(function.at(0.0, 0.0)).isEqualTo(new BigDecimal("4.5833333333"));
        assertThat(function.at(-1.0, 0.0)).isEqualTo(new BigDecimal("7.5833333333"));
        assertThat(function.at(-1.0, -1.0)).isEqualTo(new BigDecimal("23.8333333333"));
    }

    @Test
    public void shouldBeAbleToCalculateTheSquaredErrorCostFunctionForTheGivenMultipleVariableIntegerBasedTrainingSet() {
        SupervisedTrainingSet<Integer> set = new SupervisedTrainingSet<Integer>(3);
        set.addInstance(0, 0, 4, 6).addInstance(1, 1, -3, 12).addInstance(2, 2, 19, -4).addInstance(23, 3, 0, 0).addInstance(40, 6, 6, 2);

        SquaredErrorCostFunction<Integer> function = new SquaredErrorCostFunction<Integer>(set);

        assertThat(function.at(1, 1, 1, 5)).isEqualTo(new BigDecimal("523.9000000000"));
        assertThat(function.at(0, 1, 2, 4)).isEqualTo(new BigDecimal("386.8000000000"));
        assertThat(function.at(1, 0, -1, 5)).isEqualTo(new BigDecimal("800.7000000000"));
        assertThat(function.at(0, 0, 0, 5)).isEqualTo(new BigDecimal("629.4000000000"));
        assertThat(function.at(-1, 0, 19, 5)).isEqualTo(new BigDecimal("13273.5000000000"));
        assertThat(function.at(-1, -1, 11, 4)).isEqualTo(new BigDecimal("4143.5000000000"));
    }
    
}
