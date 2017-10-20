package util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public abstract class Util {

    public static boolean isAlphabetical(LinkedList<String> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i).compareToIgnoreCase(list.get(i + 1)) > 0) {
                return false;
            }
        }
        return true;
    }

    public static String getTomorrow() {
        LocalDate localDate = LocalDate.now(ZoneId.of("GMT+02:00")).plusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE dd MMMM YYYY");
        List<String> list = new LinkedList<>(Arrays.asList(localDate.format(formatter).split(" ")));
        list.remove(0);
        return String.join(" ", list);
    }
}
