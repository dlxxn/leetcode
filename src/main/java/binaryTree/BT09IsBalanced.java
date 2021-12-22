package binaryTree;

/**
 * 判断二叉树是不是平衡的
 * 平衡二叉树的高度不能超过1
 */
public class BT09IsBalanced {
    public static void main(String[] args) {
        String str = "[1,2,3,4,5,6,null,null,null,null,null,7,null]";
        TreeNode treeNode = TreeNode.mkTree(str);
        System.out.println(new BT09IsBalanced().isBalanced(treeNode));
    }

    /**
     * 方法一：自顶向下的递归
     * 定义函数 height用于计算二叉树中的任意一个节点 p的高度：
     * 有了计算节点高度的函数，即可判断二叉树是否平衡。具体做法类似于二叉树的前序遍历，即对于当前遍历到的节点，首先计算左右子树的高度，如果左右子树的高度差是否不超过 1，再分别递归地遍历左右子节点，并判断左子树和右子树是否平衡。这是一个自顶向下的递归的过程。
     *
     */
    /**
     * 方法二：自底向上的递归
     * 方法一由于是自顶向下递归，因此对于同一个节点，函数height 会被重复调用，导致时间复杂度较高。如果使用自底向上的做法，则对于每个节点，函数height 只会被调用一次。
     * 自底向上递归的做法类似于后序遍历，对于当前遍历到的节点，先递归地判断其左右子树是否平衡，再判断以当前节点为根的子树是否平衡。如果一棵子树是平衡的，则返回其高度（高度一定是非负整数），否则返回 −1。如果存在一棵子树不平衡，则整个二叉树一定不平衡。
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        return height(root) >= 0;
    }

    public int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        } else {
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }
}
