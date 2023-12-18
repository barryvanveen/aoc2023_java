package org.aoc.day4;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class ScratchCard {
    public final Integer game;
    private final Set<Integer> numbers;
    private final Set<Integer> winningNumbers;

    public ScratchCard(String input) {
        int colonPos = input.indexOf(':');
        int pipePos = input.indexOf('|');

        this.game = Integer.parseInt(input.substring(5, colonPos).trim());
        this.winningNumbers = Arrays.stream(
                input.substring(colonPos+1, pipePos).trim().split("([ ]+)")
            ).map(Integer::parseInt).collect(Collectors.toSet());
        this.numbers = Arrays.stream(
                input.substring(pipePos+1).trim().split("([ ]+)")
        ).map(Integer::parseInt).collect(Collectors.toSet());
    }

    public double score() {
        int winningCounter = 0;
        for (int i : this.winningNumbers) {
            if (this.numbers.contains(i)) {
                winningCounter++;
            }
        }

        if (winningCounter == 0) {
            return 0;
        }

        if (winningCounter == 1) {
            return 1;
        }

        return Math.pow(2, winningCounter-1);
    }

    public int winningNumbers() {
        int winningCounter = 0;
        for (int i : this.winningNumbers) {
            if (this.numbers.contains(i)) {
                winningCounter++;
            }
        }

        return winningCounter;
    }
}
