package org.aoc.day6;

public class Race {

    private final Long time;
    private final Long record;

    public Race(Long time, Long record) {
        this.time = time;
        this.record = record;
    }

    public Long waysToWin() {
        Long wins = 0L;

        for (Long i=1L; i<time; i++) {
            Long distance = (time - i) * i;
            if (distance > record) {
                wins++;
            }
        }

        return wins;
    }
}
