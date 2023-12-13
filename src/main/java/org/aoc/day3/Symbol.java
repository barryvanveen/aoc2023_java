package org.aoc.day3;

import util.Converter;

public class Symbol {
    public static boolean isSpecial(char s) {
        int asNumber = Converter.CharToInt(s);

        return !(s == '.' || (asNumber >= 0 && asNumber <= 9));
    }

    public static boolean isDigit(char s) {
        int asNumber = Converter.CharToInt(s);

        return asNumber >= 0 && asNumber <= 9;
    }
}
