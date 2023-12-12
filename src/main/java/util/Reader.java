package util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Reader {
    ArrayList<String> lines = new ArrayList<>();

    public Reader(String filename, Integer part) {
        InputStream stream;

        // first try to load a specific resource, like day1_example_1
        // where "day1_example" would be the filename
        // and we get "1" would be the part
        stream = Input.class.getClassLoader().getResourceAsStream(filename + "_" + part);

        // otherwise, load a more general input file
        if (stream == null) {
            stream = Input.class.getClassLoader().getResourceAsStream(filename);
        }

        // throw error if this fails
        if (stream == null) {
            throw new RuntimeException("Cannot open resource with filename " + filename + " and part " + part);
        }

        Scanner sc = new Scanner(stream);
        sc.useDelimiter("\\Z");

        while (sc.hasNextLine()) {
            lines.add(sc.nextLine());
        }
    }

    public ArrayList<String> get() {
        return this.lines;
    }
}
