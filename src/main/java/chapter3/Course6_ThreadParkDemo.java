package chapter3;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport.park()：使当前线程休眠。
 */
public class Course6_ThreadParkDemo {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println(Thread.interrupted());
            LockSupport.park();
            System.out.println("sssssssssssssss");
            System.out.println(Thread.interrupted());
        });
        thread.start();
        System.out.println("aaaaaaaaaaaaaa");
        TimeUnit.SECONDS.sleep(2);
        LockSupport.unpark(thread);
    }


}
