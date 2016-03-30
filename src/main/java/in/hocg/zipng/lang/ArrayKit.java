package in.hocg.zipng.lang;

import java.util.Arrays;

/**
 * Created by hocgin on 16-3-22.
 */
public class ArrayKit {

    /**
     * 数组arr中是否包含某个i
     *
     * @param arr
     * @param i
     * @param <T>
     * @return
     */
    public static <T> boolean isContain(T[] arr, T i) {
        return Arrays.binarySearch(arr, i) >= 0;
    }

}
