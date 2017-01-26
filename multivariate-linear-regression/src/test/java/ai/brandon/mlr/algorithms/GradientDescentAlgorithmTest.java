package ai.brandon.mlr.algorithms;

import static ai.brandon.mlr.model.FeatureSet.features;

import org.junit.Test;

import ai.brandon.mlr.functions.LinearFunction;
import ai.brandon.mlr.model.TrainingSet;

public class GradientDescentAlgorithmTest {

    @Test
    public void shouldBeAbleToMinimizeAndGenerateTheCorrectPredictiveFunction() {
        TrainingSet<Integer> set = new TrainingSet<Integer>();
        set.add(features(0), 0).add(features(1), 1).add(features(2), 2).add(features(3), 3).add(features(4), 4).add(features(5), 5);

        GradientDescentAlgorithm<Integer> gradientDescentAlgorithm = new GradientDescentAlgorithm<Integer>(set);

        LinearFunction<Integer> predictiveFunction = gradientDescentAlgorithm.run();
    }

}
