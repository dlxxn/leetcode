package binaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树层序遍历
 */
public class BT01LevelOrderTraversal {
    public static void main(String[] args) {
        String str = "[3,9,20,null,null,15,7]";
        TreeNode treeNode = TreeNode.mkTree(str);
        System.out.println(new BT01LevelOrderTraversal().levelOrder(treeNode));
    }


    /**
     * 广度优先搜索
     * 复杂度分析
     *
     * 记树上所有节点的个数为 nn。
     *
     * 时间复杂度：每个点进队出队各一次，故渐进时间复杂度为 O(n)。
     * 空间复杂度：队列中元素的个数不超过 nn 个，故渐进空间复杂度为 O(n)。
     */
//    public List<List<Integer>> levelOrder(TreeNode root) {
//        List<List<Integer>> ret = new ArrayList<List<Integer>>();
//        if (root == null) {
//            return ret;
//        }
//
//        Queue<TreeNode> queue = new LinkedList<TreeNode>();
//        queue.offer(root);
//        while (!queue.isEmpty()) {
//            List<Integer> level = new ArrayList<Integer>();
//            int currentLevelSize = queue.size();
//            for (int i = 1; i <= currentLevelSize; ++i) {
//                TreeNode node = queue.poll();
//                level.add(node.val);
//                if (node.left != null) {
//                    queue.offer(node.left);
//                }
//                if (node.right != null) {
//                    queue.offer(node.right);
//                }
//            }
//            ret.add(level);
//        }
//
//        return ret;
//    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();

        if (root == null) {
            return ret;
        }

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<Integer>();
            int currentSize = queue.size();
            for (int i = 1; i <= currentSize; ++i) {
                TreeNode node = queue.poll();
                level.add(node.val);

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            ret.add(level);
        }

        return ret;
    }
}

