package in.hocg.zipng;

import com.tinify.Tinify;
import in.hocg.zipng.lang.ArrayKit;
import in.hocg.zipng.lang.ColorKit;
import in.hocg.zipng.lang.FileKit;
import in.hocg.zipng.lang.StringKit;
import in.hocg.zipng.lang.inter.Callback;

import java.io.File;
import java.io.IOException;

/**
 * Created by hocgin on 16-3-22.
 */
public class Main {

    private static int successCount = 0;
    private static long beforeZipLength = 0;
    private static long afterZipLength = 0;


    /**
     * 2个参数，[from, to]
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Author: hocgin");
        System.out.println("Email: hocgin@gmail.com");
        System.out.println("Blog: http://hocg.in");
        System.out.println("Use: java -jar xx.jar [fromPath toPath]");
        System.out.println("");
        if (args == null || args.length == 0) {
            System.out.println("[WARN] Please bring parameters -> [from to]");
            System.exit(0);
        }
        Tinify.setKey(args.length >= 3 ? args[3] : Custom.TINIFY_KEY);
        final String dir = args[0];
        final String destDir = args[1];
        File fdir = new File(dir);
        FileKit.each(fdir, new Callback<File>() {
            public void invoke(File f) {
                String suffixName = FileKit.getSuffixName(f.getPath());
                if (f.isDirectory() || (f.isFile()
                        && !StringKit.isBlank(suffixName)
                        && ArrayKit.isContain(Custom.SUPPORT, suffixName.toLowerCase()))
                        ) {
                    try {
                        String path = f.getCanonicalPath();
                        path = path.replaceFirst(dir, destDir);
                        File newFile = new File(path);
                        if (!newFile.exists()) {
                            if (f.isDirectory()) {
                                FileKit.makeDir(newFile);
                            } else if (f.isFile()) {
                                FileKit.createNewFile(newFile);
                                Tinify.fromFile(f.getPath()).toFile(path);

                                System.out.println(String.format("Compressed file: from [%s][%s] to [%s][%s]",
                                        ColorKit.yellow(f.getAbsolutePath()), ColorKit.red(FileKit.getFileSize(f)),
                                        ColorKit.yellow(newFile.getAbsolutePath()), ColorKit.red(FileKit.getFileSize(newFile))));
                                beforeZipLength += f.length();
                                afterZipLength += newFile.length();
                                successCount++;
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        System.out.println(String.format("result [%d/%d]", successCount, successCount));
        System.out.println(String.format("compression %s [%s/%s]",
                ColorKit.red(FileKit.getFileSize(beforeZipLength - afterZipLength)),
                ColorKit.red(FileKit.getFileSize(afterZipLength)),
                ColorKit.red(FileKit.getFileSize(beforeZipLength))));

    }
}
