package chapter12;

import java.util.Arrays;

/**
 * 旋转数组
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 * <p>
 * https://leetcode-cn.com/problems/rotate-array/
 */
public class LeetCode_Rotate {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        rotate(nums, 8);
    }

    public static void rotate(int[] nums, int k) {
        //1、先计算应该要旋转几次
        int len = nums.length;
        int rs = k % len;
        if (rs != 0) {
            if (len - rs > rs) {
                //向右转
                while (rs-- != 0) {
                    int t = nums[len - 1];
                    for (int i = len - 1; i > 0; i--) {
                        nums[i] = nums[i - 1];
                    }
                    nums[0] = t;
                }
            } else {
                //向左
                rs = len - rs;
                while (rs-- != 0) {
                    //向右转
                    int t = nums[0];
                    for (int i = 0; i < len - 1; i++) {
                        nums[i] = nums[i + 1];
                    }
                    nums[len - 1] = t;
                }
            }
        }
        System.out.println(Arrays.toString(nums));
    }


    /**
     * leetCode最优解
     * 思想：从第1位开始移动到他应该要去的位置。
     * 每个数只用移动一次，即可到达指定的位置。
     */
    private void routeByIdxMode(int[] nums, int k) {
        int time = 0;
        for (int start = 0; time < nums.length; start++) {
            int idx = start;
            int value = nums[idx];

            do {
                idx = (idx + k) % nums.length;
                int tmp = nums[idx];
                nums[idx] = value;
                value = tmp;
                time++;
            } while (idx != start);
        }
    }
}
