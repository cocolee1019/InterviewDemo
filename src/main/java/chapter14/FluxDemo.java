package chapter14;

import reactor.core.publisher.Flux;

/**
 * Flux表示0个或N个异步序列流。
 *
 * @author ljj
 * 2019/12/22  16:34
 */
public class FluxDemo {

    public static void main(String[] args) {

        /*
            假如sink不complete，则会无限调用sink.next().
         */
        Flux.generate(sink -> {
            sink.next("Hello");
            //sink.complete();
        }).subscribe(System.out::println);

    }
}
