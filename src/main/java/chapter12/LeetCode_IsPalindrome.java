package chapter12;

/**
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * <p>
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: "race a car"
 * 输出: false
 */
public class LeetCode_IsPalindrome {

    public static void main(String[] args) {
        System.out.println(isPalindrome("race a car"));
    }

    private static boolean isPalindrome(String s) {
        s = s.toLowerCase();
        int l = 0, r = s.length() - 1;
        while (l < r) {
            boolean f = false;
            char lc, rc;
            if (!isNumberOrLetter(lc = s.charAt(l))) {
                l++;
                f = true;
            }
            if (!isNumberOrLetter(rc = s.charAt(r))) {
                r--;
                f = true;
            }
            if (f) {
                continue;
            }
            if (lc != rc) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }

    private static boolean isNumberOrLetter(char ch) {
        return (ch >= 'a' && ch <= 'z') || (ch >= '0' && ch <= '9');
    }
}
