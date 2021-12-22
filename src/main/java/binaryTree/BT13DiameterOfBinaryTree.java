package binaryTree;

/**
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
 * easy
 */
public class BT13DiameterOfBinaryTree {
    public static void main(String[] args) {
        String str = "[1,2,3,4,5,6,null]";
        TreeNode treeNode = TreeNode.mkTree(str);
        System.out.println(new BT13DiameterOfBinaryTree().diameterOfBinaryTree(treeNode));
    }


    /**
     *
     * 复杂度分析
     * 时间复杂度：O(N)，其中 N 为二叉树的节点数，即遍历一棵二叉树的时间复杂度，每个结点只被访问一次。
     * 空间复杂度：O(Height)，其中 Height 为二叉树的高度。由于递归函数在递归过程中需要为每一层递归函数分配栈空间，
     * 所以这里需要额外的空间且该空间取决于递归的深度，而递归的深度显然为二叉树的高度，并且每次递归调用的函数里又只用了常数个变量，所以所需空间复杂度为 O(Height) 。
     */

    int ans = 1;
    public int diameterOfBinaryTree(TreeNode root) {
        depth(root);
        return ans -1 ;
    }
    public int depth(TreeNode node) {
        if (node == null) {
            return 0; // 访问到空节点了，返回0
        }
        int L = depth(node.left); // 左儿子为根的子树的深度
        int R = depth(node.right); // 右儿子为根的子树的深度
        ans = Math.max(ans, L+R+1); // 计算d_node即L+R+1 并更新ans
        return Math.max(L, R)+1; // 返回该节点为根的子树的深度
    }
}
