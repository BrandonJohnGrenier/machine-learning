package ai.brandon.mlr.model;

import static org.assertj.core.api.StrictAssertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

public class TrainingInstanceTest {

    @Test
    public void shouldBeAbleToCreateATrainingInstance() {
        TrainingInstance<Integer> instance = new TrainingInstance<Integer>(Arrays.asList(1, 2, 3), 7);
        assertThat(instance.getFeatures()).asList().contains(1, 2, 3);
        assertThat(instance.getTarget()).isEqualTo(7);
    }

    @Test
    public void shouldBeAbleToFindAFeatureByIndex() {
        TrainingInstance<Integer> instance = new TrainingInstance<Integer>(Arrays.asList(1, 2, 3), 7);
        assertThat(instance.featureAt(0)).isEqualTo(1);
        assertThat(instance.featureAt(1)).isEqualTo(2);
        assertThat(instance.featureAt(2)).isEqualTo(3);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void shouldNotBeAbleToCreateATrainingInstanceWhenTheTargetIsNull() {
        new TrainingInstance<Integer>(Arrays.asList(1, 2, 3), null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotBeAbleToCreateATrainingInstanceWhenTheFeatureSetIsNull() {
        new TrainingInstance<Integer>(null, 4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotBeAbleToCreateATrainingInstanceWhenTheFeatureSetIsEmpty() {
        new TrainingInstance<Integer>(new ArrayList<Integer>(), 4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotBeAbleToCreateATrainingInstanceWhenAnElementInTheFeatureSetIsNull() {
        new TrainingInstance<Integer>(Arrays.asList(1, 2, null, 3), 5);
    }

}
