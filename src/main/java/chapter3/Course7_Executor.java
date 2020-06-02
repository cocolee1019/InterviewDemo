package chapter3;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池的学习
 *
 * 1、Executor层次设计。
 * Executor为顶层接口。其常用的子类有：ThreadPoolExecutor
 *
 */
public class Course7_Executor {

    public static void main(String[] args) {
        Executor executor = new ThreadPoolExecutor(5, 20, 5, TimeUnit.SECONDS, new ArrayBlockingQueue(100));

    }

}
