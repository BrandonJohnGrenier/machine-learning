package ai.brandon.mlr.algorithms;

import static java.util.Arrays.asList;

import org.junit.Test;

import ai.brandon.mlr.functions.LinearFunction;
import ai.brandon.mlr.model.SupervisedTrainingSet;

public class GradientDescentAlgorithmTest {

    @Test
    public void shouldBeAbleToMinimizeAndGenerateTheCorrectPredictiveFunction() {
        SupervisedTrainingSet<Integer> set = new SupervisedTrainingSet<Integer>(1);
        set.add(asList(0), 0).add(asList(1), 1).add(asList(2), 2).add(asList(3), 3).add(asList(4), 4).add(asList(5), 5);

        GradientDescentAlgorithm<Integer> gradientDescentAlgorithm = new GradientDescentAlgorithm<Integer>(set);

        LinearFunction<Integer> predictiveFunction = gradientDescentAlgorithm.run();
    }

}