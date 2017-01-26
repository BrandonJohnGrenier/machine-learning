package ai.brandon.mlr.model;

import static org.assertj.core.api.StrictAssertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class SupervisedTrainingSetTest {

    @Test
    public void shouldBeAbleToCreateASupervisedTrainingSet() {
        SupervisedTrainingSet<Integer> set = new SupervisedTrainingSet<Integer>(4);
        assertThat(set.getFeatureCount()).isEqualTo(4);
        assertThat(set.getInstances().size()).isEqualTo(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotBeAbleToCreateASupervisedTrainingSetIfTheFeatureCountIsLessThanOne() {
        new SupervisedTrainingSet<Integer>(0);
    }

    @Test
    public void shouldBeAbleToCreateASupervisedTrainingSetWithAListOfTrainingInstances() {
        List<SupervisedTrainingInstance<Integer>> instances = new ArrayList<SupervisedTrainingInstance<Integer>>();
        instances.add(new SupervisedTrainingInstance<Integer>(20, 1, 1, 1, 1));
        instances.add(new SupervisedTrainingInstance<Integer>(25, 2, 2, 2, 2));
        instances.add(new SupervisedTrainingInstance<Integer>(33, 3, 3, 3, 3));

        SupervisedTrainingSet<Integer> set = new SupervisedTrainingSet<Integer>(4, instances);
        assertThat(set.getFeatureCount()).isEqualTo(4);
        assertThat(set.getInstances().size()).isEqualTo(3);
    }

    @Test
    public void shouldBeAbleToCreateASupervisedTrainingSetWithAVarArgOfTrainingInstances() {
        SupervisedTrainingSet<Integer> set = new SupervisedTrainingSet<Integer>(4, new SupervisedTrainingInstance<Integer>(20, 1, 1, 1, 1), new SupervisedTrainingInstance<Integer>(20, 1, 1, 1, 1));
        assertThat(set.getFeatureCount()).isEqualTo(4);
        assertThat(set.getInstances().size()).isEqualTo(2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotBeAbleToCreateASupervisedTrainingSetWithAListOfTrainingInstancesIfAnyTrainingInstanceFeatureCountDoesNotMatchTheTrainingSetFeatureCount() {
        List<SupervisedTrainingInstance<Integer>> instances = new ArrayList<SupervisedTrainingInstance<Integer>>();
        instances.add(new SupervisedTrainingInstance<Integer>(20, 1, 1, 1, 1));
        instances.add(new SupervisedTrainingInstance<Integer>(25, 2, 2, 2, 2, 9));
        instances.add(new SupervisedTrainingInstance<Integer>(33, 3, 3, 3, 3));

        new SupervisedTrainingSet<Integer>(4, instances);
    }

    @Test
    public void shouldBeAbleToAddAdditionalTrainingInstancesToAnExistingTrainingSet() {
        List<SupervisedTrainingInstance<Integer>> instances = new ArrayList<SupervisedTrainingInstance<Integer>>();
        instances.add(new SupervisedTrainingInstance<Integer>(20, 1, 1, 1, 1));
        instances.add(new SupervisedTrainingInstance<Integer>(25, 2, 2, 2, 2));
        instances.add(new SupervisedTrainingInstance<Integer>(33, 3, 3, 3, 3));

        SupervisedTrainingSet<Integer> set = new SupervisedTrainingSet<Integer>(4, instances);
        assertThat(set.getInstances().size()).isEqualTo(3);

        set.add(new SupervisedTrainingInstance<Integer>(60, 1, 1, 1, 1));
        set.add(new SupervisedTrainingInstance<Integer>(70, 1, 1, 1, 1));
        assertThat(set.getInstances().size()).isEqualTo(5);
    }

}
