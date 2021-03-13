package chapter12;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 对称二叉树
 * 给定一个二叉树，检查它是否是镜像对称的。
 */
public class LeetCode_Tree_isSymmetric {

    public static void main(String[] args) {
        System.out.println(isSymmetric2(TreeNode.getInstance(new Integer[]{1, 2, 2, 3, 4, 4, 3}, 0)));
//        System.out.println(isSymmetric2(TreeNode.getInstance(new Integer[]{1, 2, 2, null, 3, null, 3}, 0)));
    }

    public static boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return false;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root.left);
        queue.add(root.right);
        while (queue.size() != 0) {
            //left
            TreeNode left = queue.poll();
            //right
            TreeNode right = queue.poll();

            if (left==null && right == null) {
                continue;
            }
            if ((left != null && right == null) || (left == null && right != null) || (left.val != right.val)) {
                return false;
            }

            queue.add(left.left);
            queue.add(right.right);
            queue.add(left.right);
            queue.add(right.left);
        }

        if (queue.size() == 0) {
            return true;
        }
        return false;
    }


    /**
     * 递归方法
     * @param root
     * @return
     */
    public static boolean isSymmetric2(TreeNode root) {
        if (root == null) {
            return false;
        }

        return check(root.left, root.right);
    }

    private static boolean check(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }

        if ((left != null && right == null) || (left == null && right != null) || (left.val != right.val)) {
            return false;
        }

        return check(left.left, right.right) && check(left.right, right.left);
    }
}
