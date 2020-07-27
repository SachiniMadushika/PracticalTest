package cacheSystem;

import cacheSystem.strategies.LFUCache;
import cacheSystem.strategies.LRUCache;

public class CacheStrategy {
    public static void main(String[] args) {
        final int capacity = 2;

        LRUCache lruCache = new LRUCache(capacity);
        lruCache.referValue(4);
        lruCache.referValue(3);
        lruCache.referValue(1);

        lruCache.displayContent();


        LFUCache lfuCache = new LFUCache(capacity);
        lfuCache.putValue(1, 10);
        lfuCache.putValue(2, 20);
        lfuCache.getValue(1);
        lfuCache.putValue(3, 30);
        lfuCache.getValue(2);
        lfuCache.getValue(3);
        lfuCache.putValue(4, 40);

        lfuCache.displayContent();
    }
}
