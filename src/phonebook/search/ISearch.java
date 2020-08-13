package phonebook.search;

import java.util.List;

public interface ISearch {
    boolean tryFindElement(List<String> directory, String searchFor);
}
