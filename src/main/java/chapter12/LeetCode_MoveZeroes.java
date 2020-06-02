package chapter12;

import java.util.Arrays;

public class LeetCode_MoveZeroes {

    public static void main(String[] args) {
        int[] aNum = {1, 2, 0, 3, 4, 5, 0, 2, 0};
        moveZeroes2(aNum);
    }

    public static void moveZeroes(int[] nums) {
        int l = nums.length - 1;
        for (int j = l; j >= 0; j--) {
            if (nums[j] == 0) {
                int i = j;
                while (i < l) {
                    //同前一个数交换位置
                    int t = nums[i + 1];
                    nums[i + 1] = nums[i];
                    nums[i] = t;
                    i++;
                }
                l--;
            }
        }
        System.out.println(Arrays.toString(nums));
    }

    public static void moveZeroes2(int[] nums) {
        int index = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != 0) {
                nums[index] = nums[j];
                if (j != index)
                    nums[j] = 0;
                index++;
            }
        }
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 最优解
     *
     * @param nums
     */
    public static void optimalSolution(int[] nums) {
        int index = 0;//定义当前下标，从0开始
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {//此时不为0
                nums[index] = nums[i];//赋值进来
                if (i != index) {//如果当前指针和遍历位置不一致，遍历所在指针变为0
                    nums[i] = 0;
                }
                index++;
            }
        }
    }
}
