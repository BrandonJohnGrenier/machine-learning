package ai.brandon.mlr.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ai.brandon.mlr.common.JSON;

public class TrainingInstance<T> {

    private final List<T> features = new ArrayList<T>();
    private final T target;

    public TrainingInstance(List<T> features, T target) {
        this.features.addAll(features);
        this.target = target;
    }

    public List<T> getFeatures() {
        return Collections.unmodifiableList(features);
    }

    public T getTarget() {
        return target;
    }

    public T getFeatureAt(Integer index) {
        return features.get(index);
    }
    
    public String toString() {
        return JSON.stringify(this);
    }
    
}
