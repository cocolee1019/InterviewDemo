package chapter12;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class LeetCode_rob {

    public static void main(String[] args) {
//        System.out.println(rob(new int[]{1,2}));
//        System.out.println(rob(new int[]{2,7,9,3,1}));
        System.out.println(rob(new int[]{1,2,3,1}));
    }

    public static int rob(int[] nums) {
         int t1 = rec(nums, 0);
         int t2 = rec(nums, 1);
         return t1 > t2 ? t1 : t2;
    }

    private static Map<Integer, Integer> cache = new HashMap<>();

    private static int rec(int[] nums, int s) {
        if (s >= nums.length) {
            return 0;
        }
        if (cache.containsKey(s)) {
            return cache.get(s);
        }

        int t1 = nums[s] + rec(nums, s+2);
        int t2 = nums[s] + rec(nums, s+3);
        t1 = t1 > t2 ? t1 : t2;
        cache.put(s, t1);
        return t1;
    }
}
