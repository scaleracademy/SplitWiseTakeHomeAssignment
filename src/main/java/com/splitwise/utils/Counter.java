package com.splitwise.utils;

public class Counter {
    private Long start;

    public Counter(Long start) {
        this.start = start;
    }

    public Long next() {
        start++;
        return start - 1;
    }
}
