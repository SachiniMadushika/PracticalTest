package cacheSystem.strategies;

import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class LRUCacheTest {
    private int capacity = 2;
    LRUCache lruCache = new LRUCache(capacity);
    HashSet<Integer> expectedOutput = new HashSet<>(capacity);

    @Test
    void testLRUCacheRemove(){

        lruCache.referValue(4);
        lruCache.referValue(3);
        lruCache.referValue(1);
        expectedOutput.add(3);
        expectedOutput.add(1);

        assertEquals(expectedOutput, lruCache.cache);
    }

    @Test
    void testLRUCache(){

        lruCache.referValue(4);
        lruCache.referValue(3);
        lruCache.referValue(7);
        lruCache.referValue(2);

        assertFalse(lruCache.cache.contains(new Integer(3)), "this value does not contain in cache");
        assertTrue(lruCache.cache.contains(new Integer(7)), "this value contains in cache");
    }

}