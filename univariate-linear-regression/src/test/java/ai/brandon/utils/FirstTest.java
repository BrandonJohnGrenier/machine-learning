package ai.brandon.utils;

import org.junit.Test;

import ai.brandon.common.model.TrainingInstance;
import ai.brandon.common.model.TrainingSet;
import ai.brandon.ulr.Algorithm;

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
		
		Algorithm algorithm = new Algorithm(set);
		algorithm.run();
	}

}
