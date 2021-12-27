package binaryTree;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 找出平衡二叉树中k小的节点
 *
 * 预备知识
 *
 * 二叉搜索树具有如下性质：
 * 结点的左子树只包含小于当前结点的数。
 * 结点的右子树只包含大于当前结点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * 二叉树的中序遍历即按照访问左子树——根结点——右子树的方式遍历二叉树；在访问其左子树和右子树时，我们也按照同样的方式遍历；直到遍历完整棵树。
 */
public class BT23KthSmallest {
    public static void main(String[] args) {
        String str = "[3,1,4,null,2]";
        TreeNode treeNode = TreeNode.mkTree(str);
        System.out.println(new BT23KthSmallest().kthSmallest(treeNode, 3));
    }

    /**
     * 复杂度分析
     *
     * 时间复杂度：O(H+k)，其中 H 是树的高度。在开始遍历之前，我们需要 O(H) 到达叶结点。当树是平衡树时，时间复杂度取得最小值 O(logN+k)；当树是线性树（树中每个结点都只有一个子结点或没有子结点）时，时间复杂度取得最大值 O(N+k)。
     *
     * 空间复杂度：O(H)，栈中最多需要存储 H 个元素。当树是平衡树时，空间复杂度取得最小值 O(logN)；当树是线性树时，空间复杂度取得最大值 O(N)。
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest(TreeNode root, int k) {
        Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            --k;
            if (k == 0) {
                break;
            }
            root = root.right;
        }
        return root.val;
    }
}
