package chapter3;

import java.util.concurrent.*;

/**
 * 线程池的学习
 *
 * 1、Executor层次设计。
 * Executor为顶层接口。其常用的子类有：ThreadPoolExecutor
 *
 */
public class Course7_Executor {

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        ExecutorService executor = new ThreadPoolExecutor(5, 20, 5, TimeUnit.SECONDS, new ArrayBlockingQueue(100));

        executor.submit(() -> {}).get(1, TimeUnit.SECONDS);

        ExecutorCompletionService completionService = new ExecutorCompletionService(executor);
    }

}
