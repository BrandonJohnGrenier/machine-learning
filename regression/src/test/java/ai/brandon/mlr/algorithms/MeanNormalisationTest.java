package ai.brandon.mlr.algorithms;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import ai.brandon.commons.model.SupervisedTrainingSet;
import ai.brandon.mlr.algorithms.MeanNormalisation;

public class MeanNormalisationTest {

    @Test
    public void shouldBeAbleToProduceANormalisedTrainingSet() {
        SupervisedTrainingSet<Integer> set = new SupervisedTrainingSet<Integer>(4);
        set.addInstance(1, 4, 3, 1, 2);
        set.addInstance(1, 5, 4, 2, 2);
        set.addInstance(1, 6, 5, 3, 2);
        set.addInstance(1, 7, 6, 4, 2);
        set.addInstance(1, 8, 7, 5, 2);

        MeanNormalisation<Integer> normalistion = new MeanNormalisation<Integer>(set);
        SupervisedTrainingSet<Double> scaled = normalistion.normalise();

        assertThat(scaled.getAllFeaturesAt(0)).containsExactly(-0.50d, -0.25d, 0.00d, 0.25d, 0.50d);
        assertThat(scaled.getAllFeaturesAt(1)).containsExactly(-0.50d, -0.25d, 0.00d, 0.25d, 0.50d);
        assertThat(scaled.getAllFeaturesAt(2)).containsExactly(-0.50d, -0.25d, 0.00d, 0.25d, 0.50d);
        assertThat(scaled.getAllFeaturesAt(3)).containsExactly(0.00d, 0.00d, 0.00d, 0.00d, 0.00d);

        assertThat(scaled.instanceAt(0).getFeatures()).containsExactly(-0.5, -0.5, -0.5, 0.0);
        assertThat(scaled.instanceAt(1).getFeatures()).containsExactly(-0.25, -0.25, -0.25, 0.0);
        assertThat(scaled.instanceAt(2).getFeatures()).containsExactly(0.0, 0.0, 0.0, 0.0);
        assertThat(scaled.instanceAt(3).getFeatures()).containsExactly(0.25, 0.25, 0.25, 0.0);
        assertThat(scaled.instanceAt(4).getFeatures()).containsExactly(0.5, 0.5, 0.5, 0.0);

        for (int i = 0; i < set.size(); i++) {
            assertThat(scaled.instanceAt(i).getId()).isEqualTo(set.instanceAt(i).getId());
        }
    }

}
