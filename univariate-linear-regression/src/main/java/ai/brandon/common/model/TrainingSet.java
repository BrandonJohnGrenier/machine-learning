package ai.brandon.common.model;

import java.util.ArrayList;
import java.util.List;

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
		if (instance == null) {
			throw new IllegalArgumentException("You cannot add a null training instance to the training set.");
		}

		this.instances.add(instance);
		return this;
	}

	public TrainingSet<T> add(T x, T y) {
		this.instances.add(new TrainingInstance<T>(x, y));
		return this;
	}

	public List<TrainingInstance<T>> list() {
		return instances;
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

}
