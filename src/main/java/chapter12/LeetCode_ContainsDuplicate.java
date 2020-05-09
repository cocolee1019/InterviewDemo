package chapter12;

import java.util.HashSet;
import java.util.Set;

/**
 * 重复元素判断
 *
 * 给定一个整数数组，判断是否存在重复元素。
 * 如果任意一值在数组中出现至少两次，函数返回 true 。如果数组中每个元素都不相同，则返回 false 。
 *
 * 这道题两个思想：
 * 1、先排序，再比较
 * 2、hash定位，利用hashSet的特性
 */
public class LeetCode_ContainsDuplicate {

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,0,4};
        System.out.println(containsDuplicate(nums));
    }

    public static boolean containsDuplicate(int[] nums) {
        Set<Integer> sets = new HashSet<>();
        for (int num : nums) {
            sets.add(num);
        }
        if (sets.size() == nums.length)
            return false;
        return true;
    }
}
