package chapter12;

/**
 * 给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 高度平衡 二叉搜索树。
 * 高度平衡 二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树。
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xninbt/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class LeetCode_Tree_sortedArrayToBST {

    public static void main(String[] args) {
//        TreeNode treeNode = sortedArrayToBST(new int[]{-10, -3, 0, 5, 9});
        TreeNode treeNode = sortedArrayToBST(new int[]{1,3});
        System.out.println(treeNode);
    }

    private static TreeNode sortedArrayToBST(int[] nums) {
        //nums已排序，取中值
        return work(nums, 0, nums.length-1);
    }

    private static TreeNode work(int[] nums, int start, int end) {
        if (end < start) {
            return null;
        }

        int avg = (start + end) / 2;
        TreeNode node = new TreeNode(nums[avg]);
        node.left = work(nums, start, avg - 1);
        node.right = work(nums, avg + 1, end);
        return node;
    }
}

