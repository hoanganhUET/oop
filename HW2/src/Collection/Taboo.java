package Collection;

import java.util.*;

public class Taboo<T> {
    private final Map<T, Set<T>> rules = new HashMap<>();

    public Taboo(List<T> rulesList) {
        for (int i = 0; i < rulesList.size() - 1; i++) {
            T key = rulesList.get(i);
            T value = rulesList.get(i + 1);
            if (key != null && value != null) {
                this.rules.computeIfAbsent(key, k -> new HashSet<>()).add(value);
            }
        }
    }

    public Set<T> noFollow(T elem) {
        return this.rules.getOrDefault(elem, Collections.emptySet());
    }

    public void reduce(List<T> list) {
        Iterator<T> iterator = list.iterator();
        T prev = null;
        while (iterator.hasNext()) {
            T current = iterator.next();
            if (prev != null && noFollow(prev).contains(current)) {
                iterator.remove();
            } else {
                prev = current;
            }
        }
    }
}