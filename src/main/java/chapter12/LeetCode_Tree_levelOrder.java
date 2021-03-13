package chapter12;

import com.alibaba.druid.sql.ast.expr.SQLUnaryExpr;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LeetCode_Tree_levelOrder {

    public static void main(String[] args) {
        System.out.println(levelOrder(TreeNode.getInstance(new Integer[]{3, 99, 20, null, null, 15, 7}, 0)));
    }

    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int i=1;
        while (queue.size() > 0) {
            TreeNode node;
            List<Integer> aList = new ArrayList<>();
            Queue<TreeNode> tQueue = new LinkedList<>();
            while ((node = queue.poll()) != null) {
                aList.add(node.val);
                if (node.left != null) {
                    tQueue.add(node.left);
                }
                if (node.right != null) {
                    tQueue.add(node.right);
                }
            }
            queue.addAll(tQueue);
            if (!aList.isEmpty()) {
                list.add(aList);
            }
        }
       return list;
    }


//    private void dfs(List<List<Integer>> list, TreeNode root) {
//        List alist = new ArrayList();
//        list.add(alist);
//        alist.add(root.val);
//
//    }
}
