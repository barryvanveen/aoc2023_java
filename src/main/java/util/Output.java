package util;

public class Output {
    private final boolean debug;

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
}
