package chapter12;

/**
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 * <p>
 * 示例：
 * <p>
 * s = "leetcode"
 * 返回 0
 * <p>
 * s = "loveleetcode"
 * 返回 2
 */
public class LeetCode_FirstUniqChar {

    public static void main(String[] args) {
        System.out.println(firstUniqChar("cc"));
    }

    public static int firstUniqChar(String s) {
        int[] index = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char a = s.charAt(i);
            index[122 - a]++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (index[122 - s.charAt(i)] == 1) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 这种方式的优点是确定了最早的值后，就不会继续再往下计算。
     * @param s
     * @return
     */
    public static int OptimalSlution(String s) {
        int res = s.length();
        for (char c = 'a'; c <= 'z'; c++) {
            int idx = s.indexOf(c);
            if (idx >= 0 && idx == s.lastIndexOf(c)) {
                res = Math.min(idx, res);
            }
        }
        return res == s.length() ? -1 : res;
    }
}
