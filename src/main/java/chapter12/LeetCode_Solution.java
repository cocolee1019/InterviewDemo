package chapter12;

import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

/**
 * 给你一个整数数组 nums ，设计算法来打乱一个没有重复元素的数组。
 * 实现 Solution class:
 *
 * Solution(int[] nums) 使用整数数组 nums 初始化对象
 * int[] reset() 重设数组到它的初始状态并返回
 * int[] shuffle() 返回数组随机打乱后的结果
 */
public class LeetCode_Solution {

    private int[] nums;
    private int len;

    public LeetCode_Solution(int[] nums) {
        this.nums = nums.clone();
        this.len = nums.length;
    }

    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return nums;
    }

    /**
     *  Fisher-Yates 洗牌算法
     */
    public int[] shuffle() {
        Random r = new Random();
        int[] numss = nums.clone();
        for (int i=0; i<len; i++) {
            int rn = r.nextInt(len);
            int t = numss[rn];
            numss[rn] = numss[i];
            numss[i] = t;
        }
        return numss;
    }

    public static void main(String[] args) {
        LeetCode_Solution solution = new LeetCode_Solution(new int[]{1, 2, 3, 4, 5});
        System.out.println(Arrays.toString(solution.shuffle()));
    }
}
