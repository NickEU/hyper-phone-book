package phonebook;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Util {
    static List<String> readNamesFromFile(String fileName, FileFormat format) {
        List<String> names = new ArrayList<>();
        var f = new File(System.getProperty("user.dir")).getParentFile();
        var p = Paths.get(f.getAbsolutePath() + File.separator + fileName);
        try (Scanner sc = new Scanner(p)) {
            while (sc.hasNextLine()) {
                if (format == FileFormat.NAME_WITH_ID) {
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

    static String convertMsToMinSec(long msElapsed) {
        int MS_IN_MIN = 60_000;
        int MS_IN_SEC = 1000;
        long ms = msElapsed;

        long min = ms / MS_IN_MIN;
        ms = ms % MS_IN_MIN;
        long sec = ms / MS_IN_SEC;
        ms = ms % MS_IN_SEC;

        String resultMin = min + " min. ";
        String resultSec = sec + " sec. ";
        String resultMs = ms + " ms.";
        return (resultMin + resultSec + resultMs).trim();
    }

    enum FileFormat {
        NAME_WITH_ID,
        NAME
    }
}
