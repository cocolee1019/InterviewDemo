package chapter12;

/**
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，
 * 所有的偶数位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。
 */
public class Algo1 {

    class Solution {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        public String intToRoman(int num) {
            StringBuffer roman = new StringBuffer();
            for (int i = 0; i < values.length; ++i) {
                int value = values[i];
                String symbol = symbols[i];
                while (num >= value) {
                    num -= value;
                    roman.append(symbol);
                }
                if (num == 0) {
                    break;
                }
            }
            return roman.toString();
        }
    }
}
