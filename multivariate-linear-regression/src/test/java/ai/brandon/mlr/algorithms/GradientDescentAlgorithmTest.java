package ai.brandon.mlr.algorithms;

import static java.util.Arrays.asList;

import org.junit.Test;

import ai.brandon.mlr.functions.LinearFunction;
import ai.brandon.mlr.model.SupervisedTrainingSet;

public class GradientDescentAlgorithmTest {

    @Test
    public void shouldBeAbleToMinimizeAndGenerateTheCorrectPredictiveFunction() {
        SupervisedTrainingSet<Integer> set = new SupervisedTrainingSet<Integer>(1);
        set.addInstance(0, asList(0)).addInstance(1, asList(1)).addInstance(2, asList(2)).addInstance(3, asList(3)).addInstance(4, asList(4)).addInstance(5, asList(5));

        GradientDescentAlgorithm<Integer> gradientDescentAlgorithm = new GradientDescentAlgorithm<Integer>(set);

        LinearFunction<Integer> predictiveFunction = gradientDescentAlgorithm.run();
    }

}
