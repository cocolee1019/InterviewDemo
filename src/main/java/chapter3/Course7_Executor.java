package chapter3;

import java.util.concurrent.*;

/**
 * 线程池的学习
 *
 * 1、Executor层次设计。
 * Executor为顶层接口。
 * ExecutorService子接口：线程池的主要接口
 * 其常用的子类有：ThreadPoolExecutor
 *
 * 如何理解线程池中的BlockQueue
 * 答：BlockQueue用于生产者与消费者之间，如果队列满了，则需要阻塞生产者，如果队列空闲，则阻塞消费者，阻塞队列实现了该逻辑与其中的细节。
 *
 * 什么是有界队列、什么是无界队列？
 * 1、有上限的队列，如ArrayBlockingQueue、LinkBlockingQueue等
 * 2、无上限的队列，如DelayQueue
 */
public class Course7_Executor {

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        ExecutorService executor = new ThreadPoolExecutor(5, 20, 5, TimeUnit.SECONDS, new ArrayBlockingQueue(100));
        executor.submit(() -> {}).get(1, TimeUnit.SECONDS);
        ExecutorCompletionService completionService = new ExecutorCompletionService(executor);
    }

}
