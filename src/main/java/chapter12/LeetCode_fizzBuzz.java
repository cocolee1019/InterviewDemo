package chapter12;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 写一个程序，输出从 1 到 n 数字的字符串表示。
 * <p>
 * 1. 如果 n 是3的倍数，输出“Fizz”；
 * 2. 如果 n 是5的倍数，输出“Buzz”；
 * 3.如果 n 同时是3和5的倍数，输出 “FizzBuzz”。
 */
public class LeetCode_fizzBuzz {

    public static void main(String[] args) {
        System.out.println(fizzBuzz(15));
    }

    public static List<String> fizzBuzz(int n) {
        List<String> list = new ArrayList<>(n);
        for (int i=1; i<=n; i++) {
            String s;
            boolean f = i%3==0;
            boolean b = i%5==0;
            if (f && b) {
                s = "FizzBuzz";
            }else if (f) {
                s = "Fizz";
            }else if (b) {
                s = "Buzz";
            }else {
                s = "" + i;
            }
            list.add(s);
        }
        return list;
    }
}
