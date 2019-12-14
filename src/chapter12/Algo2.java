package chapter12;

/**
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。
 * 要求不能创建任何新的结点，只能调整树中结点指针的指向。
 *
 * note:二叉搜索树有以下特征：
 *    1、若某结点A有左子树，则左子树中所有的值均小于该结点。
 *    2、若某结点A有右子树，则右子树中所有的值均大于该结点
 *    这两点特性，同样适用于其它子树。
 */
public class Algo2 {

    public static void main(String[] args) {

    }

    class TreeNode {
        int v;
        TreeNode left;
        TreeNode right;
    }

    TreeNode Conver(TreeNode pRoot) {
        if (pRoot == null) {
            return null;
        }
        TreeNode left = Conver(pRoot.left);
        if (left != null) {
            left.right = pRoot;
            pRoot.left = left;
        }

        TreeNode right = pRoot.right;
        if (right != null) {
            right.left = pRoot;
            return Conver(right);
        }

        return pRoot;
    }
}
