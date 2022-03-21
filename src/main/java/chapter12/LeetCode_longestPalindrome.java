package chapter12;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 最长回文子串
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 *
 * 示例 1：
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 *
 * 示例 2：
 * 输入：s = "cbbd"
 * 输出："bb"
 *
 * 提示：
 * 1 <= s.length <= 1000
 * s 仅由数字和英文字母组成
 *
 * @author luwu
 * @date 2022/3/15 10:53
 */
public class LeetCode_longestPalindrome {

    public static void main(String[] args) {
        System.out.println(longestPalindrome2("aaaaa"));
    }

    /**
     * 思路：
     * 1、将所有相同的字符排列出来
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        Map<Character, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            List<Integer> indexList = map.get(ch);
            if (indexList == null) {
                indexList = new ArrayList<>();
                map.put(ch, indexList);
            }
            indexList.add(i);
        }

        int res = 1;
        int resl = 0, resr = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            List<Integer> indexList = map.get(ch);
            if (indexList.size() == 1) {
                continue;
            }
            int index;
            for (int j = indexList.size() - 1; (index = indexList.get(j)) > i; j--) {
                if (i > resl && index < resr) {
                    break;
                }
                if (palindrome(s, i, index)) {
                    if (res < index - i + 1) {
                        resl = i;
                        resr = index;
                        res = index - i;
                    }
                }
            }
        }

        return s.substring(resl, resr + 1);
    }

    public static boolean palindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
              break;
            }

            left ++;
            right --;
        }

        if (left >= right) {
            return true;
        }

        return false;
    }

    public static String longestPalindrome2(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }

        int maxLen = 1;
        int begin = 0;
        // dp[i][j] 表示 s[i..j] 是否是回文串
        boolean[][] dp = new boolean[len][len];
        // 初始化：所有长度为 1 的子串都是回文串
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        char[] charArray = s.toCharArray();
        // 递推开始
        // 先枚举子串长度
        for (int L = 2; L <= len; L++) {
            // 枚举左边界，左边界的上限设置可以宽松一些
            for (int i = 0; i < len; i++) {
                // 由 L 和 i 可以确定右边界，即 j - i + 1 = L 得
                int j = L + i - 1;
                // 如果右边界越界，就可以退出当前循环
                if (j >= len) {
                    break;
                }

                if (charArray[i] != charArray[j]) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                // 只要 dp[i][L] == true 成立，就表示子串 s[i..L] 是回文，此时记录回文长度和起始位置
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }

}
