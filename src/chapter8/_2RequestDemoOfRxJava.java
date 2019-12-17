package chapter8;

import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.parallel.ParallelFlowable;
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

        Flowable.create((t) -> {
            //产生流
            int i = 0;
            while (true) {
                String resp = HttpUtils.generateRequest("http://ip.cn").doGet();
                System.out.println("数据流的产生是在:" + Thread.currentThread().getName() + "   <------- " + (i++));
                t.onNext(resp);
                //Thread.sleep(3000);
            }
        }, BackpressureStrategy.BUFFER)
            //.subscribeOn(Schedulers.io())
            .parallel()
            .map((s) -> {
                System.out.println(Thread.currentThread().getName() + "  <--  管道中1");
                return s = "--1-->" + s;
            })
            .map((s) -> {
                System.out.println(Thread.currentThread().getName() + "  <--  管道中2");
                return s = "--2-->" + s;
            })
            .map((s) -> {
                System.out.println(Thread.currentThread().getName() + "  <--  管道中3");
                return s = "--3-->" + s;
            })
            .sequential()
            .subscribe((t)->{
                System.out.println(Thread.currentThread().getName() + "  <--  订阅者");
                System.out.println(t.toString());
            });

        System.in.read();
        System.out.println("主线程结束");
    }
}
