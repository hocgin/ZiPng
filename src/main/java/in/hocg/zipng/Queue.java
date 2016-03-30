package in.hocg.zipng;

import java.util.ArrayList;

/**
 * Created by hocgin on 16-3-22.
 */
public class Queue {
    private static Queue ourInstance = new Queue();
    private static int nowNum = 0; // 当前步数
    private static ArrayList<Thread> threadPool = new ArrayList<Thread>();  // 线程池

    public static Queue getInstance() {
        return ourInstance;
    }



    private Queue() {
    }
}
