package com.airtribe.meditrack.util;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Lazy singleton example of an ID generator.
 */
public class LazyIdGenerator {
    private static volatile LazyIdGenerator instance;
    private final AtomicLong counter = new AtomicLong(1000);

    private LazyIdGenerator() {
        // Lazy initialization
    }

    public static LazyIdGenerator getInstance() {
        if (instance == null) {
            synchronized (LazyIdGenerator.class) {
                if (instance == null) {
                    instance = new LazyIdGenerator();
                }
            }
        }
        return instance;
    }

    public String nextId(String prefix) {
        return prefix + counter.getAndIncrement();
    }
}
