package phonebook.search;

import java.util.List;

public class BinarySearch implements ISearch {
    @Override
    public boolean tryFindElement(List<String> searchIn, String searchFor) {
        int start = 0;
        int end = searchIn.size() - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            String curEl = searchIn.get(mid);
            if (curEl.equals(searchFor)) {
                return true;
            } else if (curEl.compareTo(searchFor) > 0) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return false;
    }

    @Override
    public String getName() {
        return "binary";
    }
}
