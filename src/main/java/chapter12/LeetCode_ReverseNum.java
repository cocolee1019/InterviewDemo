package chapter12;

/**
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 *
 * 示例 1:
 * 输入: 123
 * 输出: 321
 *
 * 示例 2:
 * 输入: -123
 * 输出: -321
 *
 * 示例 3:
 * 输入: 120
 * 输出: 21
 *
 * 这道题知识点分析：
 * 1、负数的余数还是负数。
 * 2、判断一个int型的精度是否丢失，可以用long型转成int型，再转成long型来判断。
 */
public class LeetCode_ReverseNum {

    public static void main(String[] args) {
        System.out.println(reverse(-1235));
    }

    public static int reverse(int x) {
        long result = 0;
        while (x != 0) {
            result = result * 10 + x % 10;
            x /= 10;
        }
        return (int)result == result ? (int)result : 0;
    }
}
