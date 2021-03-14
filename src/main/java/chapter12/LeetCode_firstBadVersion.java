package chapter12;

/**
 * 第一个错误的版本
 * 你是产品经理，目前正在带领一个团队开发新的产品。不幸的是，你的产品的最新版本没有通过质量检测。由于每个版本都是基于之前的版本开发的，所以错误的版本之后的所有版本都是错的。
 *
 * 假设你有 n 个版本 [1, 2, ..., n]，你想找出导致之后所有版本出错的第一个错误的版本。
 * 你可以通过调用 bool isBadVersion(version) 接口来判断版本号 version 是否在单元测试中出错。实现一个函数来查找第一个错误的版本。你应该尽量减少对调用 API 的次数。
 * 示例:
 * 给定 n = 5，并且 version = 4 是第一个错误的版本。
 * 调用 isBadVersion(3) -> false
 * 调用 isBadVersion(5) -> true
 * 调用 isBadVersion(4) -> true
 * 所以，4 是第一个错误的版本。 
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnto1s/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class LeetCode_firstBadVersion {

    public static void main(String[] args) {
        System.out.println(firstBadVersion(2126753390));
    }
    /**
     * 二分查找
     * 使用递归，超出时间限制
     * @param n
     * @return
     */
    public static int firstBadVersion(int n) {
        int i = binarySearch2(0, n);
        if (isBadVersion(i)) {
            return i;
        }
        return i+1;
    }

    static int binarySearch(int start, int end) {
        if (start>=end) {
            return start;
        }
        int mid = start + (end - start) / 2;
        if (isBadVersion(mid)) {
            return binarySearch(start, mid-1);
        }
        return binarySearch(mid+1, end);
    }

    static int binarySearch2(int start, int end) {
        int mid = start;
        while (start < end) {
            mid = start + (end - start) / 2;
            if (isBadVersion(mid)) {
                if (!isBadVersion(mid - 1)) {
                    break;
                }
                end = mid - 1;
            }else {
                if (isBadVersion(mid + 1)) {
                    mid++;
                    break;
                }
                start = mid + 1;
            }
        }
        return mid;
    }

    static boolean isBadVersion(int version) {
        if (version > 1702766719) {
            return true;
        }
        return false;
    }
}
