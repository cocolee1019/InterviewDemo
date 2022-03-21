package chapter12;

/**
 * 给你一个整数数组nums ，判断这个数组中是否存在长度为 3 的递增子序列。
 * 如果存在这样的三元组下标 (i, j, k)且满足 i < j < k ，使得nums[i] < nums[j] < nums[k] ，返回 true ；否则，返回 false 。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,3,4,5]
 * 输出：true
 * 解释：任何 i < j < k 的三元组都满足题意
 * <p>
 * 示例 2：
 * 输入：nums = [5,4,3,2,1]
 * 输出：false
 * 解释：不存在满足题意的三元组
 * <p>
 * 示例 3：
 * 输入：nums = [2,1,5,0,4,6]
 * 输出：true
 * 解释：三元组 (3, 4, 5) 满足题意，因为 nums[3] == 0 < nums[4] == 4 < nums[5] == 6
 *
 * @author luwu
 * @date 2022/3/17 09:45
 */
public class LeetCode_increasingTriplet {

    public static void main(String[] args) {
        System.out.println(increasingTriplet(new int[]{1, 100, 2, 50}));
    }

    /**
     * 思路：
     * 用二个数组，分别记录距离为1的下标、距离为2的下标
     *
     * @param nums
     * @return
     */
    public static boolean increasingTriplet(int[] nums) {

        int dp1 = nums[0], dp2 = Integer.MAX_VALUE;

        //第i个字符是我
        for (int i = 1; i < nums.length; i++) {
            if (dp2 < nums[i]) {
                return true;
            } else if (dp1 < nums[i]) {
                dp2 = nums[i];
            } else {
                dp1 = nums[i];
            }
        }

        return false;
    }
}
