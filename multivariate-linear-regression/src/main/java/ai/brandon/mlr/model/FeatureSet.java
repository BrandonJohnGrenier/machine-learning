package ai.brandon.mlr.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("unchecked")
public class FeatureSet<T> {

    private final List<T> features = new ArrayList<T>();

    public FeatureSet(T... features) {
        this.features.addAll(Arrays.asList(features));
    }

    public FeatureSet(List<T> features) {
        this.features.addAll(features);
    }

    public List<T> getFeatures() {
        return Collections.unmodifiableList(features);
    }

}
