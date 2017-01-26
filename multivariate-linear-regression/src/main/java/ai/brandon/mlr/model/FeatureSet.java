package ai.brandon.mlr.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import ai.brandon.mlr.common.JSON;

@SuppressWarnings("unchecked")
public class FeatureSet<T> {

    private final List<T> features = new ArrayList<T>();

    public static <T> FeatureSet<T> features(T... features) {
        return new FeatureSet<T>(features);
    }

    public static <T> FeatureSet<T> features(List<T> features) {
        return new FeatureSet<T>(features);
    }

    public FeatureSet(T... features) {
        this(Arrays.asList(features));
    }

    public FeatureSet(List<T> features) {
        for (T feature : features) {
            if (feature == null) {
                throw new IllegalArgumentException("Error constructing feature set: cannot add a null feature.");
            }
            this.features.add(feature);
        }
    }

    public FeatureSet() {

    }

    public List<T> list() {
        return Collections.unmodifiableList(features);
    }

    public Integer size() {
        return features.size();
    }

    public List<T> getFeatures() {
        return Collections.unmodifiableList(features);
    }

    public String toString() {
        return JSON.stringify(this);
    }

}
