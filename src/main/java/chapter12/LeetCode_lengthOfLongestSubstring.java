package chapter12;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 无重复字符的最长子串
 * 给定一个字符串 s ，请你找出其中不含有重复字符的最长子串的长度。
 *
 * 示例1:
 * 输入: s = "abcabcbb"
 * 输出: 3 
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 * 示例 2:
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 *
 * 示例 3:
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是"wke"，所以其长度为 3。
 *     请注意，你的答案必须是 子串 的长度，"pwke"是一个子序列，不是子串。
 * 
 *
 * 提示：
 * 0 <= s.length <= 5 * 104
 * s 由英文字母、数字、符号和空格组成
 *
 * @author luwu
 * @date 2022/3/14 16:56
 */
public class LeetCode_lengthOfLongestSubstring {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring2("a"));
    }

    /**
     * 借助map记录当前子串的值
     *
     * 执行用时：5 ms, 在所有 Java 提交中击败了67.72%的用户
     * 内存消耗：41.3 MB, 在所有 Java 提交中击败了36.44%的用户
     * 通过测试用例：987 / 987
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) {
            return 0;
        }

        Map<Character, Integer> record = new HashMap<>();

        char[] chars = s.toCharArray();
        int maxLen = 1;
        record.put(chars[0], 0);
        int point = 0;

        for (int i = 1; i < chars.length; i++) {
            if (record.containsKey(chars[i]) && record.get(chars[i]) >= point) {
                maxLen = i - point > maxLen ? i - point : maxLen;
                point = record.get(chars[i]) + 1;
            }
            record.put(chars[i], i);
        }
        return chars.length - point > maxLen ? chars.length - point : maxLen;
    }

    /**
     * 题解：滑动窗口
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring2(String s) {
        Set<Character> set = new HashSet<>();
        int n = s.length(), rk = -1, ans = 0;
        for (int i = 0; i < n && rk+1 < n; i++) {
            if (i != 0) {
                set.remove(s.charAt(i - 1));
            }
            while (rk+1 < n && !set.contains(s.charAt(rk+1))) {
                set.add(s.charAt(rk + 1));
                rk++;
            }
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }
}
