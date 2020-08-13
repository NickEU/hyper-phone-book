package phonebook;

import phonebook.search.BinarySearch;
import phonebook.search.ISearch;
import phonebook.search.JumpSearch;
import phonebook.search.LinearSearch;
import phonebook.sort.BubbleSort;
import phonebook.sort.ISortingAlgorithm;
import phonebook.sort.QuickSort;

import java.util.ArrayList;
import java.util.List;

class PhoneBook {
    private final List<String> unsortedDirectory
        = Util.readNamesFromFile("directory.txt", Util.FileFormat.NAME_WITH_ID);
    private List<String> sortedDirectory;
    private final List<String> lookingFor
        = Util.readNamesFromFile("find.txt", Util.FileFormat.NAME);
    private final Stopwatch lastOperationTimer = new Stopwatch();
    private long timeLimitForSorting = 5000;

    String linearSearch() {
        var linearAlgo = new LinearSearch();
        String result = String.format("Start searching (%s search)...\n", linearAlgo.getName())
            + findUsingAlgorithm(linearAlgo, unsortedDirectory)
            + reportTimeTaken(lastOperationTimer.stop());
        timeLimitForSorting = lastOperationTimer.getElapsed() * 10;
        return result;
    }

    private String findUsingAlgorithm(ISearch searchAlgorithm, List<String> searchIn) {
        lastOperationTimer.reset();
        long namesFound = 0;
        for (String searchFor : lookingFor) {
            if (searchAlgorithm.tryFindElement(searchIn, searchFor)) {
                namesFound++;
            }
        }
        return "Found " + namesFound + " / " + lookingFor.size() + " entries. ";
    }

    String jumpSearch() {
        return sortAndSearch(new JumpSearch(), new BubbleSort());
    }

    String binarySearch() {
        return sortAndSearch(new BinarySearch(), new QuickSort());
    }

    private String sortAndSearch(ISearch searchAlgo, ISortingAlgorithm sortingAlgo) {
        String header = String.format("Start searching (%s sort + %s search)...\n",
            sortingAlgo.getName(), searchAlgo.getName());

        boolean sortSuccess = sortingAlgo.trySort(lastOperationTimer.reset(), timeLimitForSorting, prepareSortedDir());
        String reportSortResult = "Sorting time: " + Util.convertMsToMinSec(lastOperationTimer.stop())
            + (sortSuccess ? "\n" : " - STOPPED, moved to linear search\n");
        long sortingTime = lastOperationTimer.getElapsed();

        String reportSearch = sortSuccess
            ? findUsingAlgorithm(searchAlgo, sortedDirectory)
            : findUsingAlgorithm(new LinearSearch(), unsortedDirectory);
        long searchTime = lastOperationTimer.getElapsed();

        return header + reportSearch + reportTimeTaken(sortingTime + searchTime)
            + reportSortResult + "Searching time: " + Util.convertMsToMinSec(searchTime) + "\n";
    }

    private String reportTimeTaken(long ms) {
        return "Time taken: " + Util.convertMsToMinSec(ms) + "\n";
    }

    private List<String> prepareSortedDir() {
        sortedDirectory = new ArrayList<>(unsortedDirectory);
        return sortedDirectory;
    }
}
