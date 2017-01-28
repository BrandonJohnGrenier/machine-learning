package ai.brandon.mlr.algorithms;

import static org.assertj.core.api.StrictAssertions.assertThat;

import java.math.BigDecimal;

import org.junit.Test;

import ai.brandon.commons.MinimisableFunction;
import ai.brandon.mlr.functions.LinearFunction;
import ai.brandon.mlr.functions.NormalisationFunction;
import ai.brandon.mlr.model.SupervisedTrainingSet;

public class GradientDescentAlgorithmTest {

    @Test
    public void shouldBeAbleToMinimizeAndGenerateTheCorrectPredictiveFunctionWithASingleVariable() {
        SupervisedTrainingSet<Integer> set = new SupervisedTrainingSet<Integer>(1);
        set.addInstance(0, 0).addInstance(1, 1).addInstance(2, 2).addInstance(3, 3).addInstance(4, 4).addInstance(5, 5);

        GradientDescentAlgorithm<Integer> algorithm = new GradientDescentAlgorithm<Integer>(set);
        MinimisableFunction<Integer> function = algorithm.minimise(new LinearFunction<Integer>(0));

        assertThat(function.getParameterAt(0).doubleValue()).isBetween(0.00001, 0.01);
        assertThat(function.getParameterAt(1).doubleValue()).isBetween(0.99, 1.01);

        BigDecimal prediction = function.at(10);
        assertThat(prediction.doubleValue()).isBetween(9.99, 10.01);
    }

    @Test
    public void shouldBeAbleToMinimizeAndGenerateTheCorrectPredictiveFunctionWithMultipleVariables() {
        SupervisedTrainingSet<Integer> set = new SupervisedTrainingSet<Integer>(3);
        set.addInstance(0, 0, 1, 1).addInstance(1, 1, 1, 4).addInstance(2, 2, 3, 3).addInstance(3, 3, 1, 4).addInstance(4, 4, 3, 1).addInstance(5, 5, 5, 5);

        GradientDescentAlgorithm<Integer> algorithm = new GradientDescentAlgorithm<Integer>(set);
        MinimisableFunction<Integer> function = algorithm.minimise(new LinearFunction<Integer>(0));

        assertThat(function.getParameterAt(0).doubleValue()).isBetween(-0.004, -0.002);
        assertThat(function.getParameterAt(1).doubleValue()).isBetween(0.99, 1.01);
        assertThat(function.getParameterAt(2).doubleValue()).isBetween(0.0005, 0.0007);
        assertThat(function.getParameterAt(3).doubleValue()).isBetween(0.0005, 0.0007);

        assertThat(algorithm.getIterations()).isBetween(170, 180);
        assertThat(algorithm.getCost().doubleValue()).isBetween(0.0000009, 0.000001);

        BigDecimal prediction = function.at(4, 7, -11);
        assertThat(prediction.doubleValue()).isBetween(3.99, 4.01);
    }

    @Test
    public void scale() {
        SupervisedTrainingSet<Integer> set = new SupervisedTrainingSet<Integer>(1);
        set.addInstance(0, 0).addInstance(1, 1).addInstance(2, 2).addInstance(3, 3).addInstance(4, 4).addInstance(5, 5);

        MeanNormalisation<Integer> normalistion = new MeanNormalisation<Integer>(set);
        SupervisedTrainingSet<Double> scaled = normalistion.normalise();

        GradientDescentAlgorithm<Double> algorithm = new GradientDescentAlgorithm<Double>(scaled);
        MinimisableFunction<Double> function = algorithm.minimise(new LinearFunction<Double>(0.0));

        // assertThat(function.getParameterAt(0).doubleValue()).isBetween(-0.004, -0.002);
        // assertThat(function.getParameterAt(1).doubleValue()).isBetween(0.99, 1.01);
        // assertThat(function.getParameterAt(2).doubleValue()).isBetween(0.0005, 0.0007);
        // assertThat(function.getParameterAt(3).doubleValue()).isBetween(0.0005, 0.0007);
        // assertThat(algorithm.getIterations()).isBetween(170, 180);
        // assertThat(algorithm.getCost().doubleValue()).isBetween(0.0000009, 0.000001);

        NormalisationFunction f = normalistion.getNormalisationFunctionAt(0);
        BigDecimal n = f.normalise(10.0);

        BigDecimal prediction = function.at(n.doubleValue());
        assertThat(prediction.doubleValue()).isBetween(9.99, 10.01);
    }

}
