package chapter3;

import java.util.concurrent.TimeUnit;

/**
 * 线程基础之常用方法：
 * start：启动线程
 * interrupt：中断线程（该方法只是在线程中设置一个中断标记位，并不会真正中断线程，由本地方法interrupt0设置）
 * Thread.interrupted：类方法，返回当前线程的中断状态，并且重置中断标记。
 * isInterrupted：返回线程的中断状态，中断标记不受影响。
 * interrupt：中断线程的运行。
 * Object.wait：使拥有当前对象的monitor的线程进入WAITING状态，并且释放当前锁。需要在同步代码块或同步代码方法中使用。
 * Object.notify：随机唤醒调用Object.wait方法的线程。需要在同步代码块中使用。
 * Object.notifyAll：唤醒所有调用Object.wait方法的线程。需要在同步代码块中使用。
 * Thread.sleep：使线程睡眠。
 * Thread.suspend：阻塞一个线程，并且不会释放锁。
 * Thread.resume：使一个被suspend的线程恢复。
 * Thread.stop：停止当前线程，原理是通过抛出ThreadDeath异常，使线程中止。
 * <p>
 * <p>
 * 问题：
 * 1、调用Object.wait后，会释放当前monitor的锁吗？
 * 答：会释放Object的monitor，即表示其它线程的同步块使用了相同的Object为锁时，
 * 可以进入了。当其它线程调用Object.notify后，且其它线程已退出同步代码块或同步代码方法后，
 * monitor所在线程状态会重新抢占CPU时间。
 *
 * <p>
 * 2、Object.wait(timeout)会释放当前锁？
 * 答：会释放Object的monitor。同1问一样。
 *
 * <p>
 * 3、调用Thread.sleep()后，线程的状态是什么？如果在同步块中调用，是否会释放锁？
 * 答：调用Thread.sleep后，线程状态为TIMED_WAITING。在同步块中调用sleep方法，不会释放锁。
 *
 * <p>
 * 4、为什么Thread.stop、Thread.suspend和Thread.resume会被标记为过期？
 * 答：suspend不会释放monitor锁，容易造成线程死锁的状况。例如a线程锁定Object后，被suspend，b线程要resume a线程，
 * 但是在resume之前，又要请求Object的锁，此时便会造成死锁。
 * stop会在线程任何位置抛出异常，使线程中止。这便会出现很多问题，比如在线程finally中释放资源时抛出ThreadDeath异常，
 * 会导致资源释放失败，比如在synchronized中抛出异常会使锁提前被释放，而这种释放是不可控的。
 *
 * <p>
 * <p>
 * 5、如何使一个在循环的线程退出？
 * 答：1、调用该线程的interrupt方法，该方法会设置线程退出的标志位为退出，然后在线程中调用isInterrupted方法，判断标志位
 * 是否已被设置为中断，如果为中断则退出循环。
 * 2、当有异常未被捕获时，也会使线程退出。
 *
 * 复盘：
 * 1、关于wait的一些问题， 前面说到，wait会释放monitor锁，但没具体说明wait怎么用的？用于什么情景。
 * Object.wait()方法文档含义是：Causes the current thread to wait(使当前线程等待)，until another thread invokes the
 * notify() method or the notifyAll() method for this object.（直到其它线程调用该对象的notify()或者notifyAll()）。
 * <b>The current thread must own this object's monitor.（当前线程必需拥有该对象的monitor锁）</b>
 * the thread releases ownership of this monitor and waits until another thread notifies threads waiting on this
 * object`s monitor to wake up either through a call to the notify method or the notifyall method.
 *
 * This method should only be called by a thread that is the owner of this object's monitor.
 *
 * 由上述描述可知：wait与/notify nofityall是一对。
 * wait被用于多个线程之间<b>条件等待</b>的一种实现，调用wait必须保证当前线程已获得目标object的monitor锁。
 * wait被执行后，当前线程将进行休眠，直到目标object的notify或notifyall方法被执行。
 *
 * 猜想1：根据上面文档描述“causes the current thread to wait”是不是只要是当前线程中的任何对象调用wait方法，都会使当前线程进入等待状态？
 * 结论：事实证明，出现这个猜想，一定是基础不合格，忘记了wait的使用场景。
 *
 * 2、notify()补充
 *  先看文档：Wakes up a single thread that is waiting on this object's monitor.（唤醒单个正在等待monitor锁的线程）
 *  If any threads are waiting on this object, one of them is chosen to be awakened.（如果所有线程都在等待，随机一个将被唤醒）
 *  A thread waits on an object's monitor by calling one of the wait methods.（线程调用wait()进入等待object的monitor状态）
 *  The awakened thread will not be able to proceed until the current thread relinquishes the lock on this object.
 *  （被唤醒的线程不一定能执行，直到当前线程放弃在object上的锁。）The awakened thread will compete in the usual manner with
 *  any other threads that might be actively competing to synchronize on this object;（被唤醒的线程将会与其它监视object锁
 *  进入synchronized块的线程竞争）
 *
 *  <b>This method should only be called by a thread that is the owner of this object's monitor.</b>
 *
 *  2020-11-15
 *  线程调用wait方法后，会释放当前锁，并且进入休眠状态，等待其它线程调用notify方法，如果该线程被重新唤起，他是重新进入synchronized
 *  块还是从休眠的部分执行？
 *  答：由经验得知，是从休眠部分被重新唤起。
 */
