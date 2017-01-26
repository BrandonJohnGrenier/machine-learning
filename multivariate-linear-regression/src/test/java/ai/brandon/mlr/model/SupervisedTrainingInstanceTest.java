package ai.brandon.mlr.model;

import static org.assertj.core.api.StrictAssertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class SupervisedTrainingInstanceTest {

    @Test
    public void shouldBeAbleToCreateATrainingInstanceWithAListOfFeatures() {
        SupervisedTrainingInstance<Integer> instance = new SupervisedTrainingInstance<Integer>(7, Arrays.asList(1, 2, 3));
        assertThat(instance.getFeatures()).asList().contains(1, 2, 3);
        assertThat(instance.getTarget()).isEqualTo(7);
    }

    @Test
    public void shouldBeAbleToCreateATrainingInstanceWithAVariableArgumentOfFeatures() {
        SupervisedTrainingInstance<Integer> instance = new SupervisedTrainingInstance<Integer>(7, 1, 2, 3);
        assertThat(instance.getFeatures()).asList().contains(1, 2, 3);
        assertThat(instance.getTarget()).isEqualTo(7);
    }
    
    @Test
    public void shouldBeAbleToFindAFeatureByIndex() {
        SupervisedTrainingInstance<Integer> instance = new SupervisedTrainingInstance<Integer>(7, Arrays.asList(1, 2, 3));
        assertThat(instance.featureAt(0)).isEqualTo(1);
        assertThat(instance.featureAt(1)).isEqualTo(2);
        assertThat(instance.featureAt(2)).isEqualTo(3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotBeAbleToCreateATrainingInstanceWhenTheTargetIsNull() {
        new SupervisedTrainingInstance<Integer>(null, Arrays.asList(1, 2, 3));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotBeAbleToCreateATrainingInstanceWhenTheFeatureSetIsNull() {
        new SupervisedTrainingInstance<Integer>(4, (List<Integer>) null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotBeAbleToCreateATrainingInstanceWhenTheFeatureSetIsEmpty() {
        new SupervisedTrainingInstance<Integer>(4, new ArrayList<Integer>());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotBeAbleToCreateATrainingInstanceWhenAnElementInTheFeatureSetIsNull() {
        new SupervisedTrainingInstance<Integer>(5, Arrays.asList(1, 2, null, 3));
    }

}
