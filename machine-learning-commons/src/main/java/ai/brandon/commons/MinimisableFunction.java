package ai.brandon.commons;

import java.math.BigDecimal;
import java.util.List;

@SuppressWarnings("unchecked")
public interface MinimisableFunction<T> {

    List<BigDecimal> getParameters();

    BigDecimal getParameterAt(Integer index);

    BigDecimal at(T... inputs);

    BigDecimal at(List<T> inputs);

    MinimisableFunction<T> newInstance(T... parameters);

    MinimisableFunction<T> newInstance(BigDecimal... parameters);

}
