package phonebook.search;

import java.util.List;

public class JumpSearch implements ISearch {
    @Override
    public boolean tryFindElement(List<String> directory, String searchFor) {
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
}
