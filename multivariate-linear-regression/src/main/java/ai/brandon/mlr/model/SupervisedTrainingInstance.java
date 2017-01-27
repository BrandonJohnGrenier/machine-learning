package ai.brandon.mlr.model;

import static com.google.common.base.Objects.equal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import ai.brandon.commons.IdGenerator;
import ai.brandon.commons.JSON;

import com.google.common.base.Objects;

/**
 * A specific instance within a training set, addressable through the training set via 0-based index.
 * 
 * A supervised training instance requires the <em>feature</em> values for the training set, along with the
 * <em>target</em> value expected given the specific set of features.
 *
 * @param <T> The type of data, typically an Integer, Double or Float.
 */
@SuppressWarnings("unchecked")
public class SupervisedTrainingInstance<T> {

    private final String id;
    private final T target;
    private final List<T> features = new ArrayList<T>();

    public SupervisedTrainingInstance(T target, List<T> features) {
        validate(features, target);
        this.features.addAll(features);
        this.target = target;
        this.id = IdGenerator.generateId();
    }

    public SupervisedTrainingInstance(T target, T... features) {
        this(target, Arrays.asList(features));
    }

    public String getId() {
        return id;
    }

    public T getTarget() {
        return target;
    }

    public List<T> getFeatures() {
        return Collections.unmodifiableList(features);
    }

    public T featureAt(Integer j) {
        return features.get(j);
    }

    public int hashCode() {
        return Objects.hashCode(id);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SupervisedTrainingInstance)) {
            return false;
        }

        final SupervisedTrainingInstance<T> instance = (SupervisedTrainingInstance<T>) obj;
        return equal(this.getId(), instance.getId());
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
