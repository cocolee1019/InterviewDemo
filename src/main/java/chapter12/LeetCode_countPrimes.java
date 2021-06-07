package chapter12;

import java.util.HashMap;
import java.util.Map;

/**
 * 统计所有小于非负整数 n 的质数的数量。
 *
 * 输入：n = 10
 * 输出：4
 * 解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
 */
public class LeetCode_countPrimes {

    public static void main(String[] args) {
        System.out.println(countPrimes(10));
    }


    public static int countPrimes(int n) {
        int c = 0;
        for (int i=2; i < n; i++) {
            if (isPrime(i)) {
                c++;
                i++;
            }
        }
        if (n > 3) c ++;
        return c;
    }

    public static boolean isPrime(int n) {
        if (n == 2) return true;
        int i=2;
        for (; i <= Math.ceil(Math.sqrt(n)); i++) {
            if (n%i == 0) {
                return false;
            }
        }
        return true;
    }
}
