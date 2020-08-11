package phonebook;

public class Main {
    public static void main(String[] args) {
        var names = Util.readNamesFromFile("directory.txt", true);
        var lookingFor = Util.readNamesFromFile("find.txt", false);
        System.out.println("Start searching...");
        long before = System.currentTimeMillis();
        long namesFound = lookingFor.stream().filter(names::contains).count();
        long after = System.currentTimeMillis();
        System.out.printf("Found %d / %d entries. Time taken: %s\n"
            , namesFound, lookingFor.size(), getFormattedTimeOutput(after - before));
    }

    private static String getFormattedTimeOutput(long elapsed) {
        int MS_IN_MIN = 60_000;
        int MS_IN_SEC = 1000;
        long leftover = elapsed;

        long min = leftover / MS_IN_MIN;
        leftover = leftover % MS_IN_MIN;
        long sec = leftover / MS_IN_SEC;
        leftover = leftover % MS_IN_SEC;

        String resultMin = min > 0 ? min + "min. " : "";
        String resultSec = sec > 0 ? sec + "sec. " : "";
        String resultMs = leftover > 0 ? leftover + "ms." : "";
        return (resultMin + resultSec + resultMs).trim();
    }
}
