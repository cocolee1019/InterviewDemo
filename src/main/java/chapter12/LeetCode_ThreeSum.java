package chapter12;

import java.util.*;

/**
 * @author luwu
 * @date 2022/3/2 10:45
 * <p>
 * 给你一个包含 n 个整数的数组nums，判断nums中是否存在三个元素 a，b，c ，使得a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 * 示例 1：
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * <p>
 * 示例 2：
 * 输入：nums = []
 * 输出：[]
 * <p>
 * 示例 3：
 * 输入：nums = [0]
 * 输出：[]
 */
public class LeetCode_ThreeSum {


    public static void main(String[] args) {
        System.out.println(threeSum1(new int[]{1,2,-2,-1}));
    }

    /**
     * 该算法超时。
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum1(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList();
        Set<String> set = new HashSet<>();

        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                String t = nums[i] + "," + nums[j];
                if (set.contains(t)) {
                    continue;
                }
                int target = 0 - nums[i] - nums[j];

                int k = nums.length-1;
                for (; k > j && nums[k] > target; k--) {
                }

                if (k != j && nums[k] == target) {
                    List<Integer> okList = new ArrayList<>(3);
                    okList.add(nums[i]);
                    okList.add(nums[j]);
                    okList.add(nums[k]);
                    result.add(okList);
                    set.add(t);
                }
            }
        }
        return result;
    }


    /**
     * 利用数组有序的特性：
     * 双指针思路：将问题转化成双数之和 - 某数为0的情况， 关键在于如何求两数之后。利用数组有序的特性，一数从左取，一数从右取，
     * 双数相加，若
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList();
        Set<String> set = new HashSet<>();

        for (int i = 0; i < nums.length - 2; i++) {

        }

        return result;
    }
}
