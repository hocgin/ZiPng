package in.hocg.zipng.lang;

import in.hocg.zipng.lang.inter.Callback;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

/**
 * Created by hocgin on 16-3-22.
 */
public class FileKit {

    /**
     * 遍历处理文件夹以内所有文件
     *
     * @param dir      BaseDir
     * @param callback 处理器
     */
    public static void each(File dir, Callback<File> callback) {
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                each(file, callback);
            }
            callback.invoke(file);
        }
    }

    /**
     * 创建新文件，如果父目录不存在，也一并创建。可接受 null 参数
     *
     * @param f 文件对象
     * @return false，如果文件已存在。 true 创建成功
     * @throws IOException
     */
    public static boolean createNewFile(File f) throws IOException {
        if (null == f || f.exists())
            return false;
        makeDir(f.getParentFile());
        return f.createNewFile();
    }

    /**
     * 将文件改名
     *
     * @param src     文件
     * @param newName 新名称
     * @return 改名是否成功
     */
    public static boolean rename(File src, String newName) {
        if (src == null || newName == null)
            return false;
        if (src.exists()) {
            File newFile = new File(src.getParent() + "/" + newName);
            if (newFile.exists())
                return false;
            makeDir(newFile.getParentFile());
            return src.renameTo(newFile);
        }
        return false;
    }

    /**
     * 创建新目录，如果父目录不存在，也一并创建。可接受 null 参数
     *
     * @param dir 目录对象
     * @return false，如果目录已存在。 true 创建成功
     * @throws IOException
     */
    public static boolean makeDir(File dir) {
        if (null == dir || dir.exists())
            return false;
        return dir.mkdirs();
    }

    /**
     * @param path 路径
     * @return 父路径
     */
    public static String getParent(String path) {
        if (StringKit.isBlank(path))
            return path;
        int pos = path.replace('\\', '/').lastIndexOf('/');
        if (pos > 0)
            return path.substring(0, pos);
        return "/";
    }

    /**
     * 将文件移动到新的位置
     *
     * @param src    原始文件
     * @param target 新文件
     * @return 移动是否成功
     * @throws IOException
     */
    public static boolean move(File src, File target) throws IOException {
        if (src == null || target == null)
            return false;
        makeDir(target.getParentFile());
        if (src.isDirectory()) {
            src = new File(src.getCanonicalPath() + File.separator);
            target = new File(target.getCanonicalPath() + File.separator);
        }
        return src.renameTo(target);
    }

    /**
     * 获取文件后缀名，不包括 '.'，如 'abc.gif','，则返回 'gif'
     *
     * @param path 文件路径
     * @return 文件后缀名
     */
    public static String getSuffixName(String path) {
        if (null == path)
            return null;
        int p0 = path.lastIndexOf('.');
        int p1 = path.lastIndexOf('/');
        if (-1 == p0 || p0 < p1)
            return "";
        return path.substring(p0 + 1);
    }

    /**
     * 获取文件的大小并转化带单位
     * @param f
     * @return
     */
    public static String getFileSize(File f) {
        return getFileSize(f.length());
    }
    /**
     * 获取文件的大小并转化带单位
     * @param fl
     * @return
     */
    public static String getFileSize(long fl) {
        DecimalFormat df = new DecimalFormat("#.00");
        if (fl < 1024) {
            return df.format((double) fl) + "B";
        } else if (fl < 1048576) {
            return df.format((double) fl / 1024) + "K";
        } else if (fl < 1073741824) {
            return df.format((double) fl / 1048576) + "M";
        } else {
            return df.format((double) fl / 1073741824) + "G";
        }
    }

    // ------------------------


}
