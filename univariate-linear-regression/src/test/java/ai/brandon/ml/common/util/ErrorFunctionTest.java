package ai.brandon.ml.common.util;

import static org.assertj.core.api.StrictAssertions.assertThat;

import java.math.BigDecimal;

import org.junit.Test;

import ai.brandon.ml.common.ErrorFunction;
import ai.brandon.ml.functions.LinearFunction;
import ai.brandon.ml.model.TrainingSet;

public class ErrorFunctionTest {

	@Test
	public void theSumOfErrorsShouldBeZeroWhenTheTrainingSetAndLinearFunctionAreEqual() {
		TrainingSet<Integer> set = new TrainingSet<Integer>();
		set.add(0, 0).add(1, 1).add(2, 2).add(3, 3).add(4, 4).add(5, 5);

		BigDecimal sumOfErrors = ErrorFunction.sumOfErrors(new LinearFunction<Integer>(0, 1), set);
		assertThat(sumOfErrors).isEqualTo(new BigDecimal("0"));
	}

	@Test
	public void theSumOfErrorsShouldBePositiveWhenTheLinearFunctionHasAMorePositiveSlopeThanTheTrainingSet() {
		TrainingSet<Integer> set = new TrainingSet<Integer>();
		set.add(0, 0).add(1, 1).add(2, 2).add(3, 3).add(4, 4).add(5, 5);

		BigDecimal sumOfErrors = ErrorFunction.sumOfErrors(new LinearFunction<Integer>(1, 2), set);
		assertThat(sumOfErrors).isEqualTo(new BigDecimal("21"));
	}

	@Test
	public void theSumOfErrorsShouldBePositiveWhenTheLinearFunctionHasALessPositiveSlopeThanTheTrainingSet() {
		TrainingSet<Integer> set = new TrainingSet<Integer>();
		set.add(0, 0).add(1, 1).add(2, 2).add(3, 3).add(4, 4).add(5, 5);

		BigDecimal sumOfErrors = ErrorFunction.sumOfErrors(new LinearFunction<Integer>(0, 0), set);
		assertThat(sumOfErrors).isEqualTo(new BigDecimal("-15"));
	}

	@Test
	public void shouldReturnAValueOfZeroWhenTheTrainingSetToDeriveTheSumOfErrorsForIsNullOrEmpty() {
		assertThat(ErrorFunction.sumOfErrors(new LinearFunction<Integer>(0, 0), new TrainingSet<Integer>())).isEqualTo(new BigDecimal("0"));
		assertThat(ErrorFunction.sumOfErrors(new LinearFunction<Integer>(0, 0), null)).isEqualTo(new BigDecimal("0"));
	}

}
