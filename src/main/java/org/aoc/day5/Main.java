package org.aoc.day5;

import org.aoc.Day;
import util.Output;

import java.util.*;

public class Main implements Day {
    private final Output out;

    public Main(Output out) {
        this.out = out;
    }

    @Override
    public void part1(ArrayList<String> input) {
        List<Long> seeds = this.getSeeds(input);
        List<Mapper> mappers = this.getMappers(input);

        // move seed through mappers
        List<Long> locations = new ArrayList<>();
        for (Long s : seeds) {
            for (Mapper m : mappers) {
                s = m.map(s);
            }
            locations.add(s);
            this.out.debug("Destination: " + s);
        }
        Long answer = locations.stream().reduce(Long::min).orElseThrow();

        out.answer("part 1: " + answer);
    }

    @Override
    public void part2(ArrayList<String> input) {
        this.out.startStopwatch();

        List<Long> seeds = this.getSeeds(input);
        List<Mapper> mappers = this.getMappers(input);

        // move seed through mappers
        Long answer = null;
        for (int i=0; i<seeds.size(); i+=2) {
            Long start = seeds.get(i);
            Long range = seeds.get(i+1);
            Long end = start + range;
            for (Long j=start; j<end; j++) {
                Long seed = j;
                this.out.debug("Mapping: " + seed);
                for (Mapper m : mappers) {
                    seed = m.map(seed);
                }
                this.out.debug("Destination: " + seed);

                if (answer == null || seed < answer) {
                    answer = seed;
                }
            }
        }

        out.answer("part 2: " + answer);
        out.duration();
    }

    public List<Long> getSeeds(List<String> input) {
        return Arrays.stream(input.get(0).substring(7).trim().split("([\\s]+)"))
                .map(String::trim).map(Long::parseLong).toList();
    }

    public List<Mapper> getMappers(List<String> input) {
        List<Mapper> mappers = new ArrayList<>();
        boolean activeMapper = false;
        Mapper mapper = null;
        for (int i = 2; i<input.size(); i++) {
            // mapper start
            if (!activeMapper) {
                activeMapper = true;
                mapper = new Mapper();
                continue;
            }

            // mapper contents
            if (!input.get(i).isEmpty()) {
                Map map = new Map(input.get(i));
                mapper.addMap(map);
                continue;
            }

            // empty line, so the end of a mapper
            mappers.add(mapper);
            activeMapper = false;
            mapper = null;
        }

        // add the last mapper
        mappers.add(mapper);

        return mappers;
    }
}