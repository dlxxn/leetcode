package binaryTree;

/**
 * 剑指offer 给定一棵二叉搜索树，请找出其中第 k 大的节点的值。
 */
public class BT19KthLargest {
    public static void main(String[] args) {
        String str = "[3,1,4,null,2]";
        //String str = "[1,2,2,null,3,null,3]";
        TreeNode treeNode = TreeNode.mkTree(str);
        System.out.println(new BT19KthLargest().kthLargest(treeNode, 3));
    }

    /**
     * 解题思路：
     * 本文解法基于此性质：二叉搜索树的中序遍历为 递增序列 。
     *
     * 根据以上性质，易得二叉搜索树的 中序遍历倒序 为 递减序列 。
     * 因此，求 “二叉搜索树第 k 大的节点” 可转化为求 “此树的中序遍历倒序的第 k 个节点”。
     *
     * 复杂度分析：
     * 时间复杂度 O(N) ： 当树退化为链表时（全部为右子节点），无论 k 的值大小，递归深度都为 N ，占用 O(N) 时间。
     * 空间复杂度 O(N) ： 当树退化为链表时（全部为右子节点），系统使用 O(N) 大小的栈空间。
     * @param root
     * @param k
     * @return
     */
    int res, k;
    public int kthLargest(TreeNode root, int k) {
        this.k = k;
        dfs(root);
        return res;
    }
    void dfs(TreeNode root) {
        if(root == null) {
            return;
        }
        dfs(root.right);
        if(k == 0) {
            return;
        }
        if(--k == 0) {
            res = root.val;
        }
        dfs(root.left);
    }
}
