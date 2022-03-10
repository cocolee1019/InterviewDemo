package chapter12;

import java.util.*;

/**
 *
 * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
 * 字母异位词 是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母通常恰好只用一次。
 *
 * 示例 1:
 * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
 *
 * 示例 2:
 * 输入: strs = [""]
 * 输出: [[""]]
 *
 * 示例 3:
 * 输入: strs = ["a"]
 * 输出: [["a"]]
 *
 * @author luwu
 * @date 2022/3/10 09:53
 */
public class LeetCode_groupAnagrams {

    public static void main(String[] args) {
        System.out.println(groupAnagrams2(new String[]{"vow","pam","vic","bee","ken","jay","oft","sue","joy","yuk","sac","mac","inc","big","icy","bus","lob","flo","day","aol","eel","rex","jug","man","cod","mix","guy","ken","ion","meg","tot","noe","ref","ito","inn","van","cry","tad"}));
    }

    /**
     * 执行用时：24 ms, 在所有 Java 提交中击败了8.91%的用户
     * 内存消耗：44 MB, 在所有 Java 提交中击败了44.19%的用户
     *
     * @param strs
     * @return
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<Integer, List<List<String>>> arrsValMap = new HashMap<>();

        for (String str : strs) {
            int val = toIntValue(str.toCharArray());
            List<List<String>> valueList = arrsValMap.get(val);
            if (valueList == null) {
                List<List<String>> list = new ArrayList<>();
                List<String> sList = new ArrayList<>();
                sList.add(str);
                list.add(sList);
                arrsValMap.put(val, list);
                continue;
            }

            int i = 0;
            for (; i < valueList.size(); i++) {
                List<String> list = valueList.get(i);
                String src = list.get(0);
                char[] srcCh = src.toCharArray();
                char[] tarCh = str.toCharArray();
                Arrays.sort(srcCh);
                Arrays.sort(tarCh);
                if (equals(srcCh, tarCh)) {
                    list.add(str);
                    break;
                }
            }

            if (i == valueList.size()) {
                List<String> sList = new ArrayList<>();
                sList.add(str);
                valueList.add(sList);
            }
        }

        List<List<String>> res = new ArrayList<>();
        for (Map.Entry<Integer, List<List<String>>> entry : arrsValMap.entrySet()) {
            res.addAll(entry.getValue());
        }
        return res;
    }

    /**
     * 执行用时：24 ms, 在所有 Java 提交中击败了8.91%的用户
     * 内存消耗：44 MB, 在所有 Java 提交中击败了44.19%的用户
     *
     * @param strs
     * @return
     */
    public static List<List<String>> groupAnagrams2(String[] strs) {
        Map<String, ArrayList<String>> arrsValMap = new HashMap<>();
        for (String str : strs) {
            char[] array = str.toCharArray();
            Arrays.sort(array);
            String key = new String(array);
            ArrayList<String> list = arrsValMap.getOrDefault(key, new ArrayList<String>());
            list.add(str);
            arrsValMap.put(key, list);
        }
        return new ArrayList<>(arrsValMap.values());
    }

    public static int toIntValue(char[] arr) {
        int val = 0;
        for (int ch : arr) {
            val += ch;
        }
        return val;
    }

    public static boolean equals(char[] arr, char[] trr) {
        if (arr.length != trr.length) {
            return false;
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != trr[i]) {
                return false;
            }
        }

        return true;
    }

}
