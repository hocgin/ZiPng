package in.hocg.zipng.lang;

/**
 * Created by hocgin on 16-3-23.
 */
public class StringKit {

    /**
     * 如果此字符串为 null 或者全为空白字符，则返回 true
     *
     * @param cs 字符串
     * @return 如果此字符串为 null 或者全为空白字符，则返回 true
     */
    public static boolean isBlank(CharSequence cs) {
        return cs == null || "".equals(cs);
    }
}
