package chapter12;

import java.util.Arrays;

/**
 *给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 */
public class LeetCode_PlusOne {

    public static void main(String[] args) {
        int[] num = {9,9,9,8};
        System.out.println(Arrays.toString(plusOne(num)));
    }

    public static int[] plusOne(int[] digits) {
        int k = 0, i=digits.length-1;
        for (; i>=0; i--) {
            if (digits[i] + 1 < 10) {
                digits[i] += 1;
                break;
            }

            digits[i] = 0;
        }

        if (i < 0) {
            int[] res = new int[digits.length + 1];
            res[0] = 1;
            return res;
        }

        return digits;
    }
}
