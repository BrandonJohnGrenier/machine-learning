package ai.brandon.ml.common.util;

import org.junit.Test;

import ai.brandon.ml.algorithms.GradientDescentAlgorithm;
import ai.brandon.ml.model.TrainingInstance;
import ai.brandon.ml.model.TrainingSet;

public class FirstTest {

	@Test
	public void foo() {
		TrainingSet set = new TrainingSet();
		set.add(new TrainingInstance(1, 1));
		set.add(new TrainingInstance(2, 2));
		set.add(new TrainingInstance(3, 3));
		set.add(new TrainingInstance(4, 4));
		set.add(new TrainingInstance(5, 5));
		set.add(new TrainingInstance(6, 6));
		
		GradientDescentAlgorithm algorithm = new GradientDescentAlgorithm(set);
		algorithm.run();
	}

}
