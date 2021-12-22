package binaryTree;

import java.util.ArrayList;
import java.util.List;

/**
 * 右视图
 * easy
 */
public class BT05RightSideView {
    public static void main(String[] args) {
        String str = "[1,2,3,null,5,null,4]";
        TreeNode treeNode = TreeNode.mkTree(str);
        System.out.println(new BT05RightSideView().rightSideView(treeNode));
    }

    /**
     * 思路： 利用 BFS 进行层次遍历，记录下每层的最后一个元素。
     * 时间复杂度： O(N)，每个节点都入队出队了 1 次。
     * 空间复杂度： O(N)，使用了额外的队列空间。
     * @param root
     * @return
     */
    /*public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();

        if (root == null) {
            return res;
        }

        Queue<TreeNode> queue = new LinkedList<TreeNode>();

        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }

                //广度优先搜索，类似于层次遍历，取当前层的最后一个节点放到队列中
                if (i == size - 1) {
                    res.add(node.val);
                }
            }
        }
        return res;
    }*/

    /**
     * 思路： 我们按照 「根结点 -> 右子树 -> 左子树」 的顺序访问，就可以保证每层都是最先访问最右边的节点的。
     *
     * （与先序遍历 「根结点 -> 左子树 -> 右子树」 正好相反，先序遍历每层最先访问的是最左边的节点）
     *
     * 时间复杂度： O(N)，每个节点都访问了 1 次。
     * 空间复杂度： O(N)，因为这不是一棵平衡二叉树，二叉树的深度最少是 logN, 最坏的情况下会退化成一条链表，深度就是 N，因此递归时使用的栈空间是 O(N) 的。
      */
    List<Integer> res = new ArrayList<Integer>();

    public List<Integer> rightSideView(TreeNode root) {

        dfs(root, 0);

        return res;
    }

    private void dfs(TreeNode root, int depth) {
        if (root ==null) {
            return ;
        }

        if (depth == res.size()) {
            res.add(root.val);
        }

        depth++;

        dfs(root.right, depth);
        dfs(root.left, depth);
    }
}
