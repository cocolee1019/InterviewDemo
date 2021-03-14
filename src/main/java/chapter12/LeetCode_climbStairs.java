package chapter12;

import java.util.HashMap;
import java.util.Map;

/**
 * 动态规划
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * 注意：给定 n 是一个正整数。
 */
public class LeetCode_climbStairs {

    public static void main(String[] args) {
        System.out.println(climbStairs2(44));
    }

    static Map<Integer, Integer> map = new HashMap<>();
    public static int climbStairs(int n) {
        if (n == 0) {
            return 1;
        }else if (n < 0) {
            return 0;
        }
        if (map.get(n) != null) {
            return map.get(n);
        }
        int result = climbStairs(n-1) + climbStairs(n - 2);
        map.put(n, result);
        return result;
    }

    public static int climbStairs2(int n) {
        int[] a = new int[n + 1];
        a[1] = 1;
        a[2] = 2;
        int sum = 0;
        for (int i=3; i<=n; i++) {
            a[i] = a[i-1] + a[i-2];
        }
        return a[n];
    }
}
