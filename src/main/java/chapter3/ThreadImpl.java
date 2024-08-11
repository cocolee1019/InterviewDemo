package chapter3;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 一、Java中线程实现有哪几种方式？ <br>
 * 继承Thread <br>
 * 实现Runnable <br>
 * Callable <br>
 * 线程池 <br>
 *
 * 二、重点复习Callable，带返回结果的thread。<br>
 * 1. 如何使用？<br>
 * 1.1 实现Callable对象，重写call方法，call有返回值。<br>
 * 1.2 使用FutureTask，包装callable对象。<br>
 * 1.3 将FutureTask传入Thread。<br>
 * 1.4 调用FutureTask.get方法获取线程返回的结果。<br>
 *
 *
 *
 * @author luwu
 */
public class ThreadImpl {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        callableDemo();
    }

    public static void callableDemo() throws ExecutionException, InterruptedException {
        Callable callable = new Callable() {
            @Override
            public Object call() throws Exception {
                Thread.currentThread().sleep(1000L);
                return new String("fhhhh");
            }
        };

        FutureTask<Object> ft = new FutureTask<>(callable);
        Thread t1 = new Thread(ft);
        t1.start();
        Object o = ft.get();
        System.out.printf(o.toString());
    }
}
