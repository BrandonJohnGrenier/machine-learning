package ai.brandon.common.model;

import static com.google.common.base.Objects.equal;
import ai.brandon.common.util.IdGenerator;

import com.google.common.base.Objects;

@SuppressWarnings("unchecked")
public class TrainingInstance<T> {

	private final String id;
	private final T x;
	private final T y;

	public TrainingInstance(T x, T y) {
		if (x == null || y == null) {
			throw new IllegalArgumentException("Invalid training instance: the value of x and y cannot be null.");
		}

		this.x = x;
		this.y = y;
		this.id = IdGenerator.generateId();
	}

	public String getId() {
		return id;
	}

	public T getX() {
		return x;
	}

	public T getY() {
		return y;
	}

	public int hashCode() {
		return Objects.hashCode(id);
	}

	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof TrainingInstance)) {
			return false;
		}

		final TrainingInstance<T> instance = (TrainingInstance<T>) obj;
		return equal(this.getId(), instance.getId());
	}

}
