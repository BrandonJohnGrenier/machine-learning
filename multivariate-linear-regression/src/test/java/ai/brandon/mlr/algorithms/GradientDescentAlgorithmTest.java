package ai.brandon.mlr.algorithms;

import static org.assertj.core.api.StrictAssertions.assertThat;

import org.junit.Test;

import ai.brandon.mlr.functions.LinearFunction;
import ai.brandon.mlr.model.SupervisedTrainingSet;

public class GradientDescentAlgorithmTest {

    @Test
    public void shouldBeAbleToMinimizeAndGenerateTheCorrectPredictiveFunction() {
        SupervisedTrainingSet<Integer> set = new SupervisedTrainingSet<Integer>(1);
        set.addInstance(0, 0).addInstance(1, 1).addInstance(2, 2).addInstance(3, 3).addInstance(4, 4).addInstance(5, 5);

        GradientDescentAlgorithm<Integer> gradientDescentAlgorithm = new GradientDescentAlgorithm<Integer>(set);

        LinearFunction<Integer> predictiveFunction = gradientDescentAlgorithm.run();
        assertThat(predictiveFunction.getParameterAt(0).doubleValue()).isBetween(0.00001, 0.01);
        assertThat(predictiveFunction.getParameterAt(1).doubleValue()).isBetween(0.99, 1.01);
    }

    @Test
    public void shouldBeAbleToMinimizeAndGenerateTheCorrectPredictiveFunctionMulti() {
        SupervisedTrainingSet<Integer> set = new SupervisedTrainingSet<Integer>(3);
        set.addInstance(0, 0, 1, 1).addInstance(1, 1, 1, 4).addInstance(2, 2, 3, 3).addInstance(3, 3, 1, 4).addInstance(4, 4, 3, 1).addInstance(5, 5, 5, 5);

        GradientDescentAlgorithm<Integer> gradientDescentAlgorithm = new GradientDescentAlgorithm<Integer>(set);

        LinearFunction<Integer> predictiveFunction = gradientDescentAlgorithm.run();

        assertThat(predictiveFunction.getParameterAt(0).doubleValue()).isBetween(-0.004, -0.002);
        assertThat(predictiveFunction.getParameterAt(1).doubleValue()).isBetween(0.99, 1.01);
        assertThat(predictiveFunction.getParameterAt(2).doubleValue()).isBetween(0.0005, 0.0007);
        assertThat(predictiveFunction.getParameterAt(3).doubleValue()).isBetween(0.0005, 0.0007);

        assertThat(gradientDescentAlgorithm.getIterations()).isBetween(170, 180);
        assertThat(gradientDescentAlgorithm.getCost().doubleValue()).isBetween(0.0000009, 0.000001);
    }

}
