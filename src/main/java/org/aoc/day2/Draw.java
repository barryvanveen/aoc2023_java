package org.aoc.day2;

import java.util.ArrayList;

public class Draw {
    public Integer red;
    public Integer green;
    public Integer blue;

    public Draw(String input) {
        this.red = 0;
        this.green = 0;
        this.blue = 0;

        // input = "1 red, 2 green, 6 blue"
        String[] cubes = input.trim().split(",");

        for (String cube : cubes) {
            String[] details = cube.trim().split(" ");
            int amount = Integer.parseInt(details[0]);
            switch (details[1]) {
                case "red":
                    this.red = amount;
                    break;
                case "green":
                    this.green = amount;
                    break;
                case "blue":
                    this.blue = amount;
                    break;
                default:
                    throw new RuntimeException("Unknown color " + details[1]);
            }
        }
    }

    public Draw(Integer red, Integer green, Integer blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public boolean possible(Draw max) {
        return (this.red <= max.red && this.green <= max.green && this.blue <= max.blue);
    }

    public Integer power() {
        return this.red * this.green * this.blue;
    }

    public static ArrayList<Draw> Draws(String input) {
        ArrayList<Draw> list = new ArrayList<>();

        String[] draws = input.split("; ");
        for (String draw : draws) {
            list.add(new Draw(draw));
        }

        return list;
    }
}
