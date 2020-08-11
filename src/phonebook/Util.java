package phonebook;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Util {
    static List<String> readNamesFromFile(String fileName, boolean formatHasId) {
        List<String> names = new ArrayList<>();
        var f = new File(System.getProperty("user.dir")).getParentFile();
        try (Scanner sc = new Scanner(Paths.get(f.getAbsolutePath() + File.separator + fileName))) {
            while (sc.hasNextLine()) {
                if (formatHasId) {
                    String id = sc.next();
                }

                String name = sc.nextLine().trim();
                names.add(name);
            }
        }
        catch (IOException e) {
            System.out.println("ERROR. Cannot open " + e.getMessage());
        }
        return names;
    }
}
