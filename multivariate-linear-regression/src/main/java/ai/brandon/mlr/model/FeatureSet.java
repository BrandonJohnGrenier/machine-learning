package ai.brandon.mlr.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("unchecked")
public class FeatureSet<T> {

    private final List<T> features = new ArrayList<T>();

    public static <T> FeatureSet<T> features(T... features) {
        return new FeatureSet<T>(features);
    }

    public FeatureSet(T... features) {
        this.features.addAll(Arrays.asList(features));
    }

    public static <T> FeatureSet<T> features(List<T> features) {
        return new FeatureSet<T>(features);
    }
    
    public FeatureSet(List<T> features) {
        this.features.addAll(features);
    }

    public List<T> list() {
        return Collections.unmodifiableList(features);
    }

    public T[] array() {
        return (T[]) features.toArray();
    }

    public Integer size() {
        return features.size();
    }
    
}
