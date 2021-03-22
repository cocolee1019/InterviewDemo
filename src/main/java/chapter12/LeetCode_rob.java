package chapter12;

import java.util.HashMap;
import java.util.Map;

/**
 *打家劫舍
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnq4km/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
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
