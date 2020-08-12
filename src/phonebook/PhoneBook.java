package phonebook;

import java.util.List;

class PhoneBook {
    private final List<String> directory
        = Util.readNamesFromFile("directory.txt", true);
    //= Util.readNamesFromFile("find.txt", false);
    private final List<String> lookingFor
        = Util.readNamesFromFile("find.txt", false);
    private Stopwatch lastOperationTimer;
    private long bubbleSortTimeLimit;

    String linearSearch() {
        String result = "Start searching (linear search)...\n" + findUsingLinearSearch(true);
        bubbleSortTimeLimit = lastOperationTimer.getElapsed() * 10;
        return result;
    }

    private String findUsingLinearSearch(boolean includeTime) {
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
        if (includeTime) {
            return String.format("Found %d / %d entries. Time taken: %s\n"
                , namesFound, lookingFor.size(), Util.convertMsToMinSec(lastOperationTimer.stop()));
        } else {
            return "Found " + namesFound + " / " + lookingFor.size() + " entries. ";
        }
    }

    public String jumpSearch() {
        String reportSortResult = bubbleSort();
        long sortingTime = lastOperationTimer.getElapsed();
        String reportSearch = reportSortResult.contains("STOPPED")
            ? findUsingLinearSearch(false)
            : findUsingJumpSearch();
        long searchTime = lastOperationTimer.getElapsed();
        String reportTotalTime = "Time taken: " + Util.convertMsToMinSec(sortingTime + searchTime) + "\n";
        return "Start searching (bubble sort + jump search)...\n" + reportSearch + reportTotalTime
            + reportSortResult + "Searching time: " + Util.convertMsToMinSec(searchTime);
    }

    private String findUsingJumpSearch() {
        lastOperationTimer = new Stopwatch();
        int namesFound = 0;
        for (String searchFor : lookingFor) {
            if (foundElUsingJump(searchFor)) {
                namesFound++;
            }
        }
        return "Found " + namesFound + " / " + lookingFor.size() + " entries. ";
    }

    private boolean foundElUsingJump(String searchFor) {
        if (directory.get(0).equals(searchFor)) {
            return true;
        }
        int blockSize = (int) Math.sqrt(directory.size());
        int i = blockSize;
        while (i < directory.size()) {
            if (directory.get(i).equals(searchFor)) {
                return true;
            }

            if (directory.get(i).compareTo(searchFor) < 0) {
                i += blockSize;
            } else {
                int lowerBound = i - blockSize + 1;
                while (i-- >= lowerBound) {
                    if (directory.get(i).compareTo(searchFor) == 0) {
                        return true;
                    }
                }
                return false;
            }

            if (i >= directory.size()) {
                i = directory.size() - 1;
            }
        }

        return false;
    }

    private String bubbleSort() {
        lastOperationTimer = new Stopwatch();
        for (int i = 0; i < directory.size(); i++) {
            for (int j = 0; j < directory.size() - 1; j++) {
                if (directory.get(j).compareTo(directory.get(j + 1)) > 0) {
                    swap(j, j + 1);
                }
            }
            if (lastOperationTimer.getElapsed() > bubbleSortTimeLimit) {
                return "Sorting time: " + Util.convertMsToMinSec(lastOperationTimer.stop())
                    + " - STOPPED, moved to linear search\n";
            }
        }
        return "Sorting time: " + Util.convertMsToMinSec(lastOperationTimer.stop()) + "\n";
    }

    private void swap(int j, int i) {
        String temp = directory.get(j);
        directory.set(j, directory.get(i));
        directory.set(i, temp);
    }
}
