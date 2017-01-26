package ai.brandon.mlr.model;

public class TrainingInstance<T> {

    private final FeatureSet<T> features;
    private final T target;

    public TrainingInstance(FeatureSet<T> features, T target) {
        this.features = features;
        this.target = target;
    }

    public FeatureSet<T> getFeatures() {
        return features;
    }

    public T getTarget() {
        return target;
    }

    public T getFeatureAt(Integer index) {
        return features.list().get(index);
    }
    
}
