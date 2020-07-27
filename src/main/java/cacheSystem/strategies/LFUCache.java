package cacheSystem.strategies;

import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeMap;

public class LFUCache<Key, Value> {
    int capacity;
    HashMap<Key, Value> cache;
    HashMap<Key, Long> keyFrequency;
    TreeMap<Long, HashSet<Key>> sortedFrequencies;

    public LFUCache(int capacity)
    {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.keyFrequency = new HashMap<>();
        this.sortedFrequencies = new TreeMap<>();
    }

    private void doEviction() {
        Long minFrequency = sortedFrequencies.firstKey();
        Key evictionKey = sortedFrequencies.get(minFrequency).iterator().next();
        deleteFromSortedFrequencies(evictionKey, minFrequency);
        keyFrequency.remove(evictionKey);
        cache.remove(evictionKey);
    }

    private void frequencyIncrement(Key key) {
        Long frequency = keyFrequency.compute(key, (k, f) -> f + 1L);
        deleteFromSortedFrequencies(key, frequency - 1);
        sortedFrequencies.computeIfAbsent(frequency, keys -> new HashSet<>()).add(key);
    }

    private void deleteFromSortedFrequencies(Key key, Long frequency) {
        if (sortedFrequencies.get(frequency).size() > 1) {
            sortedFrequencies.get(frequency).remove(key);
        } else {
            sortedFrequencies.remove(frequency);
        }
    }

    public Value putValue(Key key, Value value) {
        if (cache.size() == capacity) {
            doEviction();
        }
        Value oldValue = cache.put(key, value);
        Long frequency = keyFrequency.computeIfAbsent(key, f -> 1L);
        sortedFrequencies.computeIfAbsent(frequency, keys -> new HashSet<>()).add(key);
        return oldValue;
    }

    public Value getValue(Key key) {
        Value value = cache.get(key);
        if (value != null) {
            frequencyIncrement(key);
        }
        return value;
    }

    public void displayContent()
    {
        cache.forEach((key,value) -> System.out.println("Key = " + key + " , Value = " + value));
    }
}
