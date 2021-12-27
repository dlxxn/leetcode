package binaryTree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树最大宽度
 * 给定一个二叉树，编写一个函数来获取这个树的最大宽度。树的宽度是所有层中的最大宽度。这个二叉树与满二叉树（full binary tree）结构相同，但一些节点为空。
 * 每一层的宽度被定义为两个端点（该层最左和最右的非空节点，两端点间的null节点也计入长度）之间的长度。
 *
 * medium
 **/
public class BT21WidthOfBinaryTree {

    public static void main(String[] args) {
        String str = "[1,3,2,5,3,null,9]";
        TreeNode treeNode = TreeNode.mkTree(str);
        System.out.println(new BT21WidthOfBinaryTree().widthOfBinaryTree(treeNode));
    }

    /**
     * 宽度优先搜索
     * 解释
     * 由于我们需要将给定树中的每个节点都访问一遍，我们需要遍历树。我们可以用深度优先搜索或者宽度优先搜索将树遍历。
     * 这个问题中的主要想法是给每个节点一个 position 值，如果我们走向左子树，那么 position -> position * 2，如果我们走向右子树，
     * 那么 position -> positon * 2 + 1。当我们在看同一层深度的位置值 L 和 R 的时候，宽度就是 R - L + 1。
     *
     * 复杂度分析
     * 时间复杂度： O(N)，其中 N 是输入树的节点数目，我们遍历每个节点一遍。
     * 空间复杂度： O(N)，这是 queue 的大小。
     * @param root
     * @return
     */
    public int widthOfBinaryTree(TreeNode root){

        Queue<AnnotatedNode> queue = new LinkedList<AnnotatedNode>();
        int curDepth =0;
        int left = 0 ;
        int ans = 0;

        queue.offer(new AnnotatedNode(root, 0, 0));

        while (!queue.isEmpty()) {
            AnnotatedNode anode = queue.poll();

            if (anode.node != null) {
                queue.offer(new AnnotatedNode(anode.node.left, anode.depth, anode.position *2));
                queue.offer(new AnnotatedNode(anode.node.right, anode.depth, anode.position * 2 +1));
                if (curDepth != anode.depth) {
                    curDepth = anode.depth;
                    left = anode.position;
                }

                ans = Math.max(ans, anode.position - left + 1);
            }
        }

        return ans;
    }

    class AnnotatedNode {
        TreeNode node;
        int depth;
        int position;

        AnnotatedNode(TreeNode node, int depth, int position) {
            this.node = node;
            this.depth = depth;
            this.position = position;
        }
    }
}
