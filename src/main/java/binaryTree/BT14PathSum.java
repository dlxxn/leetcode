package binaryTree;

/**
 * 给你二叉树的根节点root 和一个表示目标和的整数targetSum 。判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和targetSum 。
 * 如果存在，返回 true ；否则，返回 false 。
 *
 * easy
 */
public class BT14PathSum {
    public static void main(String[] args) {
        String str = "[5,4,8,11,null,13,4,7,2,null,null,null,1]";
        TreeNode treeNode = TreeNode.mkTree(str);
        System.out.println(new BT14PathSum().hasPathSum(treeNode, 22));
    }

    /**
     * 递归
     * 思路及算法
     * 观察要求我们完成的函数，我们可以归纳出它的功能：询问是否存在从当前节点 root 到叶子节点的路径，满足其路径和为 sum。
     * 假定从根节点到当前节点的值之和为 val，我们可以将这个大问题转化为一个小问题：是否存在从当前节点的子节点到叶子的路径，满足其路径和为 sum - val。
     * 不难发现这满足递归的性质，若当前节点就是叶子节点，那么我们直接判断 sum 是否等于 val 即可（因为路径和已经确定，就是当前节点的值，
     * 我们只需要判断该路径和是否满足条件）。若当前节点不是叶子节点，我们只需要递归地询问它的子节点是否能满足条件即可。
     *
     * 复杂度分析
     * 时间复杂度：O(N)，其中 N是树的节点数。对每个节点访问一次。
     * 空间复杂度：O(H)，其中 H是树的高度。空间复杂度主要取决于递归时栈空间的开销，最坏情况下，树呈现链状，空间复杂度为 O(N)。
     * 平均情况下树的高度与节点数的对数正相关，空间复杂度为 O(logN)。
     * @return
     */
    private boolean hasPathSum(TreeNode root, int sum){
        if (root == null) {
            return false;
        }

        if (root.left == null && root.right == null) {
                return sum == root.val;
        }

        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }
}
