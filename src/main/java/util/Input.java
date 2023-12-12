package util;

import java.util.Arrays;
import java.util.Optional;

public class Input {
    public final int day;
    public final boolean debug;

    public Input(String[] args) {
        // day
        Optional<String> dayParam = Arrays.stream(args).filter((String s) -> s.startsWith("day=")).findFirst();
        if (dayParam.isEmpty()) {
            throw new RuntimeException("Missing required day-parameter");
        }
        day = Integer.parseInt(dayParam.get().substring(4));

        // debug
        debug = Arrays.asList(args).contains("example");
    }

    public String filename() {
        return "day" + this.day + (this.debug ? "_example" : "");
    }
}
