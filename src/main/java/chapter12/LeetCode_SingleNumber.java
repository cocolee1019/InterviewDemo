package chapter12;
import java.util.Arrays;

/**
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 */
public class LeetCode_SingleNumber {

    public static void main(String[] args) {
        int[] a = {9,4,3,2,5,5,3,4,3,2};
        System.out.println(profit(a));
    }

    public static int singleNumber(int[] nums) {
        Arrays.sort(nums);

        if (nums.length == 1) {
            return nums[0];
        }

        if (nums[0] < nums[1]) {
            return nums[0];
        }

        for (int i=1; i < nums.length-1; i++) {
            if (nums[i] > nums[i-1] && nums[i] < nums[i+1]) {
                return nums[i];
            }
        }

        return nums[nums.length-1];
    }

    public static int profit(int[] nums) {
        int result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            // ^ 异或运算符， 同位为0， 异位为1。
            result ^= nums[i];
        }

        return result;
    }
}
