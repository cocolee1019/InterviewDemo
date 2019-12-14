package chapter8;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import uitls.HttpUtils;

import java.io.IOException;

/**
 * @author ljj
 * 2019/11/20 09:42
 * <p>
 * 一个异步请求。
 * <p>
 * 猜想1：
 * RxJava发现请求后，主线程可能已结束，待请求响应后，下游开始处理数据。
 * 结论：
 * 该猜想是错误的，主线程会等待该流结束，并且subscribe是终端操作，所以下面的写法不能有多个订阅者。
 * <p>
 * 1、如果需要实现猜想1的功能，则该如何修改？
 * 在RxJava中，异步任务便是建立在Schedule中的。在执行subscribe方法前，
 * 执行subscribeOn方法，将订阅者的emmit绑定到subscribeOn指定的线程中。
 * <p>
 * <p>
 * 2、如何被多个订阅者订阅。
 *
 *
 * <p>
 * 3、 observeOn的作用是什么？
 * 答：observeOn用于切换下游数据的线程，而subscribeOn是整个流从源头便切到指定线程，
 *    并且subscribeON不受位置的限制，每个流中，只有最先出现的subscribeOn生效。
 */
public class _2RequestDemoOfRxJava {

    public static void main(String[] args) throws IOException, InterruptedException {
        /**
         * 验证猜想1
         */
        Observable.create((t) -> {
            //产生流
            int i = 0;
            while (i++ <= 3) {
                String resp = HttpUtils.generateRequest("http://ip.cn").doGet();
                System.out.println("数据流的产生是在:" + Thread.currentThread().getName());
                t.onNext(resp);
                if (i == 2) {
                    t.onComplete();
                }
            }
        })
                .subscribeOn(Schedulers.computation())
                //将流切换到一个新线程
                .map((s) -> {
                    System.out.println(Thread.currentThread().getName() + "  <--  管道中1");
                    return s = "---->" + s;
                })
                .observeOn(Schedulers.newThread())
                .map((s) -> {
                    System.out.println(Thread.currentThread().getName() + "  <--  管道中2");
                    return s = "---->" + s;
                })
                .observeOn(Schedulers.io())
                //将观察者绑定到该线程中操作，即使用了其它线程，达到异步操作
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("执行订阅");
                    }

                    @Override
                    public void onNext(Object o) {
                        System.out.println(Thread.currentThread().getName() + "  <--  订阅者");
                        System.out.println(o.toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("----------------------");
                    }
                });

        Thread.sleep(8000);
        System.out.println("主线程结束");
    }
}
