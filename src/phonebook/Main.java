package phonebook;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        long before = System.currentTimeMillis();
        var names = Util.readNamesFromFile("directory.txt", true);
        var lookingFor = Util.readNamesFromFile("find.txt", false);
        System.out.println("Start searching...");
        long namesFound = findUsingLinearSearch(lookingFor, names);
        long after = System.currentTimeMillis();
        System.out.printf("Found %d / %d entries. Time taken: %s\n"
            , namesFound, lookingFor.size(), getFormattedTimeOutput(after - before));
    }

    private static long findUsingLinearSearch(List<String> namesToLookFor, List<String> directory) {
        long namesFound = 0;
        for (String searchFor : namesToLookFor) {
            for (String name : directory) {
                if (searchFor.equals(name)) {
                    namesFound++;
                    break;
                }
            }
        }
        return namesFound;
    }

    private static String getFormattedTimeOutput(long elapsed) {
        int MS_IN_MIN = 60_000;
        int MS_IN_SEC = 1000;
        long leftover = elapsed;

        long min = leftover / MS_IN_MIN;
        leftover = leftover % MS_IN_MIN;
        long sec = leftover / MS_IN_SEC;
        leftover = leftover % MS_IN_SEC;

        String resultMin = min + "min. ";
        String resultSec = sec + "sec. ";
        String resultMs = leftover + "ms.";
        return (resultMin + resultSec + resultMs).trim();
    }
}
