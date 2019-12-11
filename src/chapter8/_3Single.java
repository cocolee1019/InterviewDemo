package chapter8;

import io.reactivex.rxjava3.core.Single;

/**
 * Single为Observable的变种，类似于Observable，而不同于Ob。
 * 它总每次只发送一个值，或者一个错误通知，而不是发射一系列的值。
 * 因此，Single不需要onNext、onCompleted。它只需要下面两种：
 *  <li>onSuccess - Single发射单个的值到这个方法
 *  <li>onError - 如果无法发射需要的值，Single发射一个Throwable对象到这个方法
 *
 *  Single只会调用这两个方法中的一个，而且只会调用一次，调用了任何一个方法之后，订阅关系终止。
 *
 * @author ljj
 * 2019/12/11 09:43
 */
public class _3Single {

    public static void main(String[] args) {

        Single.create(emitter -> {
            emitter.onSuccess("sssssssssssss");
        }).subscribe(System.out::println);

    }
}
