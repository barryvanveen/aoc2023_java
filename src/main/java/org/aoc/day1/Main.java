package org.aoc.day1;

import org.aoc.Day;
import util.Converter;
import util.Output;

import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main implements Day {
    private final Output out;

    public Main(Output out) {
        this.out = out;
    }

    @Override
    public void part1(ArrayList<String> input) {
        Pattern pattern = Pattern.compile("\\d");

        ArrayList<Integer> values = new ArrayList<>();
        for (int i=0; i<input.size(); i++) {
            int sum = 0;

            // find first digit
            Matcher matcher = pattern.matcher(input.get(i));
            if (matcher.find()) {
                out.debug("First digit is " + input.get(i).charAt(matcher.start()));
                sum += Converter.CharToInt(input.get(i).charAt(matcher.start()));
            }

            // find last digit
            String reverse = new StringBuilder(input.get(i)).reverse().toString();
            Matcher matcher2 = pattern.matcher(reverse);
            if (matcher2.find()) {
                out.debug("Last digit is " + reverse.charAt(matcher2.start()));
                sum = (sum * 10) + Converter.CharToInt(reverse.charAt(matcher2.start()));
            }

            // add digits
            out.debug("Sum of " + i + " is " + sum);
            values.add(sum);
        }

        Optional<Integer> answer = values.stream().reduce(Integer::sum);
        if (answer.isEmpty()) {
            throw new RuntimeException("Cannot compute answer");
        }

        out.answer("part 1: " + answer.get());
    }

    @Override
    public void part2(ArrayList<String> input) {
        Pattern pattern = Pattern.compile("(one|two|three|four|five|six|seven|eight|nine|\\d)");
        Pattern reversePattern = Pattern.compile("(eno|owt|eerht|ruof|evif|xis|neves|thgie|enin|\\d)");

        ArrayList<Integer> values = new ArrayList<>();
        for (int i=0; i<input.size(); i++) {
            int sum = 0;

            // find first digit
            Matcher matcher = pattern.matcher(input.get(i));
            if (matcher.find()) {
                sum += matchedInt(matcher.group(0));
            }

            // find last digit
            String reverse = new StringBuilder(input.get(i)).reverse().toString();
            Matcher matcher2 = reversePattern.matcher(reverse);
            if (matcher2.find()) {
                sum = (sum * 10) + matchedInt(matcher2.group(0));
            }

            // add digits
            out.debug("Sum of " + i + " is " + sum);
            values.add(sum);
        }

        Optional<Integer> answer = values.stream().reduce(Integer::sum);
        if (answer.isEmpty()) {
            throw new RuntimeException("Cannot compute answer");
        }

        out.answer("part 2: " + answer.get());
    }

    public Integer matchedInt(String match) {
        return switch (match) {
            case "one", "eno" -> {
                out.debug("Match is <one>");
                yield 1;
            }
            case "two", "owt" -> {
                out.debug("Match is <two>");
                yield 2;
            }
            case "three", "eerht" -> {
                out.debug("Match is <three>");
                yield 3;
            }
            case "four", "ruof" -> {
                out.debug("Match is <four>");
                yield 4;
            }
            case "five", "evif" -> {
                out.debug("Match is <five>");
                yield 5;
            }
            case "six", "xis" -> {
                out.debug("Match is <six>");
                yield 6;
            }
            case "seven", "neves" -> {
                out.debug("Match is <seven>");
                yield 7;
            }
            case "eight", "thgie" -> {
                out.debug("Match is <eight>");
                yield 8;
            }
            case "nine", "enin" -> {
                out.debug("Match is <nine>");
                yield 9;
            }
            default -> {
                out.debug("Match is " + match);
                yield Integer.parseInt(match);
            }
        };
    }
}