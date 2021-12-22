package binaryTree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树的锯齿形层序遍历
 */
public class BT02ZigZagLevelOrderTraversal {
    public static void main(String[] args) {
        String str = "[3,9,20,null,null,15,7]";
        TreeNode treeNode = TreeNode.mkTree(str);
        System.out.println(new BT02ZigZagLevelOrderTraversal().zigzagLevelOrder(treeNode));
    }

    /**
     * 复杂度分析
     *
     * 时间复杂度：O(N)，其中 NN 为二叉树的节点数。每个节点会且仅会被遍历一次。
     *
     * 空间复杂度：O(N)。我们需要维护存储节点的队列和存储节点值的双端队列，空间复杂度为 O(N)。
     * @param root
     * @return
     */
//    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
//        List<List<Integer>> ans = new LinkedList<List<Integer>>();
//        if (root == null) {
//            return ans;
//        }
//
//        Queue<TreeNode> nodeQueue = new LinkedList<TreeNode>();
//        nodeQueue.offer(root);
//        boolean isOrderLeft = true;
//
//        while (!nodeQueue.isEmpty()) {
//            Deque<Integer> levelList = new LinkedList<Integer>();
//            int size = nodeQueue.size();
//            for (int i = 0; i < size; ++i) {
//                TreeNode curNode = nodeQueue.poll();
//                if (isOrderLeft) {
//                    levelList.offerLast(curNode.val);
//                } else {
//                    levelList.offerFirst(curNode.val);
//                }
//                if (curNode.left != null) {
//                    nodeQueue.offer(curNode.left);
//                }
//                if (curNode.right != null) {
//                    nodeQueue.offer(curNode.right);
//                }
//            }
//            ans.add(new LinkedList<Integer>(levelList));
//            isOrderLeft = !isOrderLeft;
//        }
//
//        return ans;
//    }

    public List<List<Integer>>  zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<List<Integer>>();

        if (root == null) {
            return ans;
        }

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        boolean isOrderLeft = true;

        while (!queue.isEmpty()) {
            Deque<Integer> levelList = new LinkedList<Integer>();
            int size = queue.size();
            for (int i = 1; i<=size; ++i) {
                TreeNode currentNode = queue.poll();
                if (isOrderLeft) {
                    levelList.addLast(currentNode.val);
                } else {
                    levelList.addFirst(currentNode.val);
                }

                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }

                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }
            ans.add(new LinkedList<>(levelList));
            isOrderLeft = !isOrderLeft;
        }

        return ans;
    }
}
