package org.aoc.day3;

import org.aoc.Day;
import util.Converter;
import util.Output;

import java.util.ArrayList;
import java.util.Optional;

public class Main implements Day {
    private final Output out;

    public Main(Output out) {
        this.out = out;
    }

    @Override
    public void part1(ArrayList<String> input) {
        int cols = input.get(0).length();
        int rows = input.size();

        Schema schema = new Schema(cols, rows, input);
        Adjacency adjacency = new Adjacency(cols, rows);

        // first scan, find positions adjacent to special symbols
        for (int r=0; r<rows; r++) {
            for (int c=0; c<cols; c++) {
                char symbol = schema.get(c, r);

                if (Symbol.isSpecial(symbol)) {
                    adjacency.putSymbol(c, r);
                }
            }
        }

        // second scan, find numbers adjacent to symbols
        ArrayList<Integer> values = new ArrayList<>();
        for (int r=0; r<rows; r++) {
            int number = 0;
            boolean isAdjacent = false;
            for (int c=0; c<cols; c++) {
                char symbol = schema.get(c, r);

                if (Symbol.isDigit(symbol)) {
                    number *= 10;
                    number += Converter.CharToInt(symbol);

                    if (adjacency.isAdjacent(c, r)) {
                        isAdjacent = true;
                    }
                    continue;
                }

                // continue if nothing found
                if (number == 0) {
                    continue;
                }

                System.out.print("Number found: " + number);
                if (isAdjacent) {
                    System.out.print(" ADJACENT");
                    values.add(number);
                }
                System.out.print("\n");

                // reset
                number = 0;
                isAdjacent = false;
            }

            // also catch numbers at the end of a row
            if (number > 0) {
                System.out.print("Number found: " + number);
                if (isAdjacent) {
                    System.out.print(" ADJACENT");
                    values.add(number);
                }
                System.out.print("\n");
            }
        }

        Optional<Integer> answer = values.stream().reduce(Integer::sum);

        if (answer.isEmpty()) {
            throw new RuntimeException("Cannot find answer");
        }

        out.answer("part 1: " + answer.get());
    }

    @Override
    public void part2(ArrayList<String> input) {
        int answer = 0;

        out.answer("part 2: " + answer);
    }
}