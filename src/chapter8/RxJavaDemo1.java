package chapter8;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * 响应式编程学习 <br>
 * 开始学习响应式编程时，带着下面几个疑问：<br>
 * 1、响应式编程能为我们带来什么好处？<br>
 * 2、响应式编程的使用场景。
 * <p></p>
 * 本次学习响应式编程的框架是Rxjava，Rxjava是对java vm响应式编程的扩展，该库用一个可观察的序列
 * 来组成<b>异步</b>和<b>基于事件</b>的程序。
 * 它扩展了 观察者模式 以支持数据/事件序列，并且添加了允许以声明方式将序列组合在一起的运算符，同时
 * 消除了如低级线程、同步、线程安全和并发数据结构之类问题的担忧。
 * <p>
 * <p>
 * RxJavaDemo1为入门demo的实现。主要是用于对RxJava3的用法有一个初步了解。
 * <p>
 * 术语：
 * upstream： 指中间操作，表示还能再产生流的操作，upstream往右看是上游，往左看是下游。
 * downstream： 指终端操作，是upstream的消费者。
 * backpressure：背压，当数据流以异步的方式运行时，每个步骤可能以不同的速度执行不同的操作，为了避免过多的
 * 步骤（通常会因为临时缓冲或需要跳过/删除数据而导致内存用量），采用了一种所谓的“背压”，“背压”是流控制的一种
 * 形式。
 * Assembly time：The preparation of dataflows by applying various intermediate operators
 * happens in the so-called assembly time。
 * <p>
 * Subscription time：This is a temporary state when subscribe() is called on a flow
 * that establishes the chain of processing steps internally
 * <p>
 * Runtime：This is the state when the flows are actively emitting items, errors or completion signals.
 */
public class RxJavaDemo1 {

    public static void main(String[] args) throws InterruptedException {

        //Flowable.just("Hello world").subscribe(System.out::println);

        //Assembly time
        /*Flowable<Integer> flow = null;
        Flowable.range(1, 5)
                .map(v -> v * v)
                .filter(v -> v % 3 == 0).forEach(System.out::println);


        Observable.create(emitter -> {
            while (!emitter.isDisposed()) {
                long time = System.currentTimeMillis();
                emitter.onNext(time);
                if (time % 2 != 0) {
                    emitter.onError(new IllegalStateException("Odd millisecond!"));
                    break;
                }
            }
        }).subscribe(System.out::println, Throwable::printStackTrace);

 */
        /*Flowable.fromCallable(() -> {
            Thread.sleep(1000); //  imitate expensive computation
            return "Done";})
                // Run I/O-like or blocking operations on a dynamically changing set of threads.
                .subscribeOn(Schedulers.io())
                // Run work on a single thread in a sequential and FIFO manner.
                .observeOn(Schedulers.single())
                .subscribe(System.out::println, Throwable::printStackTrace);

        System.out.println("end");

        Thread.sleep(2000); // <--- wait for the flow to finish
         */

        //  Concurrency within a flow
        Flowable.range(1, 10)
                .observeOn(Schedulers.computation())
                .map(v -> v * v)
                .blockingSubscribe(System.out::println);

    }
}
