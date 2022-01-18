package chapter12;

import java.util.stream.Stream;

/**
 * 编写一个函数，输入是一个无符号整数（以二进制串的形式），返回其二进制表达式中数字位数为 '1' 的个数（也被称为汉明重量）。
 *
 * 提示：
 * 请注意，在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，并且不应影响您的实现，因为无论整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。
 * 在 Java 中，编译器使用二进制补码记法来表示有符号整数。因此，在上面的示例3中，输入表示有符号整数 -3。
 *
 * 输入：00000000000000000000000000001011
 * 输出：3
 * 解释：输入的二进制串 00000000000000000000000000001011中，共有三位为 '1'。
 *
 * @author luwu
 * @date 2022/1/17 16:09
 */
public class LeetCode_hammingWeight {

    public static void main(String[] args) {
        System.out.println(hammingWeight(-5));
    }

    public static int hammingWeight(int n) {
        System.out.println(Integer.toBinaryString(n));
        int count = 0;
        for (char c : Integer.toBinaryString(n).toCharArray()) {
            if (c == '1') {
                count ++;
            }
        }
//        if (n < 0) {
//            n *= -1;
//        }
//
//        while (n != 0) {
//            if (n % 2 == 1) {
//                count ++;
//            }
//            n /= 2;
//        }
        return count;
    }
}
