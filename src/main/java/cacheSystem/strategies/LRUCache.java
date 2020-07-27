package cacheSystem.strategies;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class LRUCache {
    Set<Integer> cache;
    int capacity;

    public LRUCache(int capacity)
    {
        this.cache = new LinkedHashSet<>(capacity);
        this.capacity = capacity;
    }

    public boolean getValue(int key)
    {
        /* Assumption: If key is present in the cache use it. For that move the key to front by removing it and adding it again.
         * If not return false*/
        if (!cache.contains(key)) {
            return false;
        } else {
            cache.remove(key);
            cache.add(key);
            return true;
        }
    }

    public void putValue(int key)
    {
        /* Assumption: If key is already available, remove it from cache and add it. Because cache is defined as a set and sets are
         * not allowing duplicates.
         * If the cache is full, remove the least recently used key from the cache and add new key.
         * If cache is not full and key is not there, directly add it to the cache*/

        if (cache.contains(key)) {
            cache.remove(key);
        } else if (cache.size() == capacity) {
            int firstKey = cache.iterator().next();
            cache.remove(firstKey);
        }
        cache.add(key);
    }

    public void referValue(int key)
    {
        if (getValue(key) == false) {
            putValue(key);
        }
    }


    public void displayContent()
    {
        Iterator<Integer> iterator = cache.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next() + " ");
        }
    }
}
