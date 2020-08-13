package phonebook;

public class Stopwatch {
    private long start;
    private long elapsed;
    private boolean isStopped;
    private final long timeLimit;

    Stopwatch() {
        this(10_000);
    }

    public Stopwatch(long timeLimit) {
        start = System.currentTimeMillis();
        this.timeLimit = timeLimit;
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

    public boolean wentOverLimit() {
        return getElapsed() >= timeLimit;
    }
}
