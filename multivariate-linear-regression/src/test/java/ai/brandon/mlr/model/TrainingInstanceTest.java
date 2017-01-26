package ai.brandon.mlr.model;

import java.util.Arrays;

import org.junit.Test;

public class TrainingInstanceTest {

    @Test
    public void shouldBeAbleToCreateATrainingInstance() {
        TrainingInstance<Integer> instance = new TrainingInstance<Integer>(Arrays.asList(1, 2, 3), 7);
        System.out.println(instance);
    }
    
}
