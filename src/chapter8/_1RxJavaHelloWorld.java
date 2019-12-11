package chapter8;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * 响应式编程学习 <br>
 * 开始学习响应式编程时，带着下面几个疑问：<br>
 * 1、响应式编程能为我们带来什么好处？<br>
 *    1.1、响应式编程是非阻塞式编程方法。非阻塞式的优化是服务器可以处理更多的客户端请求。
 *    1.2、流式编程方式。流式编程可以使程序员专注于业务逻辑处理，而不用关注数据来源，并且使代码更清晰。
 * 2、响应式编程的使用场景。
 * <p></p>
     * 本次学习响应式编程的框架是Rxjava，Rxjava是对java vm响应式编程的扩展，该库用一个可观察的序列
     * 来组成<b>异步</b>和<b>基于事件</b>的程序。
     * 它扩展了 观察者模式 以支持数据/事件序列，并且添加了允许以声明方式将序列组合在一起的运算符，同时
     * 消除了如低级线程、同步、线程安全和并发数据结构之类问题的担忧。
 * <p>
 *
 * 3、入门学习之理解Observable。
 *    答：Observable是可观察者，调用Observable.create(emmit)创建一个可观察者。
 *       使用Observable.subscribe(参数)订阅它。里头的参数是emmit的实例。
 *
 */
public class _1RxJavaHelloWorld {

    public static void main(String[] args) throws InterruptedException {

        /*
            Flowable：Flowable被设计成为可背压的流。
            just生成一个流，但不做任何计算。
         */
        //创建一个被订阅者
        Flowable.just("Hello world")
                //订阅
                .subscribe((str) -> {
                    System.out.println(str.startsWith("hee"));
                    System.out.println(str.endsWith("world"));
                });


        //Assembly time
        /*Flowable<Integer> flow = null;
        Flowable.range(1, 5)
                .map(v -> v * v)
                .filter(v -> v % 3 == 0).forEach(System.out::println);


 */

        //创建一个被观察者
        Observable.create(emitter -> {
            emitter.onNext(1L);
            System.out.println("进入~");
            while (!emitter.isDisposed()) {
                long time = System.currentTimeMillis();
                emitter.onNext(time);
                emitter.onNext(time);
                emitter.onNext(time);
                emitter.onNext(time);
                //发送一个完成信息，使标isDisposed标记置为false,并且onNext不再接受事件。
                emitter.onComplete();
                System.out.println("0_0");
                emitter.onNext(time);
//                if (time % 2 != 0) {
//                    emitter.onError(new IllegalStateException("Odd millisecond!"));
//                    break;
//                }
            }
        }).map((t) -> (Long) t / 1000)
        //Observable被订， 第一个参数：onNext应用、 第二个参数：onError应用。详情见文档。
        .subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("---订阅---");
                //使标isDisposed标记置为false，并且onNext不再接受事件。
                //d.dispose();
            }

            @Override
            public void onNext(Object o) {
                System.out.println("----on next----");
                System.out.println(o);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("错误发生");
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete被执行");
            }
        });

        Flowable.fromCallable(() -> {
            Thread.sleep(1000); //  imitate expensive computation
            return "Done";
        })
        // Run I/O-like or blocking operations on a dynamically changing set of threads.
        .subscribeOn(Schedulers.io())
        // Run work on a single thread in a sequential and FIFO manner.
        .observeOn(Schedulers.single())
        .subscribe(System.out::println, Throwable::printStackTrace);

        System.out.println("end");

        Thread.sleep(2000); // <--- wait for the flow to finish


        //  Concurrency within a flow
        /*Flowable.range(1, 10)
                .observeOn(Schedulers.computation())
                .map(v -> v * v)
                .blockingSubscribe(System.out::println);*/

    }
}
