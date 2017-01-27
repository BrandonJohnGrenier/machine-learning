package ai.brandon.mlr.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import ai.brandon.commons.JSON;

@SuppressWarnings("unchecked")
public class SupervisedTrainingSet<T> {

    private final List<SupervisedTrainingInstance<T>> instances = new ArrayList<SupervisedTrainingInstance<T>>();
    private final Integer featureCount;

    public SupervisedTrainingSet(Integer featureCount) {
        if (featureCount <= 0) {
            throw new IllegalArgumentException("A supervised training set must have at least one feature.");
        }
        this.featureCount = featureCount;
    }

    public SupervisedTrainingSet(Integer featureCount, List<SupervisedTrainingInstance<T>> instances) {
        this(featureCount);
        instances.stream().forEach(instance -> add(instance));
    }

    @SafeVarargs
    public SupervisedTrainingSet(Integer featureCount, SupervisedTrainingInstance<T>... instances) {
        this(featureCount, Arrays.asList(instances));
    }

    public SupervisedTrainingSet<T> add(List<SupervisedTrainingInstance<T>> instances) {
        instances.stream().forEach(instance -> add(instance));
        return this;
    }

    public SupervisedTrainingSet<T> add(SupervisedTrainingInstance<T>... instances) {
        add(Arrays.asList(instances));
        return this;
    }

    public SupervisedTrainingSet<T> add(SupervisedTrainingInstance<T> instance) {
        validate(instance);
        this.instances.add(instance);
        return this;
    }

    public SupervisedTrainingSet<T> addInstance(T target, List<T> features) {
        add(new SupervisedTrainingInstance<T>(target, features));
        return this;
    }

    public SupervisedTrainingSet<T> addInstance(T target, T... features) {
        addInstance(target, Arrays.asList(features));
        return this;
    }

    public Integer getFeatureCount() {
        return featureCount;
    }

    public List<SupervisedTrainingInstance<T>> getInstances() {
        return Collections.unmodifiableList(instances);
    }

    public List<SupervisedTrainingInstance<T>> list() {
        return Collections.unmodifiableList(instances);
    }

    public SupervisedTrainingInstance<T> instanceAt(Integer index) {
        return instances.get(index);
    }

    public Integer indexOf(SupervisedTrainingInstance<T> instance) {
        return instances.indexOf(instance);
    }

    public boolean isEmpty() {
        return instances.isEmpty();
    }

    public Integer size() {
        return instances.size();
    }

    public Stream<SupervisedTrainingInstance<T>> stream() {
        return instances.stream();
    }

    public String toString() {
        return JSON.stringify(this);
    }

    private void validate(SupervisedTrainingInstance<T> instance) {
        if (instance == null) {
            throw new IllegalArgumentException("Cannot add training instance: the training instance to add was null.");
        }
        if (instance.getFeatures().size() != featureCount) {
            throw new IllegalArgumentException("Cannot add training instance: expected a feature count of " + featureCount + ", but the training instance has " + instance.getFeatures().size() + " feature(s).");
        }
    }

}
