package chapter12;

import java.util.Arrays;

public class LeetCode_Tree_maxDepth {

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{3,9,20,null,null,15,7};
        final TreeNode instance = TreeNode.getInstance(arr, 0);
        System.out.println(maxDepth(instance));
    }

    /**
     * 使用递归，深度搜索树
     * @param root
     * @return
     */
    public static int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        int a,b;
        return ((a=maxDepth(root.left)) < (b=maxDepth(root.right)) ? b : a) + 1;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return val + "";
    }

    public static TreeNode getInstance(Integer[] args, int root) {
        if (root>= args.length || args[root] == null) {
            return null;
        }

        TreeNode rootNode = new TreeNode(args[root]);
        int left = root * 2 + 1;
        if (left < args.length && args[left] != null) {
            rootNode.left = getInstance(args, left);
        }

        int right = root * 2 + 2;
        if (right < args.length && args[right] != null) {
            rootNode.right = getInstance(args, right);
        }
        return rootNode;
    }

    private static Integer[] copyArrays(Integer[] arr, int offset) {
        Integer[] tt = new Integer[arr.length - offset];
        int i = 0;
        for (; offset < arr.length;) {
            tt[i++] = arr[offset++];
        }
        return tt;
    }
}