package ai.brandon.ml.algorithms;

import static org.assertj.core.api.StrictAssertions.assertThat;

import org.junit.Test;

import ai.brandon.ulr.algorithms.GradientDescentAlgorithm;
import ai.brandon.ulr.functions.LinearFunction;
import ai.brandon.ulr.model.TrainingSet;

public class GradientDescentAlgorithmTest {

	@Test
	public void shouldBeAbleToMinimizeAndGenerateTheCorrectPredictiveFunction() {
		TrainingSet<Integer> set = new TrainingSet<Integer>();
		set.add(1, 1).add(2, 2).add(3, 3).add(4, 4).add(5, 5).add(6, 6);

		GradientDescentAlgorithm<Integer> gradientDescentAlgorithm = new GradientDescentAlgorithm<Integer>(set);

		LinearFunction<Integer> predictiveFunction = gradientDescentAlgorithm.run();
		assertThat(predictiveFunction.getA().doubleValue()).isBetween(0.000090, 0.000099);
		assertThat(predictiveFunction.getB().doubleValue()).isBetween(0.999900, 1.000001);
	}

}
