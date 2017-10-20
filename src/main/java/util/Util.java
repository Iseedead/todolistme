package util;

import java.util.LinkedList;

public abstract class Util {

    public static boolean isAlphabetical(LinkedList<String> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i).compareToIgnoreCase(list.get(i + 1)) > 0) {
                return false;
            }
        }
        return true;
    }
}
