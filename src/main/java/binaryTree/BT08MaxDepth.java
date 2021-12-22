package binaryTree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 最大深度
 * easy
 */
public class BT08MaxDepth {
    public static void main(String[] args) {
        String str = "[1,2,3,4,5,6,null]";
        TreeNode treeNode = TreeNode.mkTree(str);
        System.out.println(new BT08MaxDepth().maxDepth(treeNode));
    }

    /**
     * 深度优先搜索
     * 复杂度分析
     *
     * 时间复杂度：O(n)，其中 nn 为二叉树节点的个数。每个节点在递归中只被遍历一次。
     *
     * 空间复杂度：O(height)，其中height 表示二叉树的高度。递归函数需要栈空间，而栈空间取决于递归的深度，因此空间复杂度等价于二叉树的高度。
     * @param root
     * @return
     */
//    public int maxDepth(TreeNode root) {
//        if (root == null) {
//            return 0;
//        } else  {
//            int leftHeight = maxDepth(root.left);
//            int rightHeight = maxDepth(root.right);
//
//            return Math.max(leftHeight, rightHeight) + 1;
//        }
//    }

    /**
     * 广度优先搜索
     * 复杂度分析
     *
     * 时间复杂度：O(n)，其中 nn 为二叉树的节点个数。与方法一同样的分析，每个节点只会被访问一次。
     *
     * 空间复杂度：此方法空间的消耗取决于队列存储的元素数量，其在最坏情况下会达到 O(n)O(n)。
     * */

    public int maxDepth(TreeNode root) {
        int ans = 0;

        if (root== null) {
            return ans;
        }

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size>0) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                size--;
            }
            ans++;
        }

        return ans;
    }
}
