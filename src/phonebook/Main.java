package phonebook;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        long msStart = System.currentTimeMillis();
        var directory = Util.readNamesFromFile("directory.txt", true);
        var lookingFor = Util.readNamesFromFile("find.txt", false);
        System.out.println("Start searching...");
        long namesFound = findUsingLinearSearch(lookingFor, directory);
        long msEnd = System.currentTimeMillis();
        System.out.printf("Found %d / %d entries. Time taken: %s\n"
            , namesFound, lookingFor.size(), getFormattedTimeOutput(msEnd - msStart));
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

    private static String getFormattedTimeOutput(long msElapsed) {
        int MS_IN_MIN = 60_000;
        int MS_IN_SEC = 1000;
        long ms = msElapsed;

        long min = ms / MS_IN_MIN;
        ms = ms % MS_IN_MIN;
        long sec = ms / MS_IN_SEC;
        ms = ms % MS_IN_SEC;

        String resultMin = min + "min. ";
        String resultSec = sec + "sec. ";
        String resultMs = ms + "ms.";
        return (resultMin + resultSec + resultMs).trim();
    }
}
