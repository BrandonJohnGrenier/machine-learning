package ai.brandon.commons.model;

import static org.assertj.core.api.StrictAssertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import ai.brandon.commons.model.SupervisedTrainingInstance;
import ai.brandon.commons.model.SupervisedTrainingSet;

@SuppressWarnings("unchecked")
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
    public void shouldBeAbleToAddAnAdditionalTrainingInstanceToAnExistingTrainingSet() {
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

    @Test
    public void shouldBeAbleToAddAnAdditionalListTrainingInstanceToAnExistingTrainingSet() {
        List<SupervisedTrainingInstance<Integer>> instances1 = new ArrayList<SupervisedTrainingInstance<Integer>>();
        instances1.add(new SupervisedTrainingInstance<Integer>(20, 1, 1, 1, 1));
        instances1.add(new SupervisedTrainingInstance<Integer>(25, 2, 2, 2, 2));
        instances1.add(new SupervisedTrainingInstance<Integer>(33, 3, 3, 3, 3));

        SupervisedTrainingSet<Integer> set = new SupervisedTrainingSet<Integer>(4, instances1);
        assertThat(set.getInstances().size()).isEqualTo(3);

        List<SupervisedTrainingInstance<Integer>> instances2 = new ArrayList<SupervisedTrainingInstance<Integer>>();
        instances2.add(new SupervisedTrainingInstance<Integer>(20, 1, 1, 1, 1));
        instances2.add(new SupervisedTrainingInstance<Integer>(25, 2, 2, 2, 2));
        instances2.add(new SupervisedTrainingInstance<Integer>(33, 3, 3, 3, 3));

        set.add(instances2);
        assertThat(set.getInstances().size()).isEqualTo(6);
    }

    @Test
    public void shouldBeAbleToAddAdditionalVarArgTrainingInstancesToAnExistingTrainingSet() {
        List<SupervisedTrainingInstance<Integer>> instances1 = new ArrayList<SupervisedTrainingInstance<Integer>>();
        instances1.add(new SupervisedTrainingInstance<Integer>(20, 1, 1, 1, 1));
        instances1.add(new SupervisedTrainingInstance<Integer>(25, 2, 2, 2, 2));
        instances1.add(new SupervisedTrainingInstance<Integer>(33, 3, 3, 3, 3));

        SupervisedTrainingSet<Integer> set = new SupervisedTrainingSet<Integer>(4, instances1);
        assertThat(set.getInstances().size()).isEqualTo(3);

        List<SupervisedTrainingInstance<Integer>> instances2 = new ArrayList<SupervisedTrainingInstance<Integer>>();
        instances2.add(new SupervisedTrainingInstance<Integer>(20, 1, 1, 1, 1));
        instances2.add(new SupervisedTrainingInstance<Integer>(25, 2, 2, 2, 2));
        instances2.add(new SupervisedTrainingInstance<Integer>(33, 3, 3, 3, 3));

        set.add(instances2.get(0), instances2.get(1), instances2.get(2));
        assertThat(set.getInstances().size()).isEqualTo(6);
    }

    @Test
    public void shouldBeAbleToReturnTheSupervisedTrainingInstanceAtTheSpecifiedIndex() {
        List<SupervisedTrainingInstance<Integer>> instances = new ArrayList<SupervisedTrainingInstance<Integer>>();
        instances.add(new SupervisedTrainingInstance<Integer>(20, 1, 1, 1, 1));
        instances.add(new SupervisedTrainingInstance<Integer>(25, 2, 2, 2, 2));
        instances.add(new SupervisedTrainingInstance<Integer>(33, 3, 3, 3, 3));

        SupervisedTrainingSet<Integer> set = new SupervisedTrainingSet<Integer>(4, instances);
        assertThat(set.instanceAt(0)).isEqualTo(instances.get(0));
    }

    @Test
    public void shouldBeAbleToReturnTheIndexOfTheSpecifiedInstance() {
        List<SupervisedTrainingInstance<Integer>> instances = new ArrayList<SupervisedTrainingInstance<Integer>>();
        instances.add(new SupervisedTrainingInstance<Integer>(20, 1, 1, 1, 1));
        instances.add(new SupervisedTrainingInstance<Integer>(25, 2, 2, 2, 2));
        instances.add(new SupervisedTrainingInstance<Integer>(33, 3, 3, 3, 3));

        SupervisedTrainingSet<Integer> set = new SupervisedTrainingSet<Integer>(4, instances);
        assertThat(set.indexOf(instances.get(1))).isEqualTo(1);
    }

    @Test
    public void shouldBeAbleToConstructAndAddATrainingInstanceBasedOnTheProvidedTargetAndListOfFeatures() {
        List<SupervisedTrainingInstance<Integer>> instances = new ArrayList<SupervisedTrainingInstance<Integer>>();
        instances.add(new SupervisedTrainingInstance<Integer>(20, 1, 1, 1, 1));
        instances.add(new SupervisedTrainingInstance<Integer>(25, 2, 2, 2, 2));
        instances.add(new SupervisedTrainingInstance<Integer>(33, 3, 3, 3, 3));

        SupervisedTrainingSet<Integer> set = new SupervisedTrainingSet<Integer>(4, instances);
        assertThat(set.getInstances().size()).isEqualTo(3);

        set.addInstance(40, Arrays.asList(5, 6, 7, 8));
        set.addInstance(50, Arrays.asList(5, 6, 7, 8));

        assertThat(set.getInstances().size()).isEqualTo(5);
    }

    @Test
    public void shouldBeAbleToConstructAndAddATrainingInstanceBasedOnTheProvidedTargetAndVarArgsOfFeatures() {
        List<SupervisedTrainingInstance<Integer>> instances = new ArrayList<SupervisedTrainingInstance<Integer>>();
        instances.add(new SupervisedTrainingInstance<Integer>(20, 1, 1, 1, 1));
        instances.add(new SupervisedTrainingInstance<Integer>(25, 2, 2, 2, 2));
        instances.add(new SupervisedTrainingInstance<Integer>(33, 3, 3, 3, 3));

        SupervisedTrainingSet<Integer> set = new SupervisedTrainingSet<Integer>(4, instances);
        assertThat(set.getInstances().size()).isEqualTo(3);

        set.addInstance(40, 5, 6, 7, 8);
        set.addInstance(50, 5, 6, 7, 8);

        assertThat(set.getInstances().size()).isEqualTo(5);
    }

}
