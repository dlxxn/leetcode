package binaryTree;

/**
 * 判断两个树是不是相同的
 * easy
 */
public class BT27IsSameTree {
    public static void main(String[] args) {
        String str1 = "[1,2,5,3,4,null,6]";
        String str2 = "[1,2,5,3,4,null,7]";
        //String str = "[1,2,2,null,3,null,3]";
        TreeNode treeNode1 = TreeNode.mkTree(str1);
        TreeNode treeNode2 = TreeNode.mkTree(str2);
        System.out.println(new BT27IsSameTree().isSameTree(treeNode1, treeNode2));
    }

    /**
     * 深度优先搜索
     *如果两个二叉树都为空，则两个二叉树相同。如果两个二叉树中有且只有一个为空，则两个二叉树一定不相同。
     * 如果两个二叉树都不为空，那么首先判断它们的根节点的值是否相同，若不相同则两个二叉树一定不同，若相同，再分别判断两个二叉树的左子树是否相同以
     * 及右子树是否相同。这是一个递归的过程，因此可以使用深度优先搜索，递归地判断两个二叉树是否相同。
     *
     * 复杂度分析
     *
     * 时间复杂度：O(min(m,n))，其中 m 和 n 分别是两个二叉树的节点数。对两个二叉树同时进行深度优先搜索，只有当两个二叉树中的对应节点都不为空时
     * 才会访问到该节点，因此被访问到的节点数不会超过较小的二叉树的节点数。
     *
     * 空间复杂度：O(min(m,n))，其中 m 和 n 分别是两个二叉树的节点数。空间复杂度取决于递归调用的层数，递归调用的层数不会超过较小的二叉树的最大
     * 高度，最坏情况下，二叉树的高度等于节点数。
     * @param q
     * @param p
     * @return
     */
    public boolean isSameTree(TreeNode q, TreeNode p) {
        if (q == null && p == null) {
            return true;
        }

        if (q == null || q == null) {
            return false;
        }

        if (q.val != p.val) {
            return false;
        }

        return isSameTree(q.left, p.left) && isSameTree(q.right, p.right);
    }
}
