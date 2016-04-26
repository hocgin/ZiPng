package in.hocg.zipng.lang;

import java.util.Objects;

/**
 * Created by hocgin on 16-4-26.
 */
public class LangKit {

    public static <T> T ifNull(T t, T def) {
        if (null == t) {
            return def;
        }
        return t;
    }

    public static String ifEmpty(String t, String def) {
        if (isEmpty(t)) {
            return def;
        }
        return t;
    }

    public static boolean isEmpty(String t) {
        if (null == t || Objects.equals(t, "")) {
            return true;
        }
        return false;
    }
}
