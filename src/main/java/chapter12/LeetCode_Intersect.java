package chapter12;

import org.apache.activemq.broker.region.policy.LastImageSubscriptionRecoveryPolicy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntFunction;

/**
 * 给定两个数组，编写一个函数来计算它们的交集。
 *
 * 说明：
 * 输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致。
 * 我们可以不考虑输出结果的顺序。
 */
public class LeetCode_Intersect {

    public static void main(String[] args) {
        int[] a = {4,5,2,1,5,6,7,7};
        int[] b = {1,3,1,1,5,6,1,7};
        System.out.println(Arrays.toString(intersect(a, b)));
    }

    public static int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        List<Integer> result = new ArrayList<>();

        //先对齐
        int i = 0, j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                result.add(nums1[i]);
                i++;
                j++;
            }else if (nums1[i] < nums2[j]) {
                i++;
            }else {
                j++;
            }
        }
        if (result.isEmpty()) {
            return new int[0];
        }
        int k = 0;
        int res[] = new int[result.size()];
        for (Integer integer : result) {
            res[k++] = integer;
        }
        return res;
    }
}
