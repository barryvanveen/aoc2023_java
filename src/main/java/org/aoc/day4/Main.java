package org.aoc.day4;

import org.aoc.Day;
import util.Output;

import java.util.ArrayList;
import java.util.HashMap;

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
        ScratchCard[] cards = new ScratchCard[input.size()];
        int i = 0;
        for (String s : input) {
            cards[i] = new ScratchCard(s);
            i++;
        }

        HashMap<Integer, Integer> amounts = new HashMap<>();
        // we start out with 1 card of each
        for (ScratchCard s : cards) {
            amounts.put(s.game, 1);
        }

        // take the winning numbers of each card and add to follow-up cards
        for (ScratchCard c : cards) {
            int winningNumbers = c.winningNumbers();
            int numberOfCards = amounts.get(c.game);
            this.out.debug("Card " + c.game + " has " + winningNumbers + " matching numbers");
            this.out.debug("Number of " + c.game + " cards: " + numberOfCards);

            for (int x = c.game + 1; x < c.game + 1 + winningNumbers; x++) {
                amounts.put(x, amounts.get(x) + numberOfCards);
            }
        }

        // sum the number of cards
        int totalCards = 0;
        for (int a : amounts.keySet()) {
            this.out.debug("Card " + a + ": " + amounts.get(a));
            totalCards += amounts.get(a);
        }

        out.answer("part 2: " + totalCards);
    }
}