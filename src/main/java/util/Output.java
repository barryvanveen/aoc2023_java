package util;

public class Output {
    private final boolean debug;
    private long start;

    public Output(boolean debug) {
        this.debug = debug;
    }

    public void debug(String line) {
        if (this.debug) {
            System.out.println(line);
        }
    }

    public void answer(String line) {
        System.out.println("==> " + line);
    }

    public void startStopwatch() {
        this.start = System.currentTimeMillis();
    }

    public void duration() {
        long finish = System.currentTimeMillis();

        System.out.println("Duration: " + (finish - start) / 1000 + " seconds");
    }
}
