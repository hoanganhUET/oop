package Collection;

import java.util.*;

public class Appearances {
    public static <T> int sameCount(Collection<T> a, Collection<T> b) {
        Map<T, Integer> countA = countElements(a);
        Map<T, Integer> countB = countElements(b);
        int same = 0;
        for (T element : countA.keySet()) {
            if (countA.get(element).equals(countB.get(element))) {
                ++same;
            }
        }
        return same;
    }

    private static <T> Map<T, Integer> countElements(Collection<T> collection) {
        Map<T, Integer> countMap = new HashMap<>();
        for (T element : collection) {
            countMap.put(element, countMap.getOrDefault(element, 0) + 1);
        }
        return countMap;
    }
}