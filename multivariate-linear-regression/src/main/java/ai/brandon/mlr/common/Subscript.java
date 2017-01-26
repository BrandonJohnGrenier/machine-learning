package ai.brandon.mlr.common;

import java.util.HashMap;
import java.util.Map;

public final class Subscript {

    private static Map<Integer, String> map = new HashMap<Integer, String>();

    static {
        map.put(0, "\u2080");
        map.put(1, "\u2081");
        map.put(2, "\u2082");
        map.put(3, "\u2083");
        map.put(4, "\u2084");
        map.put(5, "\u2085");
        map.put(6, "\u2086");
        map.put(7, "\u2087");
        map.put(8, "\u2088");
        map.put(9, "\u2089");
    }

    private Subscript() {

    }

    public static String valueOf(Integer index) {
        return map.containsKey(index) ? map.get(index) : index.toString();
    }

}
