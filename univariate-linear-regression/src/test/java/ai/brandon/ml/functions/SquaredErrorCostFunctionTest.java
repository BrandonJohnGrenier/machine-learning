package ai.brandon.ml.functions;

import static org.assertj.core.api.StrictAssertions.assertThat;

import java.math.BigDecimal;

import org.junit.Test;

import ai.brandon.ml.functions.SquaredErrorCostFunction;
import ai.brandon.ml.model.TrainingSet;

public class SquaredErrorCostFunctionTest {

	@Test
	public void shouldBeAbleToCalculateTheSquaredErrorCostFunctionForTheGivenIntegerBasedTrainingSet() {
		TrainingSet<Integer> set = new TrainingSet<Integer>();
		set.add(0, 0).add(1, 1).add(2, 2).add(3, 3).add(4, 4).add(5, 5);

		SquaredErrorCostFunction<Integer> function = new SquaredErrorCostFunction<Integer>(set);

		assertThat(function.at(1, 1)).isEqualTo(new BigDecimal("0.500"));
		assertThat(function.at(0, 1)).isEqualTo(new BigDecimal("0.000"));
		assertThat(function.at(1, 0)).isEqualTo(new BigDecimal("2.583"));
		assertThat(function.at(0, 0)).isEqualTo(new BigDecimal("4.583"));
		assertThat(function.at(-1, 0)).isEqualTo(new BigDecimal("7.583"));
		assertThat(function.at(-1, -1)).isEqualTo(new BigDecimal("23.833"));
	}

	@Test
	public void shouldBeAbleToCalculateTheSquaredErrorCostFunctionForTheGivenDoubleBasedTrainingSet() {
		TrainingSet<Double> set = new TrainingSet<Double>();
		set.add(0.0, 0.0).add(1.0, 1.0).add(2.0, 2.0).add(3.0, 3.0).add(4.0, 4.0).add(5.0, 5.0);

		SquaredErrorCostFunction<Double> function = new SquaredErrorCostFunction<Double>(set);

		assertThat(function.at(1.0, 1.0)).isEqualTo(new BigDecimal("0.500"));
		assertThat(function.at(0.0, 1.0)).isEqualTo(new BigDecimal("0.000"));
		assertThat(function.at(1.0, 0.0)).isEqualTo(new BigDecimal("2.583"));
		assertThat(function.at(0.0, 0.0)).isEqualTo(new BigDecimal("4.583"));
		assertThat(function.at(-1.0, 0.0)).isEqualTo(new BigDecimal("7.583"));
		assertThat(function.at(-1.0, -1.0)).isEqualTo(new BigDecimal("23.833"));
	}

}
