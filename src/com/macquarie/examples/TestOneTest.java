package com.macquarie.examples;

import org.junit.Test;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.macquarie.examples.TestOne.*;

import static org.junit.Assert.*;

/**
 * Created by Hussein on 2016-06-30.
 */
public class TestOneTest {


    @Test
    public void test_should_not_duplicate_keys_in_cache() {

        TestOne testOne = new TestOne();
        CachedItem cachedItem1 = new CachedItem(1, "one", "some test");
        CachedItem cachedItem2 = new CachedItem(1, "one", "extra text");
        CachedItem cachedItem3 = new CachedItem(1, "134", "extra text");
        testOne.addToCache(cachedItem1);
        testOne.addToCache(cachedItem2);
        testOne.addToCache(cachedItem3);
        testOne.addToCache(cachedItem3);

        assertEquals(2, testOne.getCache().size());

    }

    @Test
    public void test_should_not_duplicate_cached_Item_under_same_key() {

        TestOne testOne = new TestOne();
        TestOne.CachedItem cachedItem1 = new TestOne.CachedItem(1, "one", "some test");
        TestOne.CachedItem cachedItem2 = new TestOne.CachedItem(1, "one", "extra text");
        TestOne.CachedItem cachedItem3 = new TestOne.CachedItem(2, "two", "extra text");
        TestOne.CachedItem cachedItem4 = new TestOne.CachedItem(2, "two", "extra text");
        testOne.addToCache(cachedItem1);
        testOne.addToCache(cachedItem2);
        testOne.addToCache(cachedItem3);
        testOne.addToCache(cachedItem4);
        assertEquals(2, testOne.getCache().size());
        TestOne.CacheKey cacheKey = new TestOne.CacheKey(cachedItem3);
        Optional<TestOne.CacheKey> cacheKeyOptional = testOne.getCache().keySet().stream().filter(key -> key.equals(cacheKey)).findFirst();
        assertEquals(1, testOne.getCache().get(cacheKeyOptional.get()).size());

    }


    @Test
    public void test_should_remove_cached_Item_under_same_key() {

        TestOne testOne = new TestOne();
        TestOne.CachedItem cachedItem1 = new TestOne.CachedItem(1, "one", "some test");
        TestOne.CachedItem cachedItem2 = new TestOne.CachedItem(1, "one", "extra text");
        TestOne.CachedItem cachedItem3 = new TestOne.CachedItem(2, "two", "extra text");
        TestOne.CachedItem cachedItem4 = new TestOne.CachedItem(2, "two", "extra text");
        testOne.addToCache(cachedItem1);
        testOne.addToCache(cachedItem2);
        testOne.addToCache(cachedItem3);
        testOne.addToCache(cachedItem4);
        TestOne.CacheKey cacheKey = new TestOne.CacheKey(cachedItem1);
        Optional<TestOne.CacheKey> cacheKeyOptional = testOne.getCache().keySet().stream().filter(key -> cacheKey.equals(key)).findFirst();

        assertEquals(2, testOne.getCache().get(cacheKeyOptional.get()).size());

        testOne.removeFromCache(cachedItem1);
        assertEquals(1, testOne.getCache().get(cacheKeyOptional.get()).size());

    }

}