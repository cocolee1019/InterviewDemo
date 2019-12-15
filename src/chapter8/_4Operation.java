package chapter8;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 操作符
 * @author ljj
 * 2019/12/14 10:26
 */
public class _4Operation {

    public static void main(String[] args) {

//        Observable.fromArray(new String[]{"a", "b", "c"})
//                .subscribe((t) -> {
//                    System.out.println(t);
//                });

        /*
            RxJava中，流本质上是顺序的，分为多个处理阶段，这些阶段彼此可以并行运行。
            然而下面这个程序中，map是在“computation”线程，按顺序接收1到10，再由main线程消耗结果。
        */
        Flowable.range(1, 10)
                .observeOn(Schedulers.computation())
                .map( t -> t*t)
                .blockingSubscribe(System.out::println);

        System.out.println("----------");

        /*
            改造,使其可并行运算。
            注意：flatMap不保证顺序，其输出的结果可能会交错进行。如果要考虑顺序，可以尝试以下方法：
            concatMap:映射和运行每一个item, 每次一个内部流
            concatMapEager “一次”运行所有内部流，但输出流将按照创建这些内部流的顺序进行。
         */
        Flowable.range(1, 10)
                .flatMap(x->
                    Flowable.just(x)
                        .observeOn(Schedulers.computation())
                        .map(n -> n*n)
                )
                .blockingSubscribe(System.out::println);

        System.out.println("----------x---------");

        /*
            除了上述并发方式，还可以使用Flowable.parallel()和ParallelFlowable操作代替。
         */
        Flowable.range(1, 10)
                .parallel()
                .runOn(Schedulers.computation())
                .map(v -> v*v)
                .sequential()
                .subscribe(System.out::println);
    }
}
