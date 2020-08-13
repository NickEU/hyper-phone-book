package phonebook.search;

import java.util.List;

public class JumpSearch implements ISearch {
    @Override
    public boolean tryFindElement(List<String> searchIn, String searchFor) {
        if (searchIn.get(0).equals(searchFor)) {
            return true;
        }
        int blockSize = (int) Math.sqrt(searchIn.size());
        int i = blockSize;
        while (i < searchIn.size()) {
            if (searchIn.get(i).equals(searchFor)) {
                return true;
            }

            if (searchIn.get(i).compareTo(searchFor) < 0) {
                i += blockSize;
            } else {
                int lowerBound = i - blockSize + 1;
                while (i-- >= lowerBound) {
                    if (searchIn.get(i).compareTo(searchFor) == 0) {
                        return true;
                    }
                }
                return false;
            }

            if (i >= searchIn.size()) {
                i = searchIn.size() - 1;
            }
        }

        return false;
    }
}
