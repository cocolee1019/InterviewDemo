package chapter12;

import java.util.HashMap;
import java.util.Map;

/**
 * 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 *
 * 例如， 罗马数字 2 写做II，即为两个并列的 1 。12 写做XII，即为X+II。 27 写做XXVII, 即为XX+V+II。
 *
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做IIII，而是IV。数字 1 在数字 5 的左边，所表示的数等于大数 5
 * 减小数 1 得到的数值 4 。同样地，数字 9 表示为IX。这个特殊的规则只适用于以下六种情况：
 *
 * I可以放在V(5) 和X(10) 的左边，来表示 4 和 9。
 * X可以放在L(50) 和C(100) 的左边，来表示 40 和90。
 * C可以放在D(500) 和M(1000) 的左边，来表示400 和900。
 * 给定一个罗马数字，将其转换成整数。
 *
 * 示例 1:
 * 输入: s = "III"
 * 输出: 3
 *
 * 示例 2:
 * 输入: s = "IV"
 * 输出: 4
 *
 * 示例 3:
 * 输入: s = "IX"
 * 输出: 9
 *
 * 示例 4:
 * 输入: s = "LVIII"
 * 输出: 58
 * 解释: L = 50, V= 5, III = 3.
 *
 * 示例 5:
 * 输入: s = "MCMXCIV"
 * 输出: 1994
 * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
 *
 *
 * 提示：
 * 1 <= s.length <= 15
 * s 仅含字符 ('I', 'V', 'X', 'L', 'C', 'D', 'M')
 * 题目数据保证 s 是一个有效的罗马数字，且表示整数在范围 [1, 3999] 内
 * 题目所给测试用例皆符合罗马数字书写规则，不会出现跨位等情况。
 * IL 和 IM 这样的例子并不符合题目要求，49 应该写作 XLIX，999 应该写作 CMXCIX 。
 * 关于罗马数字的详尽书写规则，可以参考 罗马数字 - Mathematics 。
 *
 * 重複數次：一個羅馬數字重複幾次，就表示這個數的幾倍。
 * 右加左減：
 * 在較大的羅馬數字的右邊記上較小的羅馬數字，表示大數字加小數字。
 * 在較大的羅馬數字的左邊記上較小的羅馬數字，表示大數字减小數字。
 * 左减的数字有限制，仅限于I、X、C。比如45不可以写成VL，只能是XLV
 * 但是，左減時不可跨越一個位值。比如，99不可以用IC（100-1）表示，而是用XCIX（[100-10]+[10-1]）表示。（等同於阿拉伯數字每位數字分別表示。）
 * 左減數字必須為一位，比如8寫成VIII，而非IIX。
 * 右加數字不可連續超過三位，比如14寫成XIV，而非XIIII。（見下方“數碼限制”一項。）
 * 加線乘千：
 * 在羅馬數字的上方加上一條橫線或者加上下標的M，表示將這個數乘以1000，即是原數的1000倍。
 * 同理，如果上方有兩條橫線，即是原數的1000000（1000^{{2}}）倍。
 * 數碼限制：
 * 同一數碼最多只能连续出現三次，如40不可表示為XXXX，而要表示為XL。
 * 例外：由於IV是古羅馬神話主神朱庇特（即IVPITER，古羅馬字母裡沒有J和U）的首字，因此有時用IIII代替IV。
 *
 * @author luwu
 * @date 2022/1/12 17:01
 */
public class LeetCode_romanToInt {

    /**
     * I         1
     * V         5
     * X         10
     * L         50
     * C         100
     * D         500
     * M         1000
     */

    static Map<Character, Integer> dict = new HashMap<>();
    static {
        dict.put('I', 1);
        dict.put('V', 5);
        dict.put('X', 10);
        dict.put('L', 50);
        dict.put('C', 100);
        dict.put('D', 500);
        dict.put('M', 1000);

    }

    public static void main(String[] args) {
//        System.out.println(romanToInt("I"));
//        System.out.println(romanToInt("II"));
//        System.out.println(romanToInt("III"));
//        System.out.println(romanToInt("IV"));
//        System.out.println(romanToInt("V"));
//        System.out.println(romanToInt("VI"));
//        System.out.println(romanToInt("VII"));
//        System.out.println(romanToInt("VIII"));
//        System.out.println(romanToInt("IX"));
//        System.out.println(romanToInt("X"));
//        System.out.println(romanToInt("XI"));
//        System.out.println(romanToInt("XII"));
//        System.out.println(romanToInt("XIII"));
//        System.out.println(romanToInt("XIV"));
//        System.out.println(romanToInt("XV"));
//        System.out.println(romanToInt("XVI"));
//        System.out.println(romanToInt("XVII"));
//        System.out.println(romanToInt("XVIII"));
//        System.out.println(romanToInt("XIX"));
//        System.out.println(romanToInt("XX"));
//        System.out.println(romanToInt("XXI"));
//        System.out.println(romanToInt("XXII"));
//        System.out.println(romanToInt("XXIII"));
//        System.out.println(romanToInt("XXIV"));
//        System.out.println(romanToInt("XXV"));
//        System.out.println(romanToInt("XXVI"));
//        System.out.println(romanToInt("XXVII"));
//        System.out.println(romanToInt("XXVIII"));
//        System.out.println(romanToInt("XXIX"));
//        System.out.println(romanToInt("XXX"));
//        System.out.println(romanToInt("XXXI"));
//        System.out.println(romanToInt("XXXII"));
//        System.out.println(romanToInt("XXXIII"));
//        System.out.println(romanToInt("XXXIV"));
//        System.out.println(romanToInt("XXXV"));
//        System.out.println(romanToInt("XXXVI"));
//        System.out.println(romanToInt("XXXVII"));
//        System.out.println(romanToInt("XXXVIII"));
//        System.out.println(romanToInt("XXXIX"));
//        System.out.println(romanToInt("XL"));
//        System.out.println(romanToInt("XLI"));
//        System.out.println(romanToInt("XLII"));
//        System.out.println(romanToInt("XLIII"));
//        System.out.println(romanToInt("XLIV"));
//        System.out.println(romanToInt("XLV"));
//        System.out.println(romanToInt("XLVI"));
//        System.out.println(romanToInt("XLVII"));
//        System.out.println(romanToInt("XLVIII"));
//        System.out.println(romanToInt("XLIX"));
//        System.out.println(romanToInt("L"));
        System.out.println(romanToInt("LX"));// 60
        System.out.println(romanToInt("LXIV"));// 64
        System.out.println(romanToInt("LXIX"));// 69
        System.out.println(romanToInt("LXX"));// 70
        System.out.println(romanToInt("LXXIV"));// 74
        System.out.println(romanToInt("LXXVI"));// 76
        System.out.println(romanToInt("LXXIX"));// 79
        System.out.println(romanToInt("LXXX"));// 80
        System.out.println(romanToInt("LXXXIV"));// 84
        System.out.println(romanToInt("LXXXVI"));// 86
        System.out.println(romanToInt("LXXXIX"));// 89
        System.out.println(romanToInt("LXXXX"));// 90
        System.out.println(romanToInt("LXXXX"));// 90

    }

    public static int romanToInt(String s) {
        int sum = dict.get(s.charAt(s.length()-1));
        for (int i = s.length()-1; i > 0; i--) {
            int left = dict.get(s.charAt(i));
            int right = dict.get(s.charAt(i - 1));
            if (left > right) {
                sum -= right;
            } else {
                sum += right;
            }
        }
        return sum;
    }
}
