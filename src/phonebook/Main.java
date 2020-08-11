package phonebook;

public class Main {
    public static void main(String[] args) {
        var names = Util.readNamesFromFile("directory.txt", true);
        var lookingFor = Util.readNamesFromFile("find.txt", false);
        System.out.println("Start searching...");
        long before = System.currentTimeMillis();
        long namesFound = lookingFor.stream().filter(names::contains).count();
        long after = System.currentTimeMillis();
        System.out.printf("Found %d/%d entries. Time taken: %s\n"
            , namesFound, lookingFor.size(), getFormattedTimeOutput(before, after));
    }

    private static String getFormattedTimeOutput(long before, long after) {
        // convert ms into a human format ( min, sec, ms)
        return String.valueOf(after - before);
    }
}
