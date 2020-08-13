package phonebook;

class Stopwatch {
    private long start;
    private long elapsed;
    private boolean isStopped;

    Stopwatch() {
        start = System.currentTimeMillis();
    }

    long getElapsed() {
        if (!isStopped) {
            elapsed = System.currentTimeMillis() - start;
        }
        return elapsed;
    }

    long stop() {
        if (!isStopped) {
            isStopped = true;
            elapsed = System.currentTimeMillis() - start;
        }

        return elapsed;
    }

    Stopwatch reset() {
        start = System.currentTimeMillis();
        isStopped = false;
        return this;
    }
}
