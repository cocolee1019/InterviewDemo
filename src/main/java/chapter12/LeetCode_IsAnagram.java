package chapter12;

import java.util.Arrays;

/**
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 *
 * 示例 1:
 *
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * 示例 2:
 *
 * 输入: s = "rat", t = "car"
 * 输出: false
 * 说明:
 * 你可以假设字符串只包含小写字母。
 *
 */
public class LeetCode_IsAnagram {

    public static void main(String[] args) {
        System.out.println(isAnagram2("car", "arc"));
    }

    public static boolean isAnagram(String s, String t) {

        int[] dict = new int[26];
        for (int i = 0; i < s.length(); i++) {
            dict[s.charAt(i) - 'a']++;
        }

        for (int i = 0; i < t.length(); i++) {
            dict[t.charAt(i) - 'a']--;
        }

        for (int i = 0; i < 26; i++) {
            if (dict[i] != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 排序后equals
     * @param s
     * @param t
     * @return
     */
    public static boolean isAnagram2(String s, String t) {
        char[] ss = s.toCharArray();
        char[] st = t.toCharArray();

        Arrays.sort(ss);
        Arrays.sort(st);

        return Arrays.equals(ss, st);
    }
}
