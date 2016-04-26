package in.hocg.zipng;

import com.tinify.Tinify;
import in.hocg.zipng.lang.*;
import in.hocg.zipng.lang.inter.Callback;
import org.apache.commons.cli.*;

import java.io.File;
import java.io.IOException;

/**
 * Created by hocgin on 16-3-22.
 */
public class Main {

    private static int successCount = 0;
    private static long beforeZipLength = 0;
    private static long afterZipLength = 0;

    private static Options options = new Options();
    private static String helpStr = "zipng [-h/--help] [-f/--from] fromPath [-t/--to] toPath [-i/--ignore] ignore [-k/--key] key";
    private static HelpFormatter helpFormatter = new HelpFormatter();

    /**
     * @param args
     */
    public static void main(String[] args) {
        options.addOption("h", "help", false, "print help for the command.");
        options.addOption("f", "from", true, "要压缩的文件 或 文件夹.");
        options.addOption("t", "to", true, "压缩后存放的文件夹");
        options.addOption("i", "ignore", true, "忽略文件名(正则)");
        options.addOption("k", "key", true, "自定义秘钥");
        String from;
        String to;
        String ignore;
        String key;
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = null;
        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            helpFormatter.printHelp(helpStr, options);
        }
        if (cmd.hasOption("h")) {
            helpFormatter.printHelp(helpStr, options);
            System.exit(0);
        }
        from = cmd.hasOption("from") ? cmd.getOptionValue("from") : "";
        to = cmd.hasOption("to") ? cmd.getOptionValue("to") : "";
        ignore = cmd.hasOption("ignore") ? cmd.getOptionValue("ignore") : "";
        key = cmd.hasOption("key") ? cmd.getOptionValue("key") : "";
        String[] arg = cmd.getArgs();
        if (arg.length <= 2 && cmd.getOptions().length == 0) { // default
            from = arg[0];
            to = arg[0];
            if (arg.length == 2) {
                to = arg[1];
            }
        }
        Tinify.setKey(LangKit.ifEmpty(key, Custom.TINIFY_KEY));
        _zip(from, to, ignore);
    }

    /**
     * @param dir
     * @param destDir 一定要是文件夹
     */
    private static void _zip(final String dir, final String destDir, final String ignore) {
        File fdestDir = new File(destDir);
        FileKit.makeDir(fdestDir);
        if (!fdestDir.isDirectory()) {
            helpFormatter.printHelp(helpStr, options);
            System.exit(0);
        }
        FileKit.each(new File(dir), new Callback<File>() {
            public void invoke(File f) {
                String suffixName = FileKit.getSuffixName(f.getPath());
                if (f.isDirectory() || (f.isFile()
                        && !StringKit.isBlank(suffixName)
                        && ArrayKit.isContain(Custom.SUPPORT, suffixName.toLowerCase()))) {
                    try {
                        String path = f.getCanonicalPath();
                        path = path.replaceFirst(dir, destDir);
                        File newFile = new File(path);
                        if (!f.getName().matches(ignore)) {
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
