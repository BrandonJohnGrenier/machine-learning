package ai.brandon.mlr.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class TrainingSet<T> {

    private final List<TrainingInstance<T>> instances = new ArrayList<TrainingInstance<T>>();

    public TrainingSet() {

    }

    public TrainingSet(List<TrainingInstance<T>> instances) {
        this.instances.addAll(instances);
    }

    public TrainingSet<T> add(List<TrainingInstance<T>> instances) {
        this.instances.addAll(instances);
        return this;
    }

    public TrainingSet<T> add(TrainingInstance<T> instance) {
        this.instances.add(instance);
        return this;
    }

    public TrainingSet<T> add(FeatureSet<T> features, T target) {
        this.instances.add(new TrainingInstance<T>(features, target));
        return this;
    }

    public Integer featureCount() {
        return instances.get(0).getFeatures().size();
    }
    
    public List<TrainingInstance<T>> list() {
        return Collections.unmodifiableList(instances);
    }

    public Integer indexOf(TrainingInstance<T> instance) {
        return instances.indexOf(instance);
    }

    public boolean isEmpty() {
        return instances.isEmpty();
    }

    public Integer size() {
        return instances.size();
    }

    public Stream<TrainingInstance<T>> stream() {
        return instances.stream();
    }

}
