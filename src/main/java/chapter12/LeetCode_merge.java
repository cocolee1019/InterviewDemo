package chapter12;

import java.util.Arrays;

/**
 * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。你可以假设 nums1 的空间大小等于 m + n，这样它就有足够的空间保存来自 nums2 的元素。
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnumcr/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class LeetCode_merge {

    public static void main(String[] args) {
//        int[] a = new int[]{1,2,3,0,0,0};
//        int[] b = new int[]{2,5,6};
        int[] a = new int[]{0};
        int[] b = new int[]{1};

        merge(a, 0, b, 1);
        System.out.println(Arrays.toString(a));
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int mt = m-1, nt = n-1, tt = nums1.length-1;
        for (; mt>=0 && nt>=0; ) {
            nums1[tt--] = nums1[mt] > nums2[nt] ? nums1[mt--] : nums2[nt--];
        }

        while (nt >= 0){
            nums1[tt--] = nums2[nt--];
        }
    }
}
