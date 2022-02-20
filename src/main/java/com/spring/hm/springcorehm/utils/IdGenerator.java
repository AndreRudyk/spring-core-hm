package com.spring.hm.springcorehm.utils;

public final class IdGenerator {

    private static final long LEFT_LIMIT = 1;

    private static final long RIGHT_LIMIT = 100000L;

    private IdGenerator() {
    }

    /**
     * Generates id value for business models
     * @return long business model id.
     */
    public static long generateId() {
        return LEFT_LIMIT + (long) (Math.random() * (RIGHT_LIMIT - LEFT_LIMIT));
    }
}
