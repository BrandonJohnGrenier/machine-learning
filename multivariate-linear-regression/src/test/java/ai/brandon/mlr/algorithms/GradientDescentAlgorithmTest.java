package ai.brandon.mlr.algorithms;

import static org.assertj.core.api.StrictAssertions.assertThat;

import java.math.BigDecimal;

import org.junit.Test;

import ai.brandon.commons.MinimisableFunction;
import ai.brandon.mlr.functions.LinearFunction;
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
    public void shouldBeAbleToMinimizeAndGenerateTheCorrectScaledPredictiveFunctionWithASingleVariable() {
        SupervisedTrainingSet<Integer> set = new SupervisedTrainingSet<Integer>(1);
        set.addInstance(0, 0).addInstance(1, 1).addInstance(2, 2).addInstance(3, 3).addInstance(4, 4).addInstance(5, 5);

        MeanNormalisation<Integer> normalistion = new MeanNormalisation<Integer>(set);
        SupervisedTrainingSet<Double> normalisedSet = normalistion.normalise();

        GradientDescentAlgorithm<Double> algorithm = new GradientDescentAlgorithm<Double>(normalisedSet);
        MinimisableFunction<Double> function = algorithm.minimise(new LinearFunction<Double>(0.0));

        assertThat(function.getParameterAt(0).doubleValue()).isBetween(2.498, 2.500);
        assertThat(function.getParameterAt(1).doubleValue()).isBetween(4.99, 5.01);
        assertThat(algorithm.getIterations()).isBetween(80, 90);
        assertThat(algorithm.getCost().doubleValue()).isBetween(0.0000000009, 0.00000001);

        BigDecimal normalisedInput = normalistion.getNormalisationFunctionAt(0).normalise(10.0);

        BigDecimal prediction = function.at(normalisedInput.doubleValue());
        assertThat(prediction.doubleValue()).isBetween(9.99, 10.01);
    }

    @Test
    public void shouldBeAbleToMinimizeAndGenerateTheCorrectScaledPredictiveFunctionWithMultipleVariables() {
        SupervisedTrainingSet<Integer> set = new SupervisedTrainingSet<Integer>(3);
        set.addInstance(0, 0, 1, 1).addInstance(1, 1, 1, 4).addInstance(2, 2, 3, 3).addInstance(3, 3, 1, 4).addInstance(4, 4, 3, 1).addInstance(5, 5, 5, 5);

        MeanNormalisation<Integer> normalistion = new MeanNormalisation<Integer>(set);
        SupervisedTrainingSet<Double> normalisedSet = normalistion.normalise();

        GradientDescentAlgorithm<Double> algorithm = new GradientDescentAlgorithm<Double>(normalisedSet);
        MinimisableFunction<Double> function = algorithm.minimise(new LinearFunction<Double>(0.0));
        
        assertThat(function.getParameterAt(0).doubleValue()).isBetween(2.499, 2.501);
        assertThat(function.getParameterAt(1).doubleValue()).isBetween(4.998, 5.000);
        assertThat(function.getParameterAt(2).doubleValue()).isBetween(0.0003, 0.005);
        assertThat(function.getParameterAt(3).doubleValue()).isBetween(0.00004, 0.00006);

        assertThat(algorithm.getIterations()).isBetween(170, 180);
        assertThat(algorithm.getCost().doubleValue()).isBetween(0.0000000009, 0.0001);

        Double x0 = normalistion.getNormalisationFunctionAt(0).normalise(4.0).doubleValue();
        Double x1 = normalistion.getNormalisationFunctionAt(1).normalise(7.0).doubleValue();
        Double x2 = normalistion.getNormalisationFunctionAt(2).normalise(-11.0).doubleValue();
        
        BigDecimal prediction = function.at(x0, x1, x2);
        assertThat(prediction.doubleValue()).isBetween(3.99, 4.01);
    }
    
}
