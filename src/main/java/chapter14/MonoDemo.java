package chapter14;

import reactor.core.publisher.Mono;

/**
 * 1、Mono是什么？
 *   在观察者模式中，充当publisher（发布者），只能发射0或1个元素。
 *
 *
 * @author ljj
 */
public class MonoDemo {

    public static void main(String[] args) {
        Mono.just(1).subscribe(System.out::print);
    }
}
