package org.aoc.day3;

import org.aoc.Day;
import util.Converter;
import util.Output;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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

                if (isAdjacent) {
                    values.add(number);
                }

                // reset
                number = 0;
                isAdjacent = false;
            }

            // also catch numbers at the end of a row
            if (number > 0) {
                if (isAdjacent) {
                    values.add(number);
                }
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
        int cols = input.get(0).length();
        int rows = input.size();

        Schema schema = new Schema(cols, rows, input);
        AsteriskAdjacency adjacency = new AsteriskAdjacency(cols, rows);

        // first scan, find positions adjacent to *
        for (int r=0; r<rows; r++) {
            for (int c=0; c<cols; c++) {
                char symbol = schema.get(c, r);

                if (symbol == '*') {
                    adjacency.putSymbol(c, r);
                }
            }
        }

        // second scan, find numbers and store them with an index
        // because any number might occur more than once
        // and keep a list of * adjacent to each index
        HashMap<Integer, Integer> indexToNumber = new HashMap<>();
        HashMap<Integer, HashSet<Integer>> indexToAsterisk = new HashMap<>();
        int index = 0;
        for (int r=0; r<rows; r++) {
            int number = 0;
            HashSet<Integer> adjacent = new HashSet<>();

            for (int c=0; c<cols; c++) {
                char symbol = schema.get(c, r);

                if (Symbol.isDigit(symbol)) {
                    number *= 10;
                    number += Converter.CharToInt(symbol);

                    adjacent.addAll(adjacency.getAdjacent(c, r));
                    continue;
                }

                // continue if nothing found
                if (number == 0) {
                    continue;
                }

                indexToNumber.put(index, number);
                indexToAsterisk.put(index, adjacent);
                index++;

                // reset
                number = 0;
                adjacent = new HashSet<>();
            }

            // also catch numbers at the end of a row
            if (number > 0) {
                indexToNumber.put(index, number);
                indexToAsterisk.put(index, adjacent);
                index++;
            }
        }

        for (int i : indexToAsterisk.keySet()) {
            System.out.print(i + ": ");
            for (int j : indexToAsterisk.get(i)) {
                System.out.print(j + ", ");
            }
            System.out.print("\n");
        }

        // per *, list all the numbers adjacent
        HashMap<Integer, HashSet<Integer>> asteriskToNumbers = new HashMap<>();
        for (int i : indexToAsterisk.keySet()) {
            for (int j : indexToAsterisk.get(i)) {
                if (!asteriskToNumbers.containsKey(j)) {
                    asteriskToNumbers.put(j, new HashSet<>());
                }
                asteriskToNumbers.get(j).add(indexToNumber.get(i));
            }
        }

        for (int i : asteriskToNumbers.keySet()) {
            System.out.print(i + ": ");
            for (int j : asteriskToNumbers.get(i)) {
                System.out.print(j + ", ");
            }
            System.out.print("\n");
        }

        // only use * with 2 adjacent numbers
        BigDecimal answer = new BigDecimal(0);
        for (int i : asteriskToNumbers.keySet()) {
            HashSet<Integer> asteriskToNumber = asteriskToNumbers.get(i);
            if (asteriskToNumber.size() == 2) {

                // compute power of adjacent numbers
                answer = answer.add(
                    asteriskToNumber.stream().map(BigDecimal::new).reduce(BigDecimal::multiply).orElseThrow()
                );
            }
        }

        out.answer("part 2: " + answer);
    }
}