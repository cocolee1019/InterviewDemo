package chapter12;

import java.util.Arrays;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * <p>
 * 示例:
 * 给定 nums = [2, 7, 11, 15], target = 9
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 */
public class LeetCode_TwoSum {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoSum(new int[]{2, 7, 11, 15}, 17)));
    }

    public static int[] twoSum(int[] nums, int target) {
        int result[] = new int[2];
        int[] tmp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            tmp[i] = target - nums[i];
            for (int j = i-1; j >= 0; j--) {
                if (tmp[j] == nums[i]) {
                    result[0] = j;
                    result[1] = i;
                }
            }
        }
        return result;
    }
}
