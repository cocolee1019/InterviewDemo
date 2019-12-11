package chapter12;

import javax.xml.transform.Source;

/**
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，
 * 所有的偶数位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。
 */
public class Algo1 {

    public static void main(String[] args) {
        int[] arr = {1,8,4,2,5,7,6,3,9};
        for (int i=1; i<arr.length; i++) {
            if (arr[i] % 2 == 1) {
                int t = arr[i];
                int j = i - 1;
                while (j>=0 && arr[j]%2 == 0) {
                    arr[j+1] = arr[j];
                    j--;
                }
                arr[j+1] = t;
            }
        }
        for (int i=0; i<arr.length; i++) {
            System.out.print(arr[i]);
        }
    }
}
