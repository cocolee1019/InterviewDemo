package chapter12;

/**
 * 最大子序和
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
 */
public class LeetCode_maxSubArray {

    public static void main(String[] args) {
        System.out.println(maxSubArray(new int[]{-2, -1, -3, -4, -1, -2, -1, -5, -4}));
//        System.out.println(maxSubArray(new int[]{8,-19,5,-4,20}));
    }

    /**
     * 动态规划算法
     * @param nums
     * @return
     */
    public static int maxSubArray(int[] nums) {
        int maxSumValue = nums[0];
        for (int i=1; i<nums.length; i++) {
            nums[i] = nums[i-1] > 0 ? nums[i-1] + nums[i] : nums[i];
            maxSumValue = maxSumValue > nums[i] ? maxSumValue : nums[i];
        }
        return maxSumValue;
    }

    public static int maxSubArray2(int[] nums) {
        if(nums.length == 1){
            return nums[0];
        }
        int maxSubArray = nums[0];
        int sum = nums[0];
        for(int i = 1; i < nums.length; i++){
            //sum要想有资格继续壮大 就必须大于 0 否则还不如恢复0
            sum = Math.max(sum,0);
            sum += nums[i];
            //maxSubArray始终取最大值
            maxSubArray = Math.max(sum,maxSubArray);
        }
        return maxSubArray;
    }
}
