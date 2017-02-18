package ai.brandon.commons.functions;

import java.math.BigDecimal;
import java.util.List;

@SuppressWarnings("unchecked")
public interface CostFunction<T> {

    BigDecimal at(List<T> inputs);

    BigDecimal at(T... inputs);

    BigDecimal at(BigDecimal... inputs);

}
