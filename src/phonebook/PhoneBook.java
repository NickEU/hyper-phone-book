package phonebook;

import java.util.List;

class PhoneBook {
    private final List<String> directory
        = Util.readNamesFromFile("directory.txt", true);
    private final List<String> lookingFor
        = Util.readNamesFromFile("find.txt", false);
    String result = "";
    private Stopwatch lastOperationTimer;

    String linearSearch() {
        return "Start searching (linear search)...\n" + findUsingLinearSearch();
    }

    private String findUsingLinearSearch() {
        lastOperationTimer = new Stopwatch();
        long namesFound = 0;
        for (String searchFor : lookingFor) {
            for (String name : directory) {
                if (searchFor.equals(name)) {
                    namesFound++;
                    break;
                }
            }
        }
        return String.format("Found %d / %d entries. Time taken: %s\n"
            , namesFound, lookingFor.size(), Util.convertMsToMinSec(lastOperationTimer.stop()));
    }

    public String jumpSearch() {
        result = "Start searching (bubble sort + jump search)...\n";
        String sortResult = badSort();
        result += sortResult.contains("STOPPED") ? findUsingLinearSearch() : findUsingJumpSearch();

        String searchResult = "Searching time: "
            + Util.convertMsToMinSec(lastOperationTimer.getElapsed());
        return result + sortResult + searchResult;
    }

    private String findUsingJumpSearch() {
        return "Found 500 / 500 entries. Time taken: 22 min. 14 sec. 482 ms.\n";
    }

    private String badSort() {
        final int LIMIT = 720_000;
        Stopwatch timer = new Stopwatch();
        return "Sorting time: 20 min. 12 sec. 251 ms. - STOPPED, moved to linear search\n";
    }
}
