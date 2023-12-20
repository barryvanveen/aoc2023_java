package org.aoc.day5;

import static java.lang.Long.parseLong;

public class Map {
    private final long sourceStart;
    private final long range;
    private final long sourceEnd;
    private final long destination;

    public Map(String input) {
        String[] parts = input.split(" ");
        this.destination = parseLong(parts[0]);
        this.sourceStart = parseLong(parts[1]);
        this.range = parseLong(parts[2]);

        this.sourceEnd = this.sourceStart + this.range;
    }

    public boolean inRange(Long input) {
        return input >= sourceStart && input < sourceEnd;
    }

    public Long map(Long input) {
        return destination + (input - sourceStart);
    }
}
