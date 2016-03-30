package in.hocg.zipng;

import java.io.File;
import java.util.Arrays;

/**
 * Created by hocgin on 16-3-22.
 */
public class ImageFile {
    private String[] support = new String[]{"png", "jpg"};
    private File src;

    public ImageFile(File src) throws Exception {
        if (_isSupport(src)) {
            this.src = src;
        } else {
            throw new Exception("not support image");
        }
    }

    private boolean _isSupport(File src) {
        String fileName = src.getName();
        if (Arrays.binarySearch(support, fileName.substring(fileName.lastIndexOf("."))) != -1) {
            return true;
        }
        return false;
    }

    public File getSrc() {
        return src;
    }
}
