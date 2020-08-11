package phonebook;

class Stopwatch {
    private final long start;
    private Long elapsed;

    Stopwatch() {
        start = System.currentTimeMillis();
    }

    long getElapsed() {
        if (elapsed == null) {
            elapsed = System.currentTimeMillis() - start;
        }
        return elapsed;
    }
}
