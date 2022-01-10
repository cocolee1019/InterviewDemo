package chapter12;

import java.util.HashMap;
import java.util.Map;

/**
 * 统计所有小于非负整数 n 的质数的数量。
 *
 * 输入：n = 10
 * 输出：4
 * 解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
 *
 * 答案：
 * 埃氏筛选法
 * 从2开始，划掉其倍数，剩余的数即为质数。
 *
 * 优化思路：
 * 为什么只需要求到sqrt(n)就行了？
 * 答：因为sqrt(n)即可遍历整个数组了。拿100举例，划掉从2到10的倍数即可，到11已经不需要再划了， 因为11的2倍、3倍、4倍，在前面均已出现。
 */
public class LeetCode_countPrimes {

    public static void main(String[] args) {
        System.out.println(countPrimes(500000));
    }


    public static int countPrimes(int n) {
        int[] flag = new int[n+1];
        int c = 0;
        for (int i=2; i*i <= n; i++) {
            if (flag[i] == 0) {
                for (int x = 2; i * x <= n; x++) {
                    flag[i * x] = 1;
                }
            }
        }

        for (int i=2; i < n; i++) {
            if (flag[i] == 0) {
                c++;
            }
        }
        return c;
    }

    public static boolean isPrime(int n) {
        int i=2;
        for (; i * i <= n; i++) {
            if (n%i == 0) {
                return false;
            }
        }
        return true;
    }
}
