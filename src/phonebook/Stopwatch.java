package phonebook;

class Stopwatch {
    private final long start;
    private Long elapsed;
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
}
