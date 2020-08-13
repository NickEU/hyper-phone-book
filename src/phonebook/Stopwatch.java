package phonebook;

public class Stopwatch {
    private long start;
    private long elapsed;
    private boolean isStopped;

    Stopwatch() {
        start = System.currentTimeMillis();
    }

    public long getElapsed() {
        if (!isStopped) {
            elapsed = System.currentTimeMillis() - start;
        }
        return elapsed;
    }

    public long stop() {
        if (!isStopped) {
            isStopped = true;
            elapsed = System.currentTimeMillis() - start;
        }

        return elapsed;
    }

    public Stopwatch reset() {
        start = System.currentTimeMillis();
        isStopped = false;
        return this;
    }
}
