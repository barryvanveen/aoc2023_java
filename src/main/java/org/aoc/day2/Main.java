package org.aoc.day2;

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
        ArrayList<Game> games = new ArrayList<>();
        for (String s : input) {
            games.add(new Game(s));
        }

        int answer = 0;
        for (Game game : games) {
            if (game.possible()) {
                answer += game.number;
                this.out.debug("Game " + game.number + " is possible");
            } else {
                this.out.debug("Game " + game.number + " is NOT possible");
            }
        }

        out.answer("part 1: " + answer);
    }

    @Override
    public void part2(ArrayList<String> input) {
        ArrayList<Game> games = new ArrayList<>();
        for (String s : input) {
            games.add(new Game(s));
        }

        int answer = 0;
        for (Game game : games) {
            Draw minimum = game.minimum();
            this.out.debug("Power of game " + game.number + ": " + minimum.power());
            answer += minimum.power();
        }

        out.answer("part 2: " + answer);
    }
}