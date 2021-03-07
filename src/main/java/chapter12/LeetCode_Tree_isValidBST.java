package chapter12;

import java.util.HashMap;

/**
 * 验证二叉搜索树
 * <p>
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * 假设一个二叉搜索树具有如下特征：
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 */
public class LeetCode_Tree_isValidBST {

    static HashMap<TreeNode, Integer> temporaryMaxValue = new HashMap<>();
    static HashMap<TreeNode, Integer> temporaryMinValue = new HashMap<>();

    public static void main(String[] args) {
//        System.out.println(isValidBST(TreeNode.getInstance(new Integer[]{-2147483648,null,2147483647}, 0)));
//        System.out.println(isValidBST(TreeNode.getInstance(new Integer[]{2,1,3}, 0)));
//        System.out.println(isValidBST(TreeNode.getInstance(new Integer[]{3,2,null,1,-2147483648}, 0)));
//        System.out.println(isValidBST(TreeNode.getInstance(new Integer[]{3,1,5,0,2,4,6}, 0)));
//        System.out.println(isValidBSTInner(TreeNode.getInstance(new Integer[]{3,1,5,0,2,4,6}, 0), Long.MIN_VALUE, Long.MAX_VALUE));
//        System.out.println(isValidBSTInner(TreeNode.getInstance(new Integer[]{5,4,6,null,null,3,7}, 0), Long.MIN_VALUE, Long.MAX_VALUE));
        System.out.println(isValidBSTInner(TreeNode.getInstance(new Integer[]{10,3,20,1,4,15,21,null,null,null,null,5,16}, 0), Long.MIN_VALUE, Long.MAX_VALUE));

    }

    public static boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }

        //效率低下
//        return bsfCompare(root.left, root.val, 1) && bsfCompare(root.right, root.val, -1) && isValidBST(root.left) && isValidBST(root.right);

        boolean flag = true;
        Integer tempVal;
        if (root.left != null) {
            flag = root.left.val < root.val;
            flag = flag && bsfSearchMaxvalue(root.left).intValue() < root.val ? true : false;
        }

        if (flag && root.right != null) {
            flag = root.right.val > root.val;
            flag = flag && bsfSearchMinvalue(root.right).intValue() > root.val ? true : false;
        }
        return flag && isValidBST(root.left) && isValidBST(root.right);
    }

    /**
     * 直接比较效率太低
     *
     * @param root
     * @param value
     * @param nag
     * @return
     */
    public static boolean bsfCompare(TreeNode root, int value, int nag) {
        if (root == null) {
            return true;
        }
        boolean flag = true;
        if (nag >= 1) {
            flag = root.val < value;
        } else {
            flag = root.val > value;
        }
        return flag && bsfCompare(root.left, value, nag) && bsfCompare(root.right, value, nag);
    }

    /**
     * 尝试返回最大值和最小值
     */
    public static Integer bsfSearchMaxvalue(TreeNode root) {
        if (root == null) {
            return null;
        }
        //增加缓存
        if (temporaryMaxValue.get(root) != null) {
            return temporaryMaxValue.get(root);
        }
        Integer leftValue = bsfSearchMaxvalue(root.left);
        Integer rightValue = bsfSearchMaxvalue(root.right);
        int val = leftValue != null && leftValue.intValue() > root.val ? leftValue : root.val;
        val = rightValue != null && rightValue.intValue() > val ? rightValue : val;
        temporaryMaxValue.put(root, val);
        return val;
    }

    public static Integer bsfSearchMinvalue(TreeNode root) {
        if (root == null) {
            return null;
        }
        //增加缓存
        if (temporaryMinValue.get(root) != null) {
            return temporaryMinValue.get(root);
        }
        Integer leftValue = bsfSearchMinvalue(root.left);
        Integer rightValue = bsfSearchMinvalue(root.right);
        Integer val = leftValue != null && leftValue.intValue() < root.val ? leftValue : root.val;
        val = rightValue != null && rightValue.intValue() < val ? rightValue : val;
        temporaryMinValue.put(root, val);
        return val;
    }

    /**
     * leetCode中的答案
     */
    public static boolean isValidBSTInner(TreeNode root, long min, long max) {
        if (root == null) {
            return true;
        }
        if (root.val <= min || root.val >= max) {
            return false;
        }
        return isValidBSTInner(root.left, min, root.val) && isValidBSTInner(root.right, root.val, max);
    }
}
