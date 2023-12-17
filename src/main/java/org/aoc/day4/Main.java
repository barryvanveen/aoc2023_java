package org.aoc.day4;

import org.aoc.Day;
import util.Output;

import java.util.ArrayList;

public class Main implements Day {
    private final Output out;

    public Main(Output out) {
        this.out = out;
    }

    @Override
    public void part1(ArrayList<String> input) {
        ScratchCard[] cards = new ScratchCard[input.size()];
        int i = 0;
        for (String s : input) {
            cards[i] = new ScratchCard(s);
            i++;
        }

        double answer = 0;
        for (ScratchCard s: cards) {
            double score = s.score();
            answer += score;
            this.out.debug("Score of game " + s.game + ": " + score);
        }

        out.answer("part 1: " + answer);
    }

    @Override
    public void part2(ArrayList<String> input) {
        out.answer("part 2: ???");
    }
}