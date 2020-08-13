package phonebook;

import phonebook.search.ISearch;
import phonebook.search.JumpSearch;
import phonebook.search.LinearSearch;

import java.util.List;

class PhoneBook {
    private final List<String> directory
        = Util.readNamesFromFile("directory.txt", Util.FileFormat.NAME_WITH_ID);
    private final List<String> lookingFor
        = Util.readNamesFromFile("find.txt", Util.FileFormat.NAME);
    private final Stopwatch lastOperationTimer = new Stopwatch();
    private long bubbleSortTimeLimit = 5000;

    String linearSearch() {
        String result = "Start searching (linear search)...\n" + findUsingAlgorithm(new LinearSearch());
        result += String.format("Time taken: %s\n", Util.convertMsToMinSec(lastOperationTimer.stop()));
        bubbleSortTimeLimit = lastOperationTimer.getElapsed() * 10;
        return result;
    }

    private String findUsingAlgorithm(ISearch searchAlgorithm) {
        lastOperationTimer.reset();
        long namesFound = 0;
        for (String searchFor : lookingFor) {
            if (searchAlgorithm.tryFindElement(directory, searchFor)) {
                namesFound++;
            }
        }
        return "Found " + namesFound + " / " + lookingFor.size() + " entries. ";
    }

    public String jumpSearch() {
        boolean bubbleSortSuccess = SortBubble.trySort(lastOperationTimer.reset(), bubbleSortTimeLimit, directory);
        String reportSortResult = "Sorting time: " + Util.convertMsToMinSec(lastOperationTimer.stop())
            + (bubbleSortSuccess ? "\n" : " - STOPPED, moved to linear search\n");

        long sortingTime = lastOperationTimer.getElapsed();
        String reportSearch = bubbleSortSuccess
            ? findUsingAlgorithm(new JumpSearch())
            : findUsingAlgorithm(new LinearSearch());
        long searchTime = lastOperationTimer.getElapsed();
        String reportTotalTime = "Time taken: " + Util.convertMsToMinSec(sortingTime + searchTime) + "\n";
        return "Start searching (bubble sort + jump search)...\n" + reportSearch + reportTotalTime
            + reportSortResult + "Searching time: " + Util.convertMsToMinSec(searchTime);
    }
}
