package ai.brandon.mlr.model;

import static org.assertj.core.api.StrictAssertions.assertThat;

import org.junit.Test;

public class FeatureSetTest {

    @Test
    public void shouldBeAbleToCreateAFeatureSet() {
        FeatureSet<Integer> set = new FeatureSet<Integer>(2, 3, 4, 6, 7);
        assertThat(set.size()).isEqualTo(5);
        assertThat(set.list().size()).isEqualTo(5);
        assertThat(set.list()).asList().contains(2, 3, 4, 6, 7);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotBeAbleToCreateAFeatureIfAnyFeatureIsNull() {
        new FeatureSet<Integer>(2, 3, null, 6, 7);
    }

}
