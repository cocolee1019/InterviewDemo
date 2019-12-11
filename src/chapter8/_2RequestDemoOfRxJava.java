package chapter8;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import uitls.HttpUtils;

import java.io.IOException;
import java.io.InputStreamReader;

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
 *
 * 2、如何被多个订阅者订阅。
 */
public class _2RequestDemoOfRxJava {

    public static void main(String[] args) throws IOException {
        /**
         * 验证猜想1
         */
        Observable.create((t) -> {
            //产生一个流
            int i = 0;
            while (i++ <= 3) {
                String resp = HttpUtils.generateRequest("http://ip.cn").doGet();
                t.onNext(resp);
                if (i == 2) {
                    t.onComplete();
                }
            }
        })
                .map((s) -> "---->" + s)
                //将观察者绑定到该线程中操作，即使用了其它线程，达到异步操作
                .subscribeOn(Schedulers.trampoline())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("执行订阅");
                    }

                    //发射数据
                    @Override
                    public void onNext(Object o) {
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
        new InputStreamReader(System.in).read();
        System.out.println("主线程结束");
    }
}
