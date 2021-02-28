package chapter12;

/**
 * 实现 strStr() 函数。
 * <p>
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
 * <p>
 * 示例 1:
 * <p>
 * 输入: haystack = "hello", needle = "ll"
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: haystack = "aaaaa", needle = "bba"
 * 输出: -1
 * 说明:
 * <p>
 * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 * <p>
 * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
 * <p>
 * "mississippi"
 * "issipi"
 */
public class LeetCode_StrStr {

    public static void main(String[] args) {
        System.out.println(strStr("a", "a"));
    }

    public static int strStr(String haystack, String needle) {
        if ("".equals(needle)) {
            return 0;
        }

        if (haystack.length() < needle.length())
            return -1;

        //该算法超时
//        for (int i = 0; i < haystack.length(); i++) {
//            int k = 0;
//            if (haystack.charAt(i) == needle.charAt(k)) {
//                int j = i + k;
//                while (k < needle.length() && j < haystack.length() && needle.charAt(k) == haystack.charAt(j)) {
//                    k++;
//                    j++;
//                }
//
//                if (k == needle.length()) {
//                    return i;
//                }
//            }
//        }
//        return -1;

        //试试String.indexof()是否超时：结论：0ms
//        return haystack.indexOf(needle);

        // jiajun`s algo 构造两个数组，用于标识连续数，避免回溯
        //  失败：未考虑本串的循环情况
//        char harr[] = haystack.toCharArray();
//        char narr[] = needle.toCharArray();
//        int a[] = new int[haystack.length()];
//        int b[] = new int[needle.length()];
//        int i = haystack.length() - 1;
//        while (i >= 0) {
//            if (i == haystack.length() - 1)
//                a[i] = 0;
//            else {
//                a[i] = harr[i + 1] == harr[i] ? a[i + 1] + 1 : 0;
//            }
//            i--;
//        }
//
//        i = needle.length() - 1;
//        while (i >= 0) {
//            if (i == needle.length() - 1)
//                b[i] = 0;
//            else {
//                b[i] = narr[i + 1] == narr[i] ? b[i + 1] + 1 : 0;
//            }
//            i--;
//        }
//
//        i = 0;
//        while (i < haystack.length()) {
//            if (harr[i] == narr[0]) {
//                int k = 0;
//                do {
//                    int s = a[i] < b[k] ? a[i] : b[k];
//                    i = i + 1 + s;
//                    k = k + 1 + s;
//                } while (i < harr.length && k < narr.length && harr[i] == narr[k]);
//
//                //正确结果
//                if (k >= narr.length)
//                    return i - narr.length;
//            } else
//                i = i + a[i] + 1;
//        }
//        return -1;

        int max = haystack.length() - needle.length();

        char first = needle.charAt(0);
        int count = needle.length();
        int count2 = haystack.length();
        char[] harr = haystack.toCharArray();
        char[] narr = needle.toCharArray();

        //模仿indexOf
        for (int i=0; i<=max; i++) {
                while (harr[i] != first && ++i <= max);
                if (i <= max) {
                    int j = i + 1;
                    int end = j + count - 1;
                    for (int k = 1; j < end  && harr[j] == narr[k]; k++,j++);
                    if (j == end) {
                        return i;
                    }
                }
        }
        return -1;
    }
}
