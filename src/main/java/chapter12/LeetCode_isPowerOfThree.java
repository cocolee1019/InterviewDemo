package chapter12;

/**
 * 给定一个整数，写一个函数来判断它是否是3的幂次方。如果是，返回 true ；否则，返回 false 。
 * <p>
 * 整数 n 是 3 的幂次方需满足：存在整数 x 使得 n == 3<sup>x</sup>
 * <p>
 * 示例 1：
 * 输入：n = 27
 * 输出：true
 * <p>
 * 示例 2：
 * 输入：n = 0
 * 输出：false
 * <p>
 * 示例 3：
 * 输入：n = 9
 * 输出：true
 * <p>
 * 示例 4：
 * 输入：n = 45
 * 输出：false
 * <p>
 * 提示：
 * -231 <= n <= 231 - 1
 * <p>
 * 进阶：不使用递归或循环完成
 *
 * 可以使用换底公式
 * log10要比loge准确
 *
 * 解法二：
 * 可以使用约数方式：比如3的100倍可以被3的1000倍整除
 *
 */
public class LeetCode_isPowerOfThree {

    public static void main(String[] args) {
        System.out.println(isPowerOfThree(177148));
    }

    public static boolean isPowerOfThree(int n) {

        if (n <= 0) {
            return false;
        }
        double v = Math.log10(n) / Math.log10(3.0);
        long v1 = (long)(v * 1000000000000000L);
        return v1 % 1000000000000000L == 0;
    }
}
