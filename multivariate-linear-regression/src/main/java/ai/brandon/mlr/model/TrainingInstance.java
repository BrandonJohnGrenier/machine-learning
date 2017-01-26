package ai.brandon.mlr.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ai.brandon.mlr.common.JSON;

public class TrainingInstance<T> {

    private final List<T> features = new ArrayList<T>();
    private final T target;

    public TrainingInstance(List<T> features, T target) {
        validate(features, target);
        this.features.addAll(features);
        this.target = target;
    }

    public List<T> getFeatures() {
        return Collections.unmodifiableList(features);
    }

    public T getTarget() {
        return target;
    }

    public T featureAt(Integer index) {
        return features.get(index);
    }

    public String toString() {
        return JSON.stringify(this);
    }

    private void validate(List<T> features, T target) {
        if (target == null || features == null || features.isEmpty()) {
            throw new IllegalArgumentException("Cannot create training instance: the features must not be null or empty, the target must not be null.");
        }

        for (T feature : features) {
            if (feature == null) {
                throw new IllegalArgumentException("Cannot create training instance: a feature cannot be null.");
            }
        }
    }

}