public class Course1_Thread {

    public static void main(String[] args) throws InterruptedException {
//        main1();
//		main2();
//		main3();
//        main4();
//        main5();
        main6();
    }

    private static void main6() {
        //猜想1：会不会当前线程任何一个对象调用wait方法，都会使线程等待？
        new Thread(Course1_Thread::sayHello5).start();
    }

    private static Object lock = new Object();

    /**
     * 重现stop抛出的ThreadDeath异常
     */
    private static void main4() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                synchronized (lock) {
                    System.out.println("线程1开始休眠3秒");
                    lock.wait(3000);
                }
                System.out.println("线程1退出");
            } catch (Throwable e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                System.out.println("线程2获得锁");
            }
            System.out.println("线程2退出");
        });

        t1.start();
//		TimeUnit.SECONDS.sleep(1);
//		t1.stop();
        t2.start();
        int i = 0;
        while (i++ <= 100000000) {
            TimeUnit.SECONDS.sleep(1);
            System.out.printf("主线程工作，输出线程1：%s,和线程2状态：%s\n", t1.getState().name(), t2.getState().name());
        }
    }

    //suspend和resume
    private static void main3() {
        Thread thread = new Thread(Course1_Thread::sayHello3);
        thread.start();
        int i = 0;
        while (thread.getState() != Thread.State.TERMINATED) {
            System.out.println("线程的状态：" + thread.getState().name());
            if (thread.getState() == Thread.State.RUNNABLE && i++ == 1) {
//				System.out.println("阻塞线程");
//				thread.suspend();
                System.out.println("停止线程");
                thread.stop();
            }
            int k = 0;
            while (k++ <= 1900000000L) ;
        }

    }

    //解答第2问
    private static void main2() {
        Thread t1 = new Thread(Course1_Thread::sayHello2);
        Thread t2 = new Thread(Course1_Thread::sayHello2);
        t1.start();
        t2.start();
        while (t1.getState() != Thread.State.TERMINATED) {

            int k = 0;
            while (k++ <= 1000000000L) ;

            System.out.printf("线程%s的状态%s\n", t1.getName(), t1.getState().name());
            System.out.printf("线程%s的状态%s\n", t2.getName(), t2.getState().name());
        }
    }

    //main1，解答第1问
    private static void main1() {
        Thread t1 = new Thread(Course1_Thread::sayHello);
        Thread t2 = new Thread(Course1_Thread::sayHello);
        t1.start();
        t2.start();
        int i = 0, j = 0;

        int k = 0;
        while (k++ <= 1000000000L) ;

        System.out.printf("线程%s的状态%s\n", t1.getName(), t1.getState().name());
        System.out.printf("线程%s的状态%s\n", t2.getName(), t2.getState().name());
//			if(t1.getState() == Thread.State.WAITING && i++ == 3) {
//				//阻塞三秒后解锁
//				synchronized(lock) {
//					//随机唤醒一个线程，可能会造成错误。
//					lock.notify();
//				}
//			}
//			
//			if(t2.getState() == Thread.State.WAITING && j++ == 5) {
//				synchronized(lock) {
//					lock.notify();
//				}
//			}
    }

    /**
     * 使用interrupt中断线程
     */
    private static void main5() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    for (; ; ) {
                        for (long i = 0; i <= 10000000000000L; ) i++;
                        System.out.println("== 运行中 ==");
                        Thread.sleep(1000);
                    }
                } catch (Exception e) {
                    //skip
                    e.printStackTrace();
                }

            }
        });
        t.start();

        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            //skip
        }
        System.out.println("中断t");
        t.interrupt();
    }

    /**
     * 验证wait
     */
    private static void sayHello() {
        Thread currentThread = Thread.currentThread();
        synchronized (lock) {
            try {

                System.out.println("使线程等待：" + currentThread.getName());
                String s = new String();
                s.wait(5000);
                //lock.wait(5000);
                System.out.println(currentThread.getName() + "被唤醒，状态为：" + currentThread.getState().name());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("hello");
        System.out.println(currentThread.getName() + "结束～");
    }

    private static byte[] lockB = new byte[1];

    /**
     * 验证sleep时的状态
     */
    private static void sayHello2() {
        try {
            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() + "进入");
                Thread.sleep(5000);
                System.out.println(Thread.currentThread().getName() + "结束");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 调用suspend和resume
     */
    public static void sayHello3() {
        System.out.println("开始工作");
        int i = 0;
        while (i++ <= 10000000000000L) {
            System.out.println("正在工作");
            int k = 0;
            while (k++ <= 1900000000L) ;
        }
        System.out.println("工作完成");
        System.out.println("hello~");
    }


    /**
     * 验证复盘时的猜想1
     */
    private static void sayHello5() {
        try {
            final String s = new String();
            synchronized (s) {
                System.out.println(Thread.currentThread().getName() + "进入");
                //等待10s
                s.wait(10000);
                s.notify();
            }
            System.out.println(Thread.currentThread().getName() + "结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
