package in.hocg.zipng.lang;

/**
 * Created by hocgin on 16-3-30.
 */
public class ColorKit {

    public static String red(Object s) {
        return String.format("\033[31m%s\033[0m", s);
    }

    public static String yellow(Object s) {
        return String.format("\033[33m%s\033[0m", s);
    }
}
