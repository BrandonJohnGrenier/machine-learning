package ai.brandon.mlr.algorithms;

import static ai.brandon.commons.BigDecimals.toBigDecimalList;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import ai.brandon.commons.BigDecimals;
import ai.brandon.mlr.functions.NormalisationFunction;
import ai.brandon.mlr.model.SupervisedTrainingInstance;
import ai.brandon.mlr.model.SupervisedTrainingSet;

public class MeanNormalisation<T> {

    private final List<NormalisationFunction> functions = new ArrayList<NormalisationFunction>();
    private final SupervisedTrainingSet<T> set;

    public MeanNormalisation(SupervisedTrainingSet<T> set) {
        this.set = set;

        for (int i = 0; i < set.getFeatureCount(); i++) {
            List<BigDecimal> featureList = BigDecimals.toBigDecimalList(set.getAllFeaturesAt(i));
            BigDecimal min = featureList.stream().min(Comparator.comparing(j -> j)).get();
            BigDecimal max = featureList.stream().max(Comparator.comparing(j -> j)).get();
            BigDecimal mean = new BigDecimal(featureList.stream().mapToDouble(d -> d.doubleValue()).average().getAsDouble());
            BigDecimal range = max.subtract(min);
            functions.add(new NormalisationFunction(mean, range));
        }
    }

    public List<NormalisationFunction> getNormalisationFunctions() {
        return functions;
    }

    public NormalisationFunction getNormalisationFunctionAt(Integer index) {
        return functions.get(index);
    }

    public SupervisedTrainingSet<Double> normalise() {
        List<List<Double>> scaledFeatures = IntStream.range(0, set.getFeatureCount()).mapToObj(i -> normalise(toBigDecimalList(set.getAllFeaturesAt(i)), functions.get(i))).collect(Collectors.toList());
        return apply(scaledFeatures, set);
    }

    private List<Double> normalise(List<BigDecimal> list, NormalisationFunction function) {
        return list.stream().map(x -> function.normalise(x)).map(r -> r.doubleValue()).collect(Collectors.toList());
    }

    private SupervisedTrainingSet<Double> apply(List<List<Double>> scaledFeatures, SupervisedTrainingSet<T> set) {
        SupervisedTrainingSet<Double> scaledSet = new SupervisedTrainingSet<Double>(set.getFeatureCount());
        scaledSet.add(IntStream.range(0, set.size()).mapToObj(i -> new SupervisedTrainingInstance<Double>(set.instanceAt(i).getId(), toDouble(set.instanceAt(i).getTarget()), scaledInstanceFeatures(scaledFeatures, i))).collect(Collectors.toList()));
        return scaledSet;
    }

    private List<Double> scaledInstanceFeatures(List<List<Double>> scaledFeatures, Integer i) {
        return IntStream.range(0, scaledFeatures.size()).mapToObj(index -> scaledFeatures.get(index).get(i)).collect(Collectors.toList());
    }

    private Double toDouble(T type) {
        return new BigDecimal(type.toString()).doubleValue();
    }

}
