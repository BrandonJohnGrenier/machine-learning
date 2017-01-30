package ai.brandon.excel.adapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdapterResult<T> {

    private T data;
    private final List<String> errors;

    private AdapterResult(T data, List<String> errors) {
        this.data = data;
        this.errors = errors;
    }

    public static <T> AdapterResult<T> accept(T results) {
        return new AdapterResult<T>(results, null);
    }

    public static <T> AdapterResult<T> reject(String error) {
        return new AdapterResult<T>(null, Arrays.asList(error));
    }

    public static <T> AdapterResult<T> reject(List<String> errors) {
        return new AdapterResult<T>(null, errors);
    }

    public boolean accepted() {
        return !rejected();
    }

    public boolean rejected() {
        return !getErrors().isEmpty();
    }

    public T getData() {
        return data;
    }

    public List<String> getErrors() {
        return errors != null ? errors : new ArrayList<>();
    }

}
