package org.aoc.day6;

import org.aoc.Day;
import util.Output;

import java.util.ArrayList;
import java.util.List;

public class Main implements Day {
    private final Output out;

    public Main(Output out) {
        this.out = out;
    }

    @Override
    public void part1(ArrayList<String> input) {
        String[] times = input.get(0).substring("Time:".length()).trim().split("([\\s]+)");
        String[] records = input.get(1).substring("Distance:".length()).trim().split("([\\s]+)");

        List<Race> races = new ArrayList<>();
        for (int i=0; i<times.length; i++) {
            races.add(new Race(Long.parseLong(times[i]), Long.parseLong(records[i])));
        }

        List<Long> waysToWin = new ArrayList<>();
        for (Race r : races) {
            Long wins = r.waysToWin();
            waysToWin.add(wins);
            out.debug("Ways to win: " + wins);
        }

        Long answer = waysToWin.stream().reduce((Long a, Long b) -> a * b).orElseThrow();
        out.answer("part 1: " + answer);
    }

    @Override
    public void part2(ArrayList<String> input) {
        String time = input.get(0).substring("Time:".length()).replaceAll("([\\s]+)", "");
        String record = input.get(1).substring("Distance:".length()).replaceAll("([\\s]+)", "");

        Race race = new Race(Long.parseLong(time), Long.parseLong(record));
        Long answer = race.waysToWin();

        out.answer("part 2: " + answer);
    }
}