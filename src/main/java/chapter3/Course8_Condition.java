package chapter3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition学习
 *
 * 常用方法：
 * await：与Object.wait相似，使获取到当前锁的线程休眠（to wait），并释放锁。该方法与wait一样，需要获得锁才可释放。
 * signal：与Object.notify相似，唤醒一个休眠状态的线程，线程需要重新获取锁。
 * signalAll：与Object.notifyAll相似，唤醒所有正在等待的线程。
 *
 *
 * 1、Condition的作用是什么？
 * 用于在ReentrantLock中实现和Object.wait()、Object.notifyA()类似的休眠、通知机制。
 *
 */
public class Course8_Condition {

    public static void main(String[] args) throws InterruptedException {

        ReentrantLock o = new ReentrantLock();
        Condition condition = o.newCondition();

        condition.await();
        condition.signal();
        condition.signalAll();
    }



}
