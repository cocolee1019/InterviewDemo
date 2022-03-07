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
        System.out.println(threeSum(new int[]{-4,-2,-2,-2,0,1,2,2,2,3,3,4,4,6,6}));
    }

    /**
     * 该算法超时。
     *
     * 后优化：利用数组有序的特性，不再全局遍历
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
     * 1、将三数之和转换为两数之和。
     * 2、在判断两数之和时，因为数组有序，若第一数大于0，即可不用再判断两数之后了。
     * 3、若两数相邻，则可以跳过两数之后的逻辑运算
     * 4、双指针的思路：在求两数之和时，左指针往右移（右指针固定），若两数之和匹配，则记录，右指针则往左移一位（匹配不上右指针也往左移）
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList();

        for (int i = 0; i < nums.length - 2 && nums[i] < 0; i++) {
            while (nums[i] == nums[i + 1] && nums[i + 1] == nums[i + 2] && nums[i] < 0) {
                i++;
            }

            int target = 0 - nums[i];
            int lp = i + 1, rp = nums.length - 1;
            while (lp < rp) {
                if (target == nums[lp] + nums[rp]) {
                    result.add(Arrays.asList(nums[i], nums[lp], nums[rp]));
                    lp++;
                    rp--;

                    while (lp + 1 < nums.length && nums[lp] == nums[lp + 1] && nums[lp] < target) {
                        lp ++;
                    }
                    while (nums[rp] == nums[rp - 1] && nums[rp] > target) {
                        rp --;
                    }

                } else if (target < nums[lp] + nums[rp]) {
                    rp--;
                } else {
                    lp++;
                }
            }

            //跳一个过去
            if (nums[i] == nums[i + 1] && nums[i] < 0) {
                i++;
            }
        }

        if (Arrays.stream(nums).filter(t -> t == 0).count() >= 3) {
            result.add(Arrays.asList(0, 0, 0));
        }
        return result;
    }
}
