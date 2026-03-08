package com.airtribe.meditrack.util;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Singleton ID generator.
 */
public class IdGenerator {
    private static final IdGenerator INSTANCE = new IdGenerator();

    private final AtomicLong counter = new AtomicLong(1);

    private IdGenerator() {
        // Eager singleton initialization (demonstrates pattern).
    }

    public static IdGenerator getInstance() {
        return INSTANCE;
    }

    public String nextId(String prefix) {
        return prefix + counter.getAndIncrement();
    }
}
