package org.aoc.day5;

import java.util.ArrayList;
import java.util.List;

public class Mapper {
    private final List<Map> maps = new ArrayList<>();

    public void addMap(Map map) {
        this.maps.add(map);
    }

    public Long map(Long input ) {
        for (Map m : this.maps) {
            if (m.inRange(input)) {
                return m.map(input);
            }
        }

        return input;
    }
}
