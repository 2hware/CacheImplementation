package com.macquarie.examples;


import java.util.*;

public class TestOne {

    public Map<CacheKey, List<CachedItem>> getCache() {
        return cache;
    }

    private Map<CacheKey, List<CachedItem>> cache
            = new HashMap<>();

    public void addToCache(CachedItem item) {
        CacheKey key = new CacheKey(item);
        if (!cache.containsKey(key)) {
            cache.put(key, new ArrayList<>());
            cache.get(key).add(item);
        } else {

            if (!cache.get(key).contains(item)) {
                cache.get(key).add(item);
            }
        }


    }

    public void removeFromCache(CachedItem item) {
        CacheKey key = new CacheKey(item);
        if (cache.containsKey(key)) {
            cache.get(key).remove(item);
        }
    }

    static class CacheKey {
        private final Integer intProperty;
        private final String strOne;
        private int hash;

        public CacheKey(CachedItem item) {
            this.intProperty = item.getIntProperty();
            this.strOne = item.getStrOne();
            this.hash = item.getStrOne().hashCode();
        }

        public Integer getIntProperty() {
            return intProperty;
        }

        public String getStrOne() {
            return strOne;
        }

        @Override
        public boolean equals(Object anObject) {
            if (this == anObject) {
                return true;
            }
            if (anObject instanceof CacheKey) {
                CacheKey anotherCacheKey = (CacheKey) anObject;
                if ((this.getIntProperty().equals(anotherCacheKey.getIntProperty()) && (this.getStrOne().equals(anotherCacheKey.getStrOne()))))
                    return true;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return this.intProperty + this.hash;
        }


    }

    static class CachedItem {
        private Integer intProperty;
        private String strOne;
        private String strTwo;

        public CachedItem(Integer intProperty, String strOne, String strTwo) {
            this.intProperty = intProperty;
            this.strOne = strOne;
            this.strTwo = strTwo;
        }

        public Integer getIntProperty() {
            return intProperty;
        }

        public String getStrOne() {
            return strOne;
        }

        public String getStrTwo() {
            return strTwo;
        }

        @Override
        public boolean equals(Object anObject) {
            if (this == anObject) {
                return true;
            }
            if (anObject instanceof CachedItem) {
                CachedItem anotherCachedItem = (CachedItem) anObject;
                if ((this.getIntProperty() == anotherCachedItem.getIntProperty()) && (this.getStrOne().equals(anotherCachedItem.getStrOne()) && (this.getStrTwo().equals(anotherCachedItem.getStrTwo()))))
                    return true;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return this.intProperty + this.getStrOne().hashCode() + this.getStrTwo().hashCode();
        }

    }


}