package chapter8;

import io.reactivex.rxjava3.core.Observable;

import java.util.Arrays;
import java.util.List;

/**
 * 操作符
 * @author ljj
 * 2019/12/14 10:26
 */
public class _4Operation {

    public static void main(String[] args) {

        Observable.fromArray(new String[]{"a", "b", "c"})
                .subscribe((t) -> {
                    System.out.println(t);
                });
    }
}
