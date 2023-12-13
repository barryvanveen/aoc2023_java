package org.aoc.day2;

import java.util.ArrayList;

public class Game {
    public final Integer number;
    public final ArrayList<Draw> draws;
    public final Draw max = new Draw(12, 13, 14);

    public Game(String input) {
        // Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
        String[] parts = input.split(":");

        // parts[0] = "Game 1"
        this.number = Integer.parseInt(parts[0].substring(5));

        // parts[1] = "3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green"
        this.draws = Draw.Draws(parts[1]);
    }

    public Game(Integer number, ArrayList<Draw> draws) {
        this.number = number;
        this.draws = draws;
    }

    public boolean possible() {
        for (Draw draw : this.draws) {
            if (!draw.possible(this.max)) {
                return false;
            }
        }

        return true;
    }

    public Draw minimum() {
        int minRed = 0;
        int minBlue = 0;
        int minGreen = 0;

        for (Draw draw : this.draws) {
            if (draw.red > minRed) minRed = draw.red;
            if (draw.green > minGreen) minGreen = draw.green;
            if (draw.blue > minBlue) minBlue = draw.blue;
        }

        return new Draw(minRed, minGreen, minBlue);
    }
}
