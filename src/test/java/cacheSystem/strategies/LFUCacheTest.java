package cacheSystem.strategies;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LFUCacheTest {

    private int capacity = 2;
    LFUCache lfuCache = new LFUCache(capacity);
    HashMap<Integer, Integer> expectedOutput = new HashMap<>(capacity);

    @Test
    void testLFUCacheInsert(){

        lfuCache.putValue(1, 10);
        lfuCache.putValue(2, 20);
        lfuCache.putValue(3, 30);//remove (1,10)
        expectedOutput.put(2,20);
        expectedOutput.put(3,30);

        assertEquals(expectedOutput, lfuCache.cache);
    }

    @Test
    void testLFUCacheRemove(){

        lfuCache.putValue(1, 15);
        lfuCache.putValue(2, 25);
        lfuCache.getValue(1);
        lfuCache.putValue(3, 35);//remove (2,25)
        lfuCache.getValue(2);//return false
        lfuCache.getValue(3);
        lfuCache.getValue(3);
        lfuCache.putValue(4, 45);//remove (1,15)
        expectedOutput.put(4,45);
        expectedOutput.put(3,35);

        assertEquals(expectedOutput, lfuCache.cache);
    }
}