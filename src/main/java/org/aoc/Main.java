package org.aoc;

import util.Input;
import util.Output;
import util.Reader;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Input in = new Input(args);
        Output out = new Output(in.debug);

        out.debug("Day: " + in.day);
        out.debug("Debug: " + in.debug);
        out.debug("Filename: " + in.filename());

        try {
            Class<?> type = Class.forName("org.aoc.day"+in.day+".Main", true, Main.class.getClassLoader());

            Class<?>[] constructorTypes = { Output.class };
            Object instance = type.getConstructor(constructorTypes).newInstance(out);

            ArrayList<String> input1 = new Reader(in.filename(), 1).get();
            out.debug("Lines for part 1: " + input1.size());

            Class<?>[] partTypes = { ArrayList.class };
            Method part1 = type.getMethod("part1", partTypes);
            part1.invoke(instance, input1);

            ArrayList<String> input2 = new Reader(in.filename(), 2).get();
            out.debug("Lines for part 2: " + input2.size());

            Method part2 = type.getMethod("part2", partTypes);
            part2.invoke(instance, input2);
        } catch (Exception ex) {
            throw new RuntimeException("Could not load class for day " + in.day, ex);
        }
    }
}